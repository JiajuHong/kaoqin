package com.mw.project.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 帖子视图
 *
 * @author yupi
 * @TableName product
 */
@Data
public class EmployeeChartVO implements Serializable {



    private String department;

    private Integer employeeCount;

    private static final long serialVersionUID = 1L;
}