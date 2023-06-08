package com.mw.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mw.project.common.ErrorCode;
import com.mw.project.exception.BusinessException;
import com.mw.project.model.entity.Department;
import com.mw.project.model.enums.PostGenderEnum;
import com.mw.project.model.enums.PostReviewStatusEnum;
import com.mw.project.service.DepartmentService;
import com.mw.project.mapper.DepartmentMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author jiaju
* @description 针对表【department(部门表)】的数据库操作Service实现
* @createDate 2023-04-19 22:50:54
*/
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department>
    implements DepartmentService{

    @Override
    public void validDept(Department department, boolean add) {
        if (department == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        String name = department.getDepartmentName();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "部门名称不能为空");
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
    }
}




