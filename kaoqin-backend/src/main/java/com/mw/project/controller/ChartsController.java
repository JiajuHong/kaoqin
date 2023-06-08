package com.mw.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.j2objc.annotations.RetainedLocalRef;
import com.mw.project.annotation.AuthCheck;
import com.mw.project.common.BaseResponse;
import com.mw.project.common.DeleteRequest;
import com.mw.project.common.ErrorCode;
import com.mw.project.common.ResultUtils;
import com.mw.project.constant.CommonConstant;
import com.mw.project.exception.BusinessException;
import com.mw.project.mapper.AttendanceMapper;
import com.mw.project.mapper.EmployeeMapper;
import com.mw.project.model.dto.department.DeptAddRequest;
import com.mw.project.model.dto.department.DeptQueryRequest;
import com.mw.project.model.dto.department.DeptUpdateRequest;
import com.mw.project.model.entity.Attendance;
import com.mw.project.model.entity.Department;
import com.mw.project.model.entity.Employee;
import com.mw.project.model.entity.User;
import com.mw.project.model.vo.AttendanceChartVO;
import com.mw.project.model.vo.EmployeeChartVO;
import com.mw.project.service.AttendanceService;
import com.mw.project.service.DepartmentService;
import com.mw.project.service.EmployeeService;
import com.mw.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 帖子接口
 *
 * @author yupi
 */
@RestController
@RequestMapping("/charts")
@Slf4j
public class ChartsController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private DepartmentService departmentService;


    @Resource
    private AttendanceMapper attendanceMapper;

    @Resource
    private UserService userService;


    @GetMapping("/getChartsData")
    public BaseResponse<HashMap<String, Object>> getChartsData() {
        HashMap<String, Object> map = new HashMap<>();
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        // 调用service的count方法实现统计查询
        long employeeCount = employeeService.count(wrapper);
        map.put("employeeCount", employeeCount);

        QueryWrapper<Department> wrapper1 = new QueryWrapper<>();
        long deptCount = departmentService.count(wrapper1);
        map.put("deptCount", deptCount);

        QueryWrapper<Attendance> wrapper2 = new QueryWrapper<>();
        wrapper2.select("count(*) as attendanceCount, attendanceType");
        wrapper2.groupBy("attendanceType");
        // 查询当前月份
        wrapper2.apply("date_format(attendanceDate,'%Y-%m') = date_format(now(),'%Y-%m')");
        List<Attendance> attendanceList = attendanceMapper.selectList(wrapper2);
        List<AttendanceChartVO> attendanceChartVOList = attendanceList.stream().map(attendance -> {
            AttendanceChartVO attendanceChartVO = new AttendanceChartVO();
            BeanUtils.copyProperties(attendance, attendanceChartVO);
            return attendanceChartVO;
        }).collect(Collectors.toList());
        map.put("attendanceList", attendanceChartVOList);

        QueryWrapper<Employee> wrapper3 = new QueryWrapper<>();
        // 与部门表联合查询
        wrapper3.select("count(*) as employeeCount, departmentId");
        wrapper3.groupBy("departmentId");
        List<Employee> employees = employeeMapper.selectList(wrapper3);
        List<EmployeeChartVO> employeeChartVOS = employees.stream().map(employee -> {
            EmployeeChartVO employeeChartVO = new EmployeeChartVO();
            QueryWrapper<Department> wrapper4 = new QueryWrapper<>();
            wrapper4.eq("id", employee.getDepartmentId());
            Department department = departmentService.getOne(wrapper4);
            employee.setDepartment(department.getDepartmentName());
            BeanUtils.copyProperties(employee, employeeChartVO);
            return employeeChartVO;
        }).collect(Collectors.toList());
        map.put("employees", employeeChartVOS);
        return ResultUtils.success(map);
    }
}
