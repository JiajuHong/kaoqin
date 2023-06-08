package com.mw.project.model.dto.Attendance;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建请求
 *
 * @TableName product
 */
@Data
public class AttendanceAddRequest implements Serializable {

    /**
     * 员工账号
     */
    private String employeeAccount;

    private String name;

    /**
     * 考勤日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date attendanceDate;

    /**
     * 考勤类型（正常、迟到、缺勤、请假）
     */
    private String attendanceType;

    private static final long serialVersionUID = 1L;
}