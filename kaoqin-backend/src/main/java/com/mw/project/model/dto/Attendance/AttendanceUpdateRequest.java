package com.mw.project.model.dto.Attendance;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 更新请求
 *
 * @TableName product
 */
@Data
public class AttendanceUpdateRequest implements Serializable {

    /**
     * id
     */
    private long id;

    /**
     * 员工账号
     */
    private String employeeAccount;
    private String name;

    /**
     * 考勤日期
     */
    private Date attendanceDate;

    /**
     * 考勤类型（正常、迟到、缺勤、请假）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String attendanceType;

    private static final long serialVersionUID = 1L;
}