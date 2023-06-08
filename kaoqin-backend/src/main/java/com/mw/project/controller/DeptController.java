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
import com.mw.project.model.dto.department.DeptAddRequest;
import com.mw.project.model.dto.department.DeptQueryRequest;
import com.mw.project.model.dto.department.DeptUpdateRequest;
import com.mw.project.model.entity.Department;
import com.mw.project.service.DepartmentService;
import com.mw.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 帖子接口
 *
 * @author yupi
 */
@RestController
@RequestMapping("/department")
@Slf4j
public class DeptController {

    @Resource
    private DepartmentService departmentService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建
     *
     * @param departmentAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addDepartment(@RequestBody DeptAddRequest departmentAddRequest, HttpServletRequest request) {
        if (departmentAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Department department = new Department();
        BeanUtils.copyProperties(departmentAddRequest, department);
        // 校验
        departmentService.validDept(department, true);
        boolean result = departmentService.save(department);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newDepartmentId = department.getId();
        return ResultUtils.success(newDepartmentId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteDepartment(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long id = deleteRequest.getId();
        // 判断是否存在
        Department oldDepartment = departmentService.getById(id);
        if (oldDepartment == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "部门不存在");
        }
        // 仅管理员可删除
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = departmentService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param departmentUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateDepartment(@RequestBody DeptUpdateRequest departmentUpdateRequest,
                                            HttpServletRequest request) {
        if (departmentUpdateRequest == null || departmentUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Department department = new Department();
        BeanUtils.copyProperties(departmentUpdateRequest, department);
        // 参数校验
        departmentService.validDept(department, false);
        long id = departmentUpdateRequest.getId();
        // 判断是否存在
        Department oldDepartment = departmentService.getById(id);
        if (oldDepartment == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "部门不存在");
        }
        // 仅管理员可修改
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = departmentService.updateById(department);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Department> getDepartmentById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Department department = departmentService.getById(id);
        return ResultUtils.success(department);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param departmentQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<Department>> listDepartment(DeptQueryRequest departmentQueryRequest) {
        Department departmentQuery = new Department();
        if (departmentQueryRequest != null) {
            BeanUtils.copyProperties(departmentQueryRequest, departmentQuery);
        }
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>(departmentQuery);
        List<Department> departmentList = departmentService.list(queryWrapper);
        return ResultUtils.success(departmentList);
    }

    /**
     * 分页获取列表
     *
     * @param departmentQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<Department>> listDepartmentByPage(DeptQueryRequest departmentQueryRequest, HttpServletRequest request) {
        if (departmentQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Department departmentQuery = new Department();
        BeanUtils.copyProperties(departmentQueryRequest, departmentQuery);
        long current = departmentQueryRequest.getCurrent();
        long size = departmentQueryRequest.getPageSize();
        String sortField = departmentQueryRequest.getSortField();
        String sortOrder = departmentQueryRequest.getSortOrder();
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>(departmentQuery);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<Department> departmentPage = departmentService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(departmentPage);
    }
    // endregion
}
