package com.mw.project.service.impl;

import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mw.project.common.ErrorCode;
import com.mw.project.exception.BusinessException;
import com.mw.project.model.entity.Employee;
import com.mw.project.service.EmployeeService;
import com.mw.project.mapper.EmployeeMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author jiaju
 * @description 针对表【employee(员工表)】的数据库操作Service实现
 * @createDate 2023-04-20 00:00:46
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
        implements EmployeeService {

    @Override
    public void validEmployee(Employee employee, boolean add) {
        if (employee == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String department = employee.getDepartment();
        String employeeName = employee.getEmployeeName();
        String employeeAccount = employee.getEmployeeAccount();
        String employeeAvatar = employee.getEmployeeAvatar();
        Integer gender = employee.getGender();
        String employeePosition = employee.getEmployeePosition();

        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(department, employeeName, employeeAccount, employeeAvatar, employeePosition) || ObjectUtils.anyNull(gender)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
    }
}




