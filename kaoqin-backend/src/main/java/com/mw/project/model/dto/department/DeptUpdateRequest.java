package com.mw.project.model.dto.department;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 *
 * @TableName product
 */
@Data
public class DeptUpdateRequest implements Serializable {

    /**
     * id
     */
    private long id;

    /**
     * 部门
     */
    private String departmentName;

    private static final long serialVersionUID = 1L;
}