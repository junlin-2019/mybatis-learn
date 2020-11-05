DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `userAddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `userAge` int(10) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `age_name`(`sex`, `userName`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
INSERT INTO `user` VALUES (1, 'jim', 1, '大兴', 18);
INSERT INTO `user` VALUES (2, '张三', 2, '北京', 19);
INSERT INTO `user` VALUES (3, '李四', 1, '深圳', 20);
INSERT INTO `user` VALUES (4, '王五', 2, '广州', 21);
INSERT INTO `user` VALUES (5, '王二小', 1, '海淀', 22);
INSERT INTO `user` VALUES (6, 'james', 2, '丰台', 23);
INSERT INTO `user` VALUES (88, '小虎', 1, '北京', 24);