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
import com.mw.project.model.dto.employee.EmployeeAddRequest;
import com.mw.project.model.dto.employee.EmployeeQueryRequest;
import com.mw.project.model.dto.employee.EmployeeUpdateRequest;
import com.mw.project.model.entity.Department;
import com.mw.project.model.entity.Employee;
import com.mw.project.model.vo.EmployeeVO;
import com.mw.project.service.DepartmentService;
import com.mw.project.service.EmployeeService;
import com.mw.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 帖子接口
 *
 * @author yupi
 */
@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private UserService userService;

    @Resource
    private DepartmentService departmentService;

    // region 增删改查

    /**
     * 创建
     *
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addEmployee(@RequestBody EmployeeAddRequest employeeAddRequest, HttpServletRequest request) {
        if (employeeAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeAddRequest, employee);
        // 校验
        employeeService.validEmployee(employee, true);
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("departmentName", employee.getDepartment());
        Department department = departmentService.getOne(wrapper);
        if (department == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "部门不存在");
        }
        employee.setDepartmentId(department.getId());
        boolean result = employeeService.save(employee);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        Long id = employee.getId();
        return ResultUtils.success(id);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteEmployee(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = deleteRequest.getId();
        // 判断是否存在
        Employee oldEmployee = employeeService.getById(id);
        if (oldEmployee == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "员工不存在");
        }
        // 仅管理员可删除
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = employeeService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param employeeUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateEmployee(@RequestBody EmployeeUpdateRequest employeeUpdateRequest,
                                            HttpServletRequest request) {
        if (employeeUpdateRequest == null || employeeUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeUpdateRequest, employee);
        // 参数校验
        employeeService.validEmployee(employee, false);
        long id = employeeUpdateRequest.getId();
        // 判断是否存在
        Employee oldEmployee = employeeService.getById(id);
        if (oldEmployee == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "员工不存在");
        }
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("departmentName", employee.getDepartment());
        Department department = departmentService.getOne(wrapper);
        if (department == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "部门不存在");
        }
        employee.setDepartmentId(department.getId());
        // 仅管理员可修改
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = employeeService.updateById(employee);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Employee> getEmployeeById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Employee employee = employeeService.getById(id);
        return ResultUtils.success(employee);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param employeeQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<EmployeeVO>> listEmployee(EmployeeQueryRequest employeeQueryRequest) {
        Employee employeeQuery = new Employee();
        if (employeeQueryRequest != null) {
            BeanUtils.copyProperties(employeeQueryRequest, employeeQuery);
        }
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>(employeeQuery);
        // 如果查询条件中包含部门名称，则需要先查询部门表，再根据部门 id 查询员工表
        if (StringUtils.isNotBlank(employeeQuery.getDepartment())) {
            QueryWrapper<Department> wrapper = new QueryWrapper<>();
            wrapper.eq("departmentName", employeeQuery.getDepartment());
            Department department = departmentService.getOne(wrapper);
            if (department == null) {
                throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "部门不存在");
            }
            queryWrapper.eq("departmentId", department.getId()); // 根据部门 id 查询员工表
        }
        List<Employee> employeeList = employeeService.list(queryWrapper);
        List<EmployeeVO> list = employeeList.stream().map(employee -> {
            EmployeeVO employeeVO = new EmployeeVO();
            QueryWrapper<Department> wrapper = new QueryWrapper<>();
            wrapper.eq("id", employee.getDepartmentId());
            Department department = departmentService.getOne(wrapper);
            if (department != null) {
                employee.setDepartment(department.getDepartmentName());
            }
            BeanUtils.copyProperties(employee, employeeVO);
            return employeeVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    /**
     * 分页获取列表
     *
     * @param employeeQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Employee>> listEmployeeByPage(EmployeeQueryRequest employeeQueryRequest, HttpServletRequest request) {
        if (employeeQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Employee employeeQuery = new Employee();
        BeanUtils.copyProperties(employeeQueryRequest, employeeQuery);
        long current = employeeQueryRequest.getCurrent();
        long size = employeeQueryRequest.getPageSize();
        String sortField = employeeQueryRequest.getSortField();
        String sortOrder = employeeQueryRequest.getSortOrder();
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<Employee> queryWrapper = new QueryWrapper<>(employeeQuery);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<Employee> employeePage = employeeService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(employeePage);
    }
    // endregion
}
