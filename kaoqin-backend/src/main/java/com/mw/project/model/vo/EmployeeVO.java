package com.mw.project.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mw.project.model.entity.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子视图
 *
 * @author yupi
 * @TableName product
 */
@Data
public class EmployeeVO implements Serializable {
    private Long id;


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

    @TableField(exist = false)
    private Integer employeeCount;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}