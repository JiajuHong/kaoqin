-- 创建库
create database if not exists mawei;

-- 切换库
use mawei;


-- 部门表
create table if not exists department
(
    id             bigint auto_increment comment 'id' primary key,
    departmentName varchar(256)                       null comment '部门',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete       tinyint  default 0                 not null comment '是否删除'
) comment '部门表';

-- 员工表
create table if not exists employee
(
    id               bigint auto_increment comment 'id' primary key,
    departmentId     bigint                                 null comment '部门 id',
    employeeName     varchar(256)                           null comment '员工名',
    employeeAccount  varchar(256)                           not null comment '员工账号',
    employeeAvatar   varchar(1024)                          null comment '员工头像',
    gender           tinyint                                null comment '性别',
    employeePosition varchar(256)                           null comment '职位',
    employeeRole     varchar(256) default 'user'            not null comment '员工角色：user / admin',
    employeePassword varchar(512)                           not null comment '密码',
    createTime       datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime       datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete         tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount unique (employeeAccount),
    constraint fk_department foreign key (departmentId) references department (id)
) comment '员工表';

-- 用户表
create table if not exists user
(
    id              bigint auto_increment comment 'id' primary key,
    employeeAccount varchar(256)                           not null comment '员工账号',
    userName        varchar(256)                           null comment '用户昵称',
    userAccount     varchar(256)                           not null comment '账号',
    userAvatar      varchar(1024)                          null comment '用户头像',
    gender          tinyint                                null comment '性别',
    userRole        varchar(256) default 'user'            not null comment '用户角色：user / admin',
    userPassword    varchar(512)                           not null comment '密码',
    createTime      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime      datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete        tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount unique (userAccount),
    constraint fk_employee foreign key (employeeAccount) references employee (employeeAccount)
) comment '用户';

-- 考勤表
create table if not exists attendance
(
    id             bigint auto_increment comment 'id' primary key,
    employeeId     bigint                             not null comment '员工 id',
    attendanceDate date                               not null comment '考勤日期',
    attendanceType varchar(256)                       not null comment '考勤类型（正常、迟到、缺勤、请假）',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete       tinyint  default 0                 not null comment '是否删除'
) comment '考勤表';