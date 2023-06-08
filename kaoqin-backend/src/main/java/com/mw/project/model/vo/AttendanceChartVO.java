package com.mw.project.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 帖子视图
 *
 * @author yupi
 * @TableName product
 */
@Data
public class AttendanceChartVO implements Serializable {



    /**
     * 考勤类型（正常、迟到、缺勤、请假）
     */
    private String attendanceType;

    /**
     * 考勤次数
     */

    private Integer attendanceCount;

    private static final long serialVersionUID = 1L;
}