package com.mw.project.service;

import com.mw.project.model.entity.Attendance;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author jiaju
* @description 针对表【attendance(考勤表)】的数据库操作Service
* @createDate 2023-04-20 04:09:22
*/
public interface AttendanceService extends IService<Attendance> {

    void validAttendance(Attendance attendance, boolean add);
}
