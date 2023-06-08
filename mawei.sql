/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : mawei

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 08/05/2023 00:53:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `employeeAccount` varchar(256) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '员工账号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `attendanceDate` date NOT NULL COMMENT '考勤日期',
  `attendanceType` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '考勤类型（正常、迟到、缺勤、请假）',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '考勤表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES (1, '3013203129', '马伟', '2023-04-19', '请假', '2023-04-20 04:08:30', '2023-04-20 14:47:21', 1);
INSERT INTO `attendance` VALUES (6, '3013203129', '马伟', '2023-04-06', '迟到', '2023-04-20 05:22:06', '2023-04-20 07:17:53', 0);
INSERT INTO `attendance` VALUES (7, '3013203129', '马伟', '2023-04-05', '迟到', '2023-04-20 05:23:43', '2023-04-20 12:44:03', 0);
INSERT INTO `attendance` VALUES (8, '3013203129', '马伟', '2023-04-29', '正常', '2023-04-20 06:12:15', '2023-04-20 06:12:15', 0);
INSERT INTO `attendance` VALUES (9, '1111111111111', '张三', '2023-04-11', '正常', '2023-04-20 06:33:44', '2023-04-20 06:33:44', 0);
INSERT INTO `attendance` VALUES (16, '3013203129', '马伟', '2023-04-05', '正常', '2023-04-21 12:53:12', '2023-04-21 12:53:12', 0);
INSERT INTO `attendance` VALUES (17, '3013203129', '马伟', '2023-04-05', '正常', '2023-04-21 12:53:23', '2023-04-21 12:53:23', 0);
INSERT INTO `attendance` VALUES (18, '3013203129', '马伟', '2023-04-05', '正常', '2023-04-21 12:53:25', '2023-04-21 12:53:25', 0);
INSERT INTO `attendance` VALUES (19, '3013203119', '李绍强', '2023-05-01', '正常', '2023-05-01 20:07:56', '2023-05-01 20:07:56', 0);
INSERT INTO `attendance` VALUES (20, '3013203119', '李绍强', '2023-05-01', '迟到', '2023-05-01 22:18:46', '2023-05-01 22:18:46', 0);
INSERT INTO `attendance` VALUES (21, '3013203132', '小黑子', '2023-05-01', '迟到', '2023-05-01 22:19:03', '2023-05-01 22:19:03', 0);
INSERT INTO `attendance` VALUES (22, '3013203131', '真爱粉', '2023-05-01', '正常', '2023-05-01 22:19:21', '2023-05-01 22:19:21', 0);
INSERT INTO `attendance` VALUES (23, '3013203130', '淳鹿人', '2023-05-01', '正常', '2023-05-01 22:19:33', '2023-05-01 22:19:33', 0);
INSERT INTO `attendance` VALUES (24, '3013203133', '真ikun', '2023-05-01', '正常', '2023-05-01 22:19:49', '2023-05-01 22:19:49', 0);
INSERT INTO `attendance` VALUES (25, '3013203134', '甄德士尼亚', '2023-05-01', '正常', '2023-05-01 22:20:05', '2023-05-01 22:20:05', 0);
INSERT INTO `attendance` VALUES (26, '3013203135', '油饼', '2023-05-01', '正常', '2023-05-01 22:20:19', '2023-05-01 22:20:19', 0);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `departmentName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '研发部', '2023-04-20 02:05:35', '2023-04-20 02:32:47', 0);
INSERT INTO `department` VALUES (3, '销售部', '2023-04-20 03:24:54', '2023-04-20 03:24:54', 0);
INSERT INTO `department` VALUES (4, '售后部', '2023-04-20 17:05:35', '2023-04-20 17:05:35', 0);
INSERT INTO `department` VALUES (5, '董事会', '2023-04-20 17:05:52', '2023-04-20 17:05:52', 0);
INSERT INTO `department` VALUES (6, '总裁办', '2023-04-20 17:05:58', '2023-04-20 17:05:58', 0);
INSERT INTO `department` VALUES (7, '零售部', '2023-04-20 17:06:05', '2023-04-20 17:06:12', 1);
INSERT INTO `department` VALUES (8, '网商部', '2023-04-20 17:06:26', '2023-04-20 17:06:26', 0);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `departmentId` bigint(20) NULL DEFAULT NULL COMMENT '部门 id',
  `employeeName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工名',
  `employeeAccount` varchar(256) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '员工账号',
  `employeeAvatar` varchar(1024) CHARACTER SET latin1 COLLATE latin1_german2_ci NULL DEFAULT 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202303201452868.webp' COMMENT '员工头像',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `employeePosition` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职位',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_userAccount`(`employeeAccount`) USING BTREE,
  INDEX `fk_dept`(`departmentId`) USING BTREE,
  CONSTRAINT `fk_dept` FOREIGN KEY (`departmentId`) REFERENCES `department` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 5, '马伟', '3013203129', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202303201448801.webp', 0, '董事长', '2023-04-20 02:06:07', '2023-04-20 17:17:54', 0);
INSERT INTO `employee` VALUES (2, 3, '张三', '1111111111111', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202303201452868.webp', 0, '销售', '2023-04-20 03:25:29', '2023-04-20 04:00:36', 1);
INSERT INTO `employee` VALUES (3, 1, '11', '111', '1', 0, '1', '2023-04-20 04:01:38', '2023-04-20 04:01:40', 1);
INSERT INTO `employee` VALUES (4, 5, '李绍强', '3013203119', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304161412627.webp', 0, '副董事长', '2023-04-20 07:00:14', '2023-04-20 17:18:23', 0);
INSERT INTO `employee` VALUES (5, 3, '1111', '111111', '1111', 1, '1', '2023-04-20 12:44:56', '2023-04-20 12:45:09', 1);
INSERT INTO `employee` VALUES (6, 3, '蔡徐坤1', '1234', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304161559858.webp', 1, '销售', '2023-04-20 14:45:05', '2023-04-20 14:45:22', 1);
INSERT INTO `employee` VALUES (7, 3, '淳鹿人', '3013203130', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304201709485.webp', 1, '销售', '2023-04-20 17:10:20', '2023-04-20 17:10:20', 0);
INSERT INTO `employee` VALUES (8, 4, '真爱粉', '3013203131', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304201711728.webp', 0, '售后工程师', '2023-04-20 17:11:47', '2023-04-20 17:11:47', 0);
INSERT INTO `employee` VALUES (9, 3, '小黑子', '3013203132', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304201712370.webp', 0, '销售', '2023-04-20 17:13:05', '2023-04-20 17:13:05', 0);
INSERT INTO `employee` VALUES (10, 4, '真ikun', '3013203133', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304201713320.webp', 0, '售后经理', '2023-04-20 17:14:03', '2023-04-20 17:14:03', 0);
INSERT INTO `employee` VALUES (11, 1, '甄德士尼亚', '3013203134', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304201718008.webp', 0, '后端开发', '2023-04-20 17:19:26', '2023-04-20 17:19:26', 0);
INSERT INTO `employee` VALUES (12, 1, '油饼', '3013203135', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304201719081.webp', 0, '后端开发', '2023-04-20 17:20:08', '2023-04-20 17:20:08', 0);
INSERT INTO `employee` VALUES (13, 8, '香精煎鱼', '3013203136', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304201720310.webp', 0, '保安', '2023-04-20 17:20:59', '2023-04-20 17:20:59', 0);
INSERT INTO `employee` VALUES (14, 6, '香翅捞饭', '3013203137', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304201721942.webp', 0, '总裁', '2023-04-20 17:22:01', '2023-04-20 17:22:01', 0);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(4) NOT NULL DEFAULT 0 COMMENT '性别（0-男, 1-女）',
  `education` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '学历',
  `place` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '地点',
  `job` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '职业',
  `contact` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '联系方式',
  `loveExp` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '感情经历',
  `content` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL COMMENT '内容（个人介绍）',
  `photo` varchar(1024) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '照片地址',
  `reviewStatus` int(11) NOT NULL DEFAULT 0 COMMENT '状态（0-待审核, 1-通过, 2-拒绝）',
  `reviewMessage` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '审核信息',
  `viewNum` int(11) NOT NULL DEFAULT 0 COMMENT '浏览数',
  `thumbNum` int(11) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `userId` bigint(20) NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '帖子' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `employeeAccount` varchar(256) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '员工账号',
  `userName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `userAccount` varchar(256) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '账号',
  `userAvatar` varchar(1024) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202303201452868.webp' COMMENT '用户头像',
  `gender` tinyint(4) NULL DEFAULT NULL COMMENT '性别',
  `userRole` varchar(256) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user / admin',
  `userPassword` varchar(512) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '密码',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_userAccount`(`userAccount`) USING BTREE,
  INDEX `fk_employee`(`employeeAccount`) USING BTREE,
  CONSTRAINT `fk_employee` FOREIGN KEY (`employeeAccount`) REFERENCES `employee` (`employeeAccount`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '3013203129', '马伟', 'mawei', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202303201448801.webp', 0, 'admin', 'd5cc37b6d932fe85702eea37965e2407', '2023-04-20 02:08:40', '2023-04-20 02:48:44', 0);
INSERT INTO `user` VALUES (2, '3013203119', '李绍强', 'lishaoqiang', 'https://tuchuang-1310600455.cos.ap-nanjing.myqcloud.com/jiaju/202304161412627.webp', 0, 'user', 'd5cc37b6d932fe85702eea37965e2407', '2023-04-20 07:00:50', '2023-04-20 17:25:16', 0);

SET FOREIGN_KEY_CHECKS = 1;
