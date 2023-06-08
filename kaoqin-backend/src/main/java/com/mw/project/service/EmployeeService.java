package com.mw.project.service;

import com.mw.project.model.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mw.project.model.entity.Employee;

/**
* @author jiaju
* @description 针对表【employee(员工表)】的数据库操作Service
* @createDate 2023-04-20 00:00:46
*/
public interface EmployeeService extends IService<Employee> {
    void validEmployee(Employee employee, boolean add);

}
