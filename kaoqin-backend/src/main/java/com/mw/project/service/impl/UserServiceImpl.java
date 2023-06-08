package com.mw.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mw.project.common.ErrorCode;
import com.mw.project.constant.UserConstant;
import com.mw.project.exception.BusinessException;
import com.mw.project.mapper.EmployeeMapper;
import com.mw.project.model.entity.Employee;
import com.mw.project.model.entity.User;
import com.mw.project.service.UserService;
import com.mw.project.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* @author jiaju
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-04-19 23:36:45
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private EmployeeMapper employeeMapper;


    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "jiaju";

    @Override
    public long userRegister(String userAccount, String employeeAccount, String userPassword, String checkPassword) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, employeeAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码小于8位");
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        }
        synchronized (userAccount.intern()) {
            // 账户不能重复
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", userAccount);
            long count = userMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号已经被注册");
            }

            QueryWrapper<Employee> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("employeeAccount", employeeAccount);
            Employee employee = employeeMapper.selectOne(queryWrapper1);
            if (employee == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "员工账号不存在");
            }
            // 2. 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
            // 3. 插入数据
            User user = new User();
            user.setUserAccount(userAccount);
            user.setEmployeeAccount(employeeAccount);
            user.setUserPassword(encryptPassword);
            user.setUserName(employee.getEmployeeName());
            user.setGender(employee.getGender());
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return user.getId();
        }
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不足4位");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码不足8位");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 3. 记录用户的登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
        return user;
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        long userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    @Override
    public boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && UserConstant.ADMIN_ROLE.equals(user.getUserRole());
    }
    /**
     * 用户注销
     *
     * @param request
     */
    @Override
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        User userToUpdate = new User();
        // 没有输入密码则不用修改
        if (StringUtils.isNotBlank(user.getUserPassword())) {
            userToUpdate.setUserPassword(DigestUtils.md5DigestAsHex((SALT + user.getUserPassword()).getBytes()));
        }
        if (StringUtils.isNotBlank(user.getUserRole())) {
            userToUpdate.setUserRole(user.getUserRole());
        }
        if (StringUtils.isNotBlank(user.getUserAccount())) {
            String userAccount = user.getUserAccount();
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", userAccount);
            long count = userMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号已经被注册");
            }
            userToUpdate.setUserAccount(userAccount);
        }
        if (StringUtils.isNotBlank(user.getUserName())) {
            userToUpdate.setUserName(user.getUserName());
        }
        if (user.getGender() != null) {
            userToUpdate.setGender(user.getGender());
        }
        if (user.getUserAvatar() != null) {
            userToUpdate.setUserAvatar(user.getUserAvatar());
        }
        userToUpdate.setId(user.getId());
        return userMapper.updateById(userToUpdate) > 0;
    }

    @Override
    public boolean addUser(User user) {
        synchronized (user.getUserAccount().intern()) {
            // 账户不能重复
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", user.getUserAccount());
            long count = userMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
            }

            QueryWrapper<Employee> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("employeeAccount", user.getEmployeeAccount());
            Employee employee = employeeMapper.selectOne(queryWrapper1);
            if (employee == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "员工账号不存在");
            }
            // 2. 加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + user.getUserPassword()).getBytes());
            // 3. 插入数据
            User userToAdd = new User();
            userToAdd.setUserAccount(user.getUserAccount());
            userToAdd.setEmployeeAccount(user.getEmployeeAccount());
            userToAdd.setUserName(user.getUserName());
            userToAdd.setUserPassword(encryptPassword);
            userToAdd.setGender(user.getGender());
            userToAdd.setUserRole(user.getUserRole());
            boolean saveResult = this.save(userToAdd);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return true;
        }
    }

}




