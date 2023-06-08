package com.mw.project.service;

import com.mw.project.model.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mw.project.model.entity.Post;

/**
* @author jiaju
* @description 针对表【department(部门表)】的数据库操作Service
* @createDate 2023-04-19 22:50:54
*/
public interface DepartmentService extends IService<Department> {
    void validDept(Department department, boolean add);

}
