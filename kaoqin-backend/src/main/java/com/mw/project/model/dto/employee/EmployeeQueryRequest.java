package com.mw.project.model.dto.employee;

import com.mw.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户查询请求
 *
 * @author yupi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeQueryRequest extends PageRequest implements Serializable {
    /**
     * 部门 id
     */
    private String department;

    /**
     * 员工名
     */
    private String employeeName;

    /**
     * 员工账号
     */
    private String employeeAccount;

    /**
     * 员工头像
     */
    private String employeeAvatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 职位
     */
    private String employeePosition;

    private static final long serialVersionUID = 1L;
}