/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : 192.168.1.75:3306
 Source Schema         : moyu

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 20/05/2019 14:27:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for moyu_house_info
-- ----------------------------
DROP TABLE IF EXISTS `moyu_house_info`;
CREATE TABLE `moyu_house_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `house_title` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `house_ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '低楼层(共33层) | 2011年建 | 3室2厅 | 85.86平米 | 南 \r\n\r\n\r\n 高楼层(共7层) 2室2厅 | 67.73平米 | 南\r\n\r\n分割格式不同',
  `house_file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址；C:\\Users\\wishm\\Pictures\\beike\\ XXX;网络地址XXX',
  `house_sum_price` int(10) NULL DEFAULT NULL COMMENT '总价格 单位：万',
  `house_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '位置',
  `house_floor_level` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '楼层分级',
  `house_sum_level` int(10) NULL DEFAULT NULL COMMENT '总楼层',
  `house_create_time` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房屋建成时间',
  `house_layout` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房间布局，一室一厅等',
  `house_measure` decimal(12, 4) NULL DEFAULT NULL COMMENT '房屋面积',
  `house_orientation` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房屋朝向',
  `house_price` decimal(18, 4) NULL DEFAULT NULL COMMENT '单价 XXX元/平米',
  `house_star` int(10) NULL DEFAULT NULL COMMENT 'XXX人关注',
  `house_publish` int(10) NULL DEFAULT NULL COMMENT 'XXX月前发布',
  `house_taxfree` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '免税条件优惠',
  `house_subway` int(255) NULL DEFAULT NULL COMMENT '0 是 1否  ;近地铁',
  `house_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '房屋链接',
  `house_origin` int(10) NULL DEFAULT NULL COMMENT '0 贝壳 ;房屋来源',
  `is_del` int(11) NULL DEFAULT 0,
  `gtm_created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moyu_house_info
