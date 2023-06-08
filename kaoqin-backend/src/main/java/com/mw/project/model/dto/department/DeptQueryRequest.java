package com.mw.project.model.dto.department;

import com.mw.project.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询请求
 *
 * @author yupi
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DeptQueryRequest extends PageRequest implements Serializable {

    /**
     * 部门
     */
    private String departmentName;

    private static final long serialVersionUID = 1L;
}