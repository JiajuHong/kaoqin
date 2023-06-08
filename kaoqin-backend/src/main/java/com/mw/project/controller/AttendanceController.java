package com.mw.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mw.project.annotation.AuthCheck;
import com.mw.project.common.BaseResponse;
import com.mw.project.common.DeleteRequest;
import com.mw.project.common.ErrorCode;
import com.mw.project.common.ResultUtils;
import com.mw.project.constant.CommonConstant;
import com.mw.project.exception.BusinessException;
import com.mw.project.mapper.AttendanceMapper;
import com.mw.project.mapper.EmployeeMapper;
import com.mw.project.model.dto.Attendance.AttendanceAddRequest;
import com.mw.project.model.dto.Attendance.AttendanceQueryRequest;
import com.mw.project.model.dto.Attendance.AttendanceUpdateRequest;
import com.mw.project.model.entity.Attendance;
import com.mw.project.model.entity.Employee;
import com.mw.project.model.entity.User;
import com.mw.project.service.AttendanceService;
import com.mw.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 帖子接口
 *
 * @author yupi
 */
@RestController
@RequestMapping("/attendance")
@Slf4j
public class AttendanceController {

    @Resource
    private AttendanceService attendanceService;

    @Resource
    private UserService userService;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private AttendanceMapper attendanceMapper;

    // region 增删改查

    /**
     * 创建
     *
     * @param attendanceAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addAttendance(@RequestBody AttendanceAddRequest attendanceAddRequest, HttpServletRequest request) {
        if (attendanceAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Attendance attendance = new Attendance();
        BeanUtils.copyProperties(attendanceAddRequest, attendance);
        // 校验
        attendanceService.validAttendance(attendance, true);
        String employeeAccount = attendanceAddRequest.getEmployeeAccount();
        String name = attendanceAddRequest.getName();
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("employeeAccount", employeeAccount);
        Employee employee = employeeMapper.selectOne(wrapper);
        if (employee == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "员工账号不存在");
        }
        QueryWrapper<Employee> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("employeeName", name);
        List<Employee> employees = employeeMapper.selectList(wrapper1);
        if (employees.size() < 1) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "没有该员工");
        }
        boolean result = attendanceService.save(attendance);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newAttendanceId = attendance.getId();
        return ResultUtils.success(newAttendanceId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteAttendance(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        Attendance oldAttendance = attendanceService.getById(id);
        if (oldAttendance == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅管理员可删除
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = attendanceService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param attendanceUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateAttendance(@RequestBody AttendanceUpdateRequest attendanceUpdateRequest,
                                            HttpServletRequest request) {
        if (attendanceUpdateRequest == null || attendanceUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Attendance attendance = new Attendance();
        BeanUtils.copyProperties(attendanceUpdateRequest, attendance);
        // 参数校验
        attendanceService.validAttendance(attendance, false);
        long id = attendanceUpdateRequest.getId();
        // 判断是否存在
        Attendance oldAttendance = attendanceService.getById(id);
        if (oldAttendance == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        String employeeAccount = attendanceUpdateRequest.getEmployeeAccount();
        String name = attendanceUpdateRequest.getName();
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        wrapper.eq("employeeAccount", employeeAccount);
        Employee employee = employeeMapper.selectOne(wrapper);
        if (employee == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "员工账号不存在");
        }
        QueryWrapper<Employee> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("employeeName", name);
        List<Employee> employees = employeeMapper.selectList(wrapper1);
        if (employees.size() < 1) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "没有该员工");
        }
        // 仅本人或管理员可修改
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = attendanceService.updateById(attendance);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Attendance> getAttendanceById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Attendance attendance = attendanceService.getById(id);
        return ResultUtils.success(attendance);
    }

    @GetMapping("/getMyAttendance")
    public BaseResponse<List<Attendance>> getMyAttendance(AttendanceQueryRequest attendanceQueryRequest, HttpServletRequest request) {
        Attendance attendanceQuery = new Attendance();
        if (attendanceQueryRequest != null) {
            BeanUtils.copyProperties(attendanceQueryRequest, attendanceQuery);
        }
        User user = userService.getLoginUser(request);
        String employeeAccount = user.getEmployeeAccount();
        attendanceQuery.setEmployeeAccount(employeeAccount);
        QueryWrapper<Attendance> wrapper = new QueryWrapper<>(attendanceQuery);
        List<Attendance> attendances = attendanceMapper.selectList(wrapper);
        // 根据考勤日期排序
        attendances.sort(Comparator.comparing(Attendance::getAttendanceDate));
        return ResultUtils.success(attendances);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param attendanceQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<Attendance>> listAttendance(AttendanceQueryRequest attendanceQueryRequest) {
        Attendance attendanceQuery = new Attendance();
        if (attendanceQueryRequest != null) {
            BeanUtils.copyProperties(attendanceQueryRequest, attendanceQuery);
        }
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>(attendanceQuery);
        List<Attendance> attendanceList = attendanceService.list(queryWrapper);
        // 根据考勤日期排序
        attendanceList.sort(Comparator.comparing(Attendance::getAttendanceDate));
        return ResultUtils.success(attendanceList);
    }

    /**
     * 分页获取列表
     *
     * @param attendanceQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Attendance>> listAttendanceByPage(AttendanceQueryRequest attendanceQueryRequest, HttpServletRequest request) {
        if (attendanceQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Attendance attendanceQuery = new Attendance();
        BeanUtils.copyProperties(attendanceQueryRequest, attendanceQuery);
        long current = attendanceQueryRequest.getCurrent();
        long size = attendanceQueryRequest.getPageSize();
        String sortField = attendanceQueryRequest.getSortField();
        String sortOrder = attendanceQueryRequest.getSortOrder();

        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper<>(attendanceQuery);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<Attendance> attendancePage = attendanceService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(attendancePage);
    }

    // endregion

}