-- ----------------------------
INSERT INTO `moyu_house_info` VALUES (1, '央企开发，精装修，5.8米层高，动静分离，很舒服。', NULL, '435539c3870512-173e-422f-ad19-323e3decfcc7.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-99dc3187-9837-409f-be7b-12efeba70db0.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 125, '保利湾天地', '低楼层', 27, '2010', ' 2室2厅 ', 9685.0000, ' 南 北', 129066.0000, 124, 22, '', 1, 'https://hz.ke.com/ershoufang/19042319610100108243.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (2, '连城国际舒适两居 装修清爽 业主诚心出售', NULL, '79185810a6fa69-8620-41a4-bab6-75a87152a006.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-70ae3e94-93a0-483f-9a6c-206c8cbc128d.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 195, '复地连城国际', '低楼层', 17, '2011', ' 2室2厅 ', 8486.0000, ' 南', 22979.0000, 491, 8, '', 0, 'https://hz.ke.com/ershoufang/18122019610100112464.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (3, '新惠名苑 精装修电梯房  客厅带精装大阳台', NULL, '3906011f8f9e34-aae4-459d-9331-8736fc5280e9.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-43f8e7b7-cd85-4da0-a828-3f2a0d4fca01.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 160, '新惠名苑', '高楼层', 18, '2011', ' 2室1厅 ', 6699.0000, ' 南', 238842.0000, 146, 5, '', 1, 'https://hz.ke.com/ershoufang/18122319610100111439.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (4, '金都夏宫，简装修，小区环境优雅，诚心出售看房方便', NULL, '635187b0337c31-36fd-43a8-b69c-89744f5c7079.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-46e05b53-525c-4a98-884e-0a96ab6aaf62.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 180, '金都夏宫', '低楼层', 40, '2014', ' 3室2厅 ', 8979.0000, ' 南', 200468.0000, 101, 3, '', 1, 'https://hz.ke.com/ershoufang/19030519610100102562.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (5, '如诗如画的小区环境让你住进去后就不想搬家了', '高楼层(共33层) 2室2厅 | 80.63平米 | 南', '966394143fe5d4-c2b4-46e1-a086-1c6cc0864315.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-f49c8441-91d8-4780-8214-7d2fd6c16578.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 136, '越秀星汇城', NULL, NULL, NULL, NULL, NULL, NULL, 168672.0000, 35, 27, '', 1, 'https://hz.ke.com/ershoufang/19041619610100115258.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (6, '朝晖七区 南北通透 单价低 近地铁 视野开阔', NULL, '70002931623b5e-a65c-442a-8337-f2a63eb1a52d.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-422c6e92-3d2a-4a45-9fd0-b6c81a7e0105.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 188, '朝晖七区', '高楼层', 7, '1986', ' 2室1厅 ', 5366.0000, ' 南 北', 350354.0000, 550, 6, '', 0, 'https://hz.ke.com/ershoufang/18120619610100112118.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (7, '梅堰简装两房，楼梯房，低楼层，房东急卖', NULL, '7234989b2fe9c8-1572-4047-a044-cfccbfa28052.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-8a23572d-af9b-453e-819b-dce4caa26f28.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 126, '梅堰小区', '低楼层', 6, '2000', ' 2室1厅 ', 6383.0000, ' 南', 197399.0000, 69, 29, '', 1, 'https://hz.ke.com/ershoufang/19042219610100139673.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (8, '业主可以满两年过户，采光好，视野佳', '高楼层(共7层) 2室2厅 | 67.73平米 | 南', '7943624d2ed8f7-e451-41b2-9271-da0288244aff.jpg;https://ke-image.ljcdn.com/330100-inspection/test-59b591a4-d0a6-4228-a009-47fe74e7272c.png!m_fill,w_280,h_210,f_jpg?from=ke.com', 135, '棉百弄', NULL, NULL, NULL, NULL, NULL, NULL, 199321.0000, 116, 3, '', 1, 'https://hz.ke.com/ershoufang/18120519610102959523.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (9, '潘水苑 三房户型 业主诚心出售 看房有钥匙', NULL, '426122a62519c4-8d9e-4e41-99cc-eeb994872b1b.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-2c4e0827-698e-4519-b908-e5b55a4c05b3.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 215, '潘水苑', '高楼层', 6, '2005', ' 3室2厅 ', 852.0000, ' 南', 252347.0000, 97, 2, '', 0, 'https://hz.ke.com/ershoufang/19041019610100100999.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (10, '盛世嘉园上下两层LOFT  空间够大  舒适', NULL, '72214945873bd6-3c49-4b65-8f4f-ec03d1524b7d.jpg;https://ke-image.ljcdn.com/330100-inspection/test-2563acf1-fa1d-4d53-a843-0b863e61784b.png!m_fill,w_280,h_210,f_jpg?from=ke.com', 140, '盛世嘉园', '高楼层', 18, '2006', ' 1室1厅 ', 6699.0000, ' 北', 208986.0000, 200, 9, '', 1, 'https://hz.ke.com/ershoufang/18120519610100171966.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (11, '70年产权loft住宅  满五唯一 精装修 高楼层 视野好', NULL, '6606638ab39b2d-524b-40fb-8b71-a8824a644ac9.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-57e29945-2b97-42f9-acfa-55013a866eeb.png!m_fill,w_280,h_210,f_jpg?from=ke.com', 157, '盛世嘉园', '高楼层', 18, '2006', ' 2室1厅 ', 6797.0000, ' 南', 230984.0000, 112, 7, '', 1, 'https://hz.ke.com/ershoufang/18120519610100497250.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (12, '新小区，毛坯小三房，户型正，看房方便，房东诚售', NULL, '689026ee0fb7fd-26fe-4be8-a20c-c03e494db8b4.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-8d158721-0c1f-45bb-b026-1d27b7453460.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 180, '众安学君里', '低楼层', 24, '2018', ' 3室1厅 ', 8977.0000, ' 南', 200512.0000, 59, 25, '', 1, 'https://hz.ke.com/ershoufang/19041919610100118223.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (13, '满两年，楼层好，视野开阔，业主诚心出售', NULL, '627435fb2ddc8f-ba88-46b8-99c6-aa03f0e3c8f7.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-edce70b5-1854-485c-8998-d684626b8cd1.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 219, '世茂江滨花园瑞景湾', '低楼层', 33, '2010', ' 3室1厅 ', 8878.0000, ' 南', 246677.0000, 63, 2, '', 0, 'https://hz.ke.com/ershoufang/19033019610100120320.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (14, '诚心出售，价格还能谈，纯毛坯，采光充足', NULL, '5391576b0f1227-33e2-476c-98d2-7181567aa125.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-111afd1b-fec6-4d16-96b0-659c96d7b0ca.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 199, '钱塘梧桐蓝山', '高楼层', 24, '2011', ' 3室2厅 ', 8927.0000, ' 南', 222919.0000, 28, 2, '', 1, 'https://hz.ke.com/ershoufang/19041019610100115999.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (15, '都市阳光和苑，两房，精装修一年时间，高楼层', '中楼层(共28层) 2室1厅 | 62.14平米 | 南', '141488c35cc570-f43d-435d-8b88-c63727c4a2d5.jpg;https://ke-image.ljcdn.com/330100-inspection/test-4f0afb5d-b73b-4603-9b0a-44fb93a1ef00.png!m_fill,w_280,h_210,f_jpg?from=ke.com', 175, '都市阳光和苑', NULL, NULL, NULL, NULL, NULL, NULL, 281622.0000, 343, 5, '', 1, 'https://hz.ke.com/ershoufang/18120519610101322754.html', 0, 0, '2019-05-13 08:49:48');
INSERT INTO `moyu_house_info` VALUES (16, '万科七贤郡，两房朝南户型，精装修，视野开阔。', NULL, '644731649c7dd5-9b26-49b8-96aa-21d9c31cd7db.jpg;https://ke-image.ljcdn.com/330100-inspection/ff4cae54-1566-4a08-978a-7b9699b0cb3f.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 195, '良渚文化村七贤郡', '高楼层', 6, '2014', ' 2室2厅 ', 7283.0000, ' 南', 267747.0000, 111, 10, '', 1, 'https://hz.ke.com/ershoufang/18120519610102224870.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (17, '此房满五年唯一住房 省15万税 精装自住 客厅带阳台', '低楼层(共11层) 2室2厅 | 89平米 | 南 北', '504229e6577637-67d5-499c-88c1-989e22ebe686.jpg;https://ke-image.ljcdn.com/330100-inspection/98cd0ed9-b1bc-46e5-91d8-38342b0cf70b.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 260, '万科魅力之城', NULL, NULL, NULL, NULL, NULL, NULL, 292135.0000, 316, 2, '', 1, 'https://hz.ke.com/ershoufang/18120519610103964712.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (18, '小区新，房子未装修，适合小年轻居住。', '中楼层(共24层) 1室1厅 | 54.64平米 | 西', '228817cc1a4d93-54b1-44d0-87fc-29a8e6952df6.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-fd48645d-32cc-4962-abad-48be8c1937ef.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 78, '大园新城南区', NULL, NULL, NULL, NULL, NULL, NULL, 142753.0000, 57, 2, '', 1, 'https://hz.ke.com/ershoufang/19032119610100113863.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (19, '满五年，户型方正，低楼层，适合有老人小孩居住', '低楼层(共6层) 2室1厅 | 58.74平米 | 南', '409476789bddbf-5776-46be-80e6-eb6095179f10.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-4562fbae-31c0-472e-a6f2-449adb127a1e.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 122, '荷花小区', NULL, NULL, NULL, NULL, NULL, NULL, 207695.0000, 21, 18, '', 1, 'https://hz.ke.com/ershoufang/19050119610100386148.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (20, '翠苑好房  采光通风性好 免综合税 看房方便诚心出售', NULL, '875862d6f6d208-8522-4fed-96bc-f7c74c1e8570.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-df0de393-05e2-4e37-8ea8-2f1a44e5376c.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 259, '翠苑二区北区', '高楼层', 7, '1996', ' 3室1厅 ', 7196.0000, ' 南 北', 359922.0000, 173, 3, '', 0, 'https://hz.ke.com/ershoufang/19030319610100119163.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (21, '西湖区 金地自在城 开发商精装 业主诚心出售', NULL, '1929985c4aa91a-c1c9-4528-b8ed-bbc38bf46139.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-1993e473-7ce0-41c4-926c-c2d07e9db9b0.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 275, '金地自在城', '高楼层', 25, '2012', ' 2室1厅 ', 7939.0000, ' 南', 346391.0000, 51, 2, '', 0, 'https://hz.ke.com/ershoufang/19042319610100112549.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (22, '钱江世纪城，70年住宅，振宁路地铁口，自住精装修！', '中楼层(共6层) 3室1厅 | 109.04平米 | 南', '8869190753f382-b3c7-4701-9725-9aae0e5e6eb6.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-6f69e487-eb60-4b50-a511-5257abc908c0.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 292, '幸福时代公寓', NULL, NULL, NULL, NULL, NULL, NULL, 267792.0000, 91, 2, '', 0, 'https://hz.ke.com/ershoufang/19031119610100106710.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (23, '次新小区，滨江打造的高品质小区，户型方正，配套齐全', NULL, '542305d9b2a348-f06f-4a5b-9e41-50a8bed9323f.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-3a66e635-0434-4ddc-bc9a-7e340f0d5c39.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 338, '滨江江南之星', '低楼层', 34, '2018', ' 3室2厅 ', 8786.0000, ' 南', 384703.0000, 55, 4, '', 1, 'https://hz.ke.com/ershoufang/19021519610100110412.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (24, '华元十六街区 44方 中间楼层  朝南 户型方正', NULL, '897796109a60de-efa5-440d-9518-65e8a373c83c.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-28f67744-6c52-4099-bc9a-d75653a92c9c.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 55, '华元十六街区', '中楼层', 16, '2004', ' 1室1厅 ', 441.0000, ' 北', 124717.0000, 26, 2, '', 0, 'https://hz.ke.com/ershoufang/19032619610100112330.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (25, '德胜东村，满五年，自住装修，唯一一套。', NULL, '1488741da9420a-cc3a-482c-8ac8-f429af2ecec0.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-8f5c6a59-9242-4077-87fa-6a6ad0afe3ba.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 180, '德胜东村', '高楼层', 7, '1993', ' 2室1厅 ', 5339.0000, ' 南', 337142.0000, 155, 6, '', 1, 'https://hz.ke.com/ershoufang/18120619610100108618.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (26, '青山鹤岭满二年的房子厅带阳台，主卧朝南，低总价', '低楼层(共11层) 2室2厅 | 71.04平米 | 南', '205149dd171b6e-983e-4579-96af-774709317cfb.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-3cb2617c-cab5-4924-9427-1571bc7a8c4e.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 112, '青山鹤岭', NULL, NULL, NULL, NULL, NULL, NULL, 157658.0000, 27, 2, '', 1, 'https://hz.ke.com/ershoufang/19041719610100114718.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (27, '霞飞郡精装公寓 民水民电通燃气 可拎包入住', NULL, '475679b14aa8f8-c600-4d08-974e-4e89a5f2058a.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-ad8858e6-ff4a-4ed3-9ae1-da68bc491d88.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 95, '保利霞飞郡', '中楼层', 7, '2013', ' 1室1厅 ', 4733.0000, ' 北', 200718.0000, 53, 9, '', 1, 'https://hz.ke.com/ershoufang/18120519610101744956.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (28, '怡丰城！有自行浇筑面积，一梯一户', '中楼层(共18层) 3室2厅 | 83.2平米 | 南', '346989cd7252e4-4cfe-43a1-ac01-bf3b5cf481ac.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-8e17ae02-0a19-46f1-9e2d-be48aab010ae.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 190, '东田怡丰城', NULL, NULL, NULL, NULL, NULL, NULL, 228365.0000, 77, 5, '', 1, 'https://hz.ke.com/ershoufang/19010319610100115484.html', 0, 0, '2019-05-13 08:49:49');
INSERT INTO `moyu_house_info` VALUES (29, '此房产证满两年，楼层好，视野好，采光好。', NULL, '94418924601ca6-e386-4fe1-a920-75589306fee7.jpg;https://ke-image.ljcdn.com/330100-inspection/prod-7141f253-3536-4a51-b737-b419aa39c0e7.jpg!m_fill,w_280,h_210,f_jpg?from=ke.com', 280, '宋都晨光国际', '高楼层', 31, '2013', ' 3室2厅 ', 895.0000, ' 南 北', 312849.0000, 38, 2, '', 0, 'https://hz.ke.com/ershoufang/19032819610100106466.html', 0, 0, '2019-05-13 08:49:49');

-- ----------------------------
-- Table structure for moyu_menu
-- ----------------------------
DROP TABLE IF EXISTS `moyu_menu`;
CREATE TABLE `moyu_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `menu_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `menu_type` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `menu_lever` int(11) NULL DEFAULT NULL,
  `menu_icon` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` int(3) NULL DEFAULT NULL COMMENT '排序',
  `is_del` int(11) NULL DEFAULT 0,
  `gtm_created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moyu_menu
-- ----------------------------
INSERT INTO `moyu_menu` VALUES (1, 0, '系统菜单', 'main', 'sys', 'main', 0, 'el-icon-menu', 0, 0, '2019-01-24 08:35:46');
INSERT INTO `moyu_menu` VALUES (2, 1, '商品管理', 'web', 'product', NULL, 1, 'el-icon-goods', 1, 0, '2019-01-24 08:36:13');
INSERT INTO `moyu_menu` VALUES (3, 1, '系统管理', 'web', 'system', NULL, 1, 'el-icon-setting', 2, 0, '2019-01-24 08:38:51');
INSERT INTO `moyu_menu` VALUES (4, 1, '日志数据', 'web', 'log', '', 1, 'el-icon-message', 3, 0, '2019-01-24 08:39:56');
INSERT INTO `moyu_menu` VALUES (5, 2, '产品引进', 'web', 'product:bring', '/productBring', 2, 'el-icon-d-arrow-right', 4, 0, '2019-01-24 08:40:00');
INSERT INTO `moyu_menu` VALUES (6, 2, '产品阅览', 'web', 'product:read', '/productRead', 2, 'el-icon-d-arrow-right', 5, 0, '2019-01-24 08:40:50');
INSERT INTO `moyu_menu` VALUES (7, 2, '产品订购', 'web', 'product:reserve', '/productReserve', 2, 'el-icon-d-arrow-right', 6, 0, '2019-01-24 08:41:00');
INSERT INTO `moyu_menu` VALUES (8, 3, '用户管理', 'web', 'user:sys', '/userManage', 2, 'el-icon-d-arrow-right', 7, 0, '2019-01-24 08:41:09');
INSERT INTO `moyu_menu` VALUES (9, 3, '角色管理', 'web', 'role:sys', '/roleManage', 2, 'el-icon-d-arrow-right', 8, 0, '2019-01-24 08:41:12');
INSERT INTO `moyu_menu` VALUES (10, 3, '菜单管理', 'web', 'menu:sys', '/menuManage', 2, 'el-icon-d-arrow-right', 9, 0, '2019-01-24 08:41:18');
INSERT INTO `moyu_menu` VALUES (11, 3, '系统参数', 'web', 'sys:param', '/sysParam', 2, 'el-icon-d-arrow-right', 10, 0, '2019-03-16 12:59:48');
INSERT INTO `moyu_menu` VALUES (16, 1, '应用模板', '1', 'sys:applicationTemplate', '/applicationTemplate', 1, 'el-icon-news', 4, 0, '2019-04-11 03:56:19');
INSERT INTO `moyu_menu` VALUES (17, 16, '模板', '2', '', '', 2, 'el-icon-tickets', 11, 0, '2019-04-11 03:56:58');
INSERT INTO `moyu_menu` VALUES (18, 16, '策略', '2', NULL, NULL, 2, 'el-icon-d-arrow-right', NULL, 0, '2019-04-11 06:34:47');

-- ----------------------------
-- Table structure for moyu_role
-- ----------------------------
DROP TABLE IF EXISTS `moyu_role`;
CREATE TABLE `moyu_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_del` int(11) NOT NULL,
  `gtm_created` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moyu_role
-- ----------------------------
INSERT INTO `moyu_role` VALUES (8, 'admin', 'gxjTest', 0, '2019-03-17 13:42:26');
INSERT INTO `moyu_role` VALUES (9, 'test', '测试', 0, '2019-03-17 14:32:38');
INSERT INTO `moyu_role` VALUES (10, 'product', '产品', 0, '2019-03-18 01:53:18');
INSERT INTO `moyu_role` VALUES (11, 'operation', '运维', 0, '2019-03-18 01:53:41');
INSERT INTO `moyu_role` VALUES (12, 'com', '公司', 0, '2019-03-18 02:35:55');
INSERT INTO `moyu_role` VALUES (13, 'cn', '特殊', 0, '2019-03-18 02:37:33');

-- ----------------------------
-- Table structure for moyu_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `moyu_role_menu_relation`;
CREATE TABLE `moyu_role_menu_relation`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `role_id` int(8) NULL DEFAULT NULL,
  `menu_id` int(8) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moyu_role_menu_relation
-- ----------------------------
INSERT INTO `moyu_role_menu_relation` VALUES (11, 8, 1);
INSERT INTO `moyu_role_menu_relation` VALUES (12, 8, 2);
INSERT INTO `moyu_role_menu_relation` VALUES (13, 8, 3);
INSERT INTO `moyu_role_menu_relation` VALUES (15, 8, 5);
INSERT INTO `moyu_role_menu_relation` VALUES (16, 8, 6);
INSERT INTO `moyu_role_menu_relation` VALUES (17, 8, 7);
INSERT INTO `moyu_role_menu_relation` VALUES (18, 8, 8);
INSERT INTO `moyu_role_menu_relation` VALUES (19, 8, 9);
INSERT INTO `moyu_role_menu_relation` VALUES (20, 8, 10);
INSERT INTO `moyu_role_menu_relation` VALUES (22, 8, 4);
INSERT INTO `moyu_role_menu_relation` VALUES (23, 8, 11);
INSERT INTO `moyu_role_menu_relation` VALUES (24, 8, 16);
INSERT INTO `moyu_role_menu_relation` VALUES (25, 8, 17);
INSERT INTO `moyu_role_menu_relation` VALUES (26, 10, 4);
INSERT INTO `moyu_role_menu_relation` VALUES (27, 10, 5);
INSERT INTO `moyu_role_menu_relation` VALUES (28, 10, 6);
INSERT INTO `moyu_role_menu_relation` VALUES (29, 9, 3);
INSERT INTO `moyu_role_menu_relation` VALUES (30, 9, 8);
INSERT INTO `moyu_role_menu_relation` VALUES (31, 9, 9);

-- ----------------------------
-- Table structure for moyu_user
-- ----------------------------
DROP TABLE IF EXISTS `moyu_user`;
CREATE TABLE `moyu_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `real_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone_no` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lock_account` int(11) NULL DEFAULT NULL COMMENT '锁定次数',
  `salt` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '盐',
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  `state` int(11) NULL DEFAULT NULL,
  `is_del` int(11) NULL DEFAULT 0,
  `gtm_created` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_created` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moyu_user
-- ----------------------------
INSERT INTO `moyu_user` VALUES (1, 'moyu', '123', '郭子', '13735417360', 0, '1', '杭州市', 10, 0, '2019-01-24 07:13:01', NULL);
INSERT INTO `moyu_user` VALUES (26, '大白', '123', '竟成了', '13', NULL, NULL, '和政治', 10, 0, '2019-03-17 14:52:20', NULL);
INSERT INTO `moyu_user` VALUES (27, 'mo无影yu', '123', '123', '31', NULL, NULL, '31', 20, 0, '2019-03-17 14:53:32', NULL);

-- ----------------------------
-- Table structure for moyu_user_equipment
-- ----------------------------
DROP TABLE IF EXISTS `moyu_user_equipment`;
CREATE TABLE `moyu_user_equipment`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户设备信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for moyu_user_login
-- ----------------------------
DROP TABLE IF EXISTS `moyu_user_login`;
CREATE TABLE `moyu_user_login`  (
  `id` int(11) NOT NULL,
  `user_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moyu_user_login
-- ----------------------------
INSERT INTO `moyu_user_login` VALUES (1, 1);

-- ----------------------------
-- Table structure for moyu_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `moyu_user_role_relation`;
CREATE TABLE `moyu_user_role_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户权限关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of moyu_user_role_relation
-- ----------------------------
INSERT INTO `moyu_user_role_relation` VALUES (7, 1, 8);
INSERT INTO `moyu_user_role_relation` VALUES (13, 26, 9);

SET FOREIGN_KEY_CHECKS = 1;
