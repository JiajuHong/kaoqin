package com.mw.project.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mw.project.common.ErrorCode;
import com.mw.project.exception.BusinessException;
import com.mw.project.model.entity.Attendance;
import com.mw.project.service.AttendanceService;
import com.mw.project.mapper.AttendanceMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author jiaju
* @description 针对表【attendance(考勤表)】的数据库操作Service实现
* @createDate 2023-04-20 04:09:22
*/
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance>
    implements AttendanceService{

    @Override
    public void validAttendance(Attendance attendance, boolean add) {
        if (attendance == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }

        String employeeAccount = attendance.getEmployeeAccount();
        Date attendanceDate = attendance.getAttendanceDate();
        String attendanceType = attendance.getAttendanceType();
        String name = attendance.getName();

        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(employeeAccount,attendanceType, name) || attendanceDate == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "所有参数都是必填项");
            }
        }
    }
}




