package com.mw.project.model.dto.employee;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户创建请求
 *
 * @author yupi
 */
@Data
public class EmployeeAddRequest implements Serializable {

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