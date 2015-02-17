/*
Navicat MySQL Data Transfer

Source Server         : CrowdService
Source Server Version : 50618
Source Host           : localhost:3306
Source Database       : crowdservice

Target Server Type    : MYSQL
Target Server Version : 50618
File Encoding         : 65001

Date: 2015-02-03 16:38:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for agentinfo
-- ----------------------------
DROP TABLE IF EXISTS `agentinfo`;
CREATE TABLE `agentinfo` (
`guid`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`isOnline`  bit(1) NOT NULL ,
`longitude`  decimal(64,8) NULL DEFAULT 0.00000000 ,
`lastActive`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,
`capacity`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`reputation`  decimal(14,2) NOT NULL DEFAULT 100.00 ,
`latitude`  decimal(64,8) NULL DEFAULT 0.00000000 ,
PRIMARY KEY (`guid`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of agentinfo
-- ----------------------------
BEGIN;
INSERT INTO `agentinfo` VALUES ('an', '\0', '121.59529288', '2015-01-29 17:12:04', 'fghh', '100.00', '31.19365456'), ('sanxing', '\0', '121.59528572', '2015-01-29 22:41:11', 'ryijn', '100.00', '31.19408536'), ('xiao', '\0', '121.59535300', '2015-01-29 20:25:10', 'coding', '100.00', '31.19400400');
COMMIT;

-- ----------------------------
-- Table structure for microtask
-- ----------------------------
DROP TABLE IF EXISTS `microtask`;
CREATE TABLE `microtask` (
`id`  bigint(64) NOT NULL AUTO_INCREMENT ,
`template`  text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`consumer`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`cost`  int(11) NULL DEFAULT 0 ,
`state`  bit(3) NOT NULL DEFAULT b'0' ,
`deadline`  int(32) NULL DEFAULT NULL ,
`createTime`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`compositeService`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`crowdService`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`consumer`) REFERENCES `agentinfo` (`guid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK4` (`consumer`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=183

;

-- ----------------------------
-- Records of microtask
-- ----------------------------
BEGIN;
INSERT INTO `microtask` VALUES ('172', '<Root><Description>Please get to the designated location and inspect the second-hand computer specified below. Check it and evaluate whether it is consistent with its description given below. Take a picture of computer and upload the picture.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><TextDisplay><Key>Seller Address</Key><Value>#1159 Cailun Road</Value></TextDisplay><ImageInput><Key>Photo of the Second-hand Computer</Key><Value></Value></ImageInput><ChoiceInput><Key>Please Choose Consistent Degree</Key><Value>Very Consistent,Consistent,Not Consistent</Value></ChoiceInput></Root>', 'sanxing', '60', '', '150', '2015-01-29 17:03:26', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.siteinspection.InspectSiteService'), ('173', '<Root><Description>Please accesss the price of the given second-hand computer configuration based on the photo given.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><ImageDisplay><Key>Photo of the Second-hand Computer</Key><Value>1422522399570.jpg</Value></ImageDisplay><TextInput><Key>the Price of this computer You Assess</Key><Value/></TextInput></Root>', 'sanxing', '60', '', '150', '2015-01-29 17:06:40', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.priceassessment.AssessPriceService'), ('174', '<Root><Description>Please get to the designated location and inspect the second-hand computer specified below. Check it and evaluate whether it is consistent with its description given below. Take a picture of computer and upload the picture.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><TextDisplay><Key>Seller Address</Key><Value>#1159 Cailun Road</Value></TextDisplay><ImageInput><Key>Photo of the Second-hand Computer</Key><Value></Value></ImageInput><ChoiceInput><Key>Please Choose Consistent Degree</Key><Value>Very Consistent,Consistent,Not Consistent</Value></ChoiceInput></Root>', 'sanxing', '3', '', '36', '2015-01-29 20:12:51', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.siteinspection.InspectSiteService'), ('175', '<Root><Description>Please get to the designated location and inspect the second-hand computer specified below. Check it and evaluate whether it is consistent with its description given below. Take a picture of computer and upload the picture.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><TextDisplay><Key>Seller Address</Key><Value>#1159 Cailun Road</Value></TextDisplay><ImageInput><Key>Photo of the Second-hand Computer</Key><Value></Value></ImageInput><ChoiceInput><Key>Please Choose Consistent Degree</Key><Value>Very Consistent,Consistent,Not Consistent</Value></ChoiceInput></Root>', 'sanxing', '6', '', '60', '2015-01-29 20:15:11', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.siteinspection.InspectSiteService'), ('176', '<Root><Description>Please accesss the price of the given second-hand computer configuration based on the photo given.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><ImageDisplay><Key>Photo of the Second-hand Computer</Key><Value>1422533821567.jpg</Value></ImageDisplay><TextInput><Key>the Price of this computer You Assess</Key><Value/></TextInput></Root>', 'sanxing', '6', '', '60', '2015-01-29 20:17:01', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.priceassessment.AssessPriceService'), ('177', '<Root><Description>Please get to the designated location and inspect the second-hand computer specified below. Check it and evaluate whether it is consistent with its description given below. Take a picture of computer and upload the picture.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><TextDisplay><Key>Seller Address</Key><Value>#1159 Cailun Road</Value></TextDisplay><ImageInput><Key>Photo of the Second-hand Computer</Key><Value></Value></ImageInput><ChoiceInput><Key>Please Choose Consistent Degree</Key><Value>Very Consistent,Consistent,Not Consistent</Value></ChoiceInput></Root>', 'sanxing', '6', '', '36', '2015-01-29 20:18:00', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.siteinspection.InspectSiteService'), ('178', '<Root><Description>Please accesss the price of the given second-hand computer configuration based on the photo given.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><ImageDisplay><Key>Photo of the Second-hand Computer</Key><Value>1422533921191.jpg</Value></ImageDisplay><TextInput><Key>the Price of this computer You Assess</Key><Value/></TextInput></Root>', 'sanxing', '6', '', '36', '2015-01-29 20:18:41', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.priceassessment.AssessPriceService'), ('179', '<Root><Description>Please get to the designated location and inspect the second-hand computer specified below. Check it and evaluate whether it is consistent with its description given below. Take a picture of computer and upload the picture.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><TextDisplay><Key>Seller Address</Key><Value>#1159 Cailun Road</Value></TextDisplay><ImageInput><Key>Photo of the Second-hand Computer</Key><Value></Value></ImageInput><ChoiceInput><Key>Please Choose Consistent Degree</Key><Value>Very Consistent,Consistent,Not Consistent</Value></ChoiceInput></Root>', 'xiao', '6', '', '36', '2015-01-29 20:20:59', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.siteinspection.InspectSiteService'), ('180', '<Root><Description>Please accesss the price of the given second-hand computer configuration based on the photo given.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><ImageDisplay><Key>Photo of the Second-hand Computer</Key><Value>1422534100851.jpg</Value></ImageDisplay><TextInput><Key>the Price of this computer You Assess</Key><Value/></TextInput></Root>', 'xiao', '6', '', '36', '2015-01-29 20:21:40', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.priceassessment.AssessPriceService'), ('181', '<Root><Description>Please get to the designated location and inspect the second-hand computer specified below. Check it and evaluate whether it is consistent with its description given below. Take a picture of computer and upload the picture.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><TextDisplay><Key>Seller Address</Key><Value>#1159 Cailun Road</Value></TextDisplay><ImageInput><Key>Photo of the Second-hand Computer</Key><Value></Value></ImageInput><ChoiceInput><Key>Please Choose Consistent Degree</Key><Value>Very Consistent,Consistent,Not Consistent</Value></ChoiceInput></Root>', 'sanxing', '60', '', '36', '2015-01-29 20:22:55', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.siteinspection.InspectSiteService'), ('182', '<Root><Description>Please accesss the price of the given second-hand computer configuration based on the photo given.</Description><TextDisplay><Key>Brand</Key><Value>Thinkpad</Value></TextDisplay><TextDisplay><Key>Series</Key><Value>X240</Value></TextDisplay><TextDisplay><Key>Newness</Key><Value>90%</Value></TextDisplay><TextDisplay><Key>CPU</Key><Value>Intel Core i5</Value></TextDisplay><TextDisplay><Key>Memory</Key><Value>8G DDR3</Value></TextDisplay><TextDisplay><Key>Hard Disk</Key><Value>256G SSD</Value></TextDisplay><ImageDisplay><Key>Photo of the Second-hand Computer</Key><Value>1422534214474.jpg</Value></ImageDisplay><TextInput><Key>the Price of this computer You Assess</Key><Value/></TextInput></Root>', 'sanxing', '60', '', '36', '2015-01-29 20:23:34', 'edu.fudan.se.crowdservice.template.BuyingSHComputerTemplateV2', 'edu.fudan.se.crowdservice.priceassessment.AssessPriceService');
COMMIT;

-- ----------------------------
-- Table structure for workerresponse
-- ----------------------------
DROP TABLE IF EXISTS `workerresponse`;
CREATE TABLE `workerresponse` (
`id`  bigint(64) NOT NULL AUTO_INCREMENT ,
`worker`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`responseString`  text CHARACTER SET utf8 COLLATE utf8_general_ci NULL ,
`isAccepted`  bit(1) NOT NULL DEFAULT b'0' ,
`answerTime`  timestamp NULL DEFAULT NULL ,
`taskid`  bigint(64) NOT NULL ,
`offer`  int(32) NOT NULL DEFAULT 0 ,
`isSelected`  bit(1) NOT NULL DEFAULT b'0' ,
PRIMARY KEY (`id`),
FOREIGN KEY (`taskid`) REFERENCES `microtask` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
FOREIGN KEY (`worker`) REFERENCES `agentinfo` (`guid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK1` (`taskid`) USING BTREE ,
INDEX `FK2` (`worker`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=181

;

-- ----------------------------
-- Records of workerresponse
-- ----------------------------
BEGIN;
INSERT INTO `workerresponse` VALUES ('168', 'xiao', '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Root>\r\n<ImageInput>\r\n<Key>Photo of the Second-hand Computer</Key>\r\n<Value>1422522252843.jpg</Value>\r\n</ImageInput>\r\n<ChoiceInput>\r\n<Key>Please Choose Consistent Degree</Key>\r\n<Value>Consistent</Value>\r\n</ChoiceInput>\r\n</Root>\r\n', '', '2015-01-29 17:04:12', '172', '5', ''), ('169', 'an', '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Root>\r\n<ImageInput>\r\n<Key>Photo of the Second-hand Computer</Key>\r\n<Value>1422522251628.jpg</Value>\r\n</ImageInput>\r\n<ChoiceInput>\r\n<Key>Please Choose Consistent Degree</Key>\r\n<Value>Consistent</Value>\r\n</ChoiceInput>\r\n</Root>\r\n', '\0', '2015-01-29 17:04:11', '172', '2', ''), ('170', 'an', '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Root>\r\n<TextInput>\r\n<Key>the Price of this computer You Assess</Key>\r\n<Value>5000</Value>\r\n</TextInput>\r\n</Root>\r\n', '\0', '2015-01-29 17:07:29', '173', '2', ''), ('171', 'xiao', '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Root>\r\n<TextInput>\r\n<Key>the Price of this computer You Assess</Key>\r\n<Value>6000</Value>\r\n</TextInput>\r\n</Root>\r\n', '', '2015-01-29 17:07:29', '173', '5', ''), ('172', 'xiao', null, '', null, '174', '5', ''), ('173', 'xiao', '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Root>\r\n<ImageInput>\r\n<Key>Photo of the Second-hand Computer</Key>\r\n<Value>1422533771785.jpg</Value>\r\n</ImageInput>\r\n<ChoiceInput>\r\n<Key>Please Choose Consistent Degree</Key>\r\n<Value>Very Consistent</Value>\r\n</ChoiceInput>\r\n</Root>\r\n', '', '2015-01-29 20:16:11', '175', '20', ''), ('174', 'xiao', null, '', null, '176', '2', ''), ('175', 'xiao', '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Root>\r\n<ImageInput>\r\n<Key>Photo of the Second-hand Computer</Key>\r\n<Value>1422533892746.jpg</Value>\r\n</ImageInput>\r\n<ChoiceInput>\r\n<Key>Please Choose Consistent Degree</Key>\r\n<Value>Very Consistent</Value>\r\n</ChoiceInput>\r\n</Root>\r\n', '', '2015-01-29 20:18:12', '177', '5', ''), ('176', 'xiao', '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Root>\r\n<TextInput>\r\n<Key>the Price of this computer You Assess</Key>\r\n<Value>5000</Value>\r\n</TextInput>\r\n</Root>\r\n', '', '2015-01-29 20:19:26', '178', '5', ''), ('177', 'sanxing', '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Root>\r\n<ImageInput>\r\n<Key>Photo of the Second-hand Computer</Key>\r\n<Value>1422534084880.jpg</Value>\r\n</ImageInput>\r\n<ChoiceInput>\r\n<Key>Please Choose Consistent Degree</Key>\r\n<Value>Very Consistent</Value>\r\n</ChoiceInput>\r\n</Root>\r\n', '', '2015-01-29 20:21:24', '179', '5', ''), ('178', 'sanxing', null, '\0', null, '180', '20', '\0'), ('179', 'xiao', '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Root>\r\n<ImageInput>\r\n<Key>Photo of the Second-hand Computer</Key>\r\n<Value>1422534191415.jpg</Value>\r\n</ImageInput>\r\n<ChoiceInput>\r\n<Key>Please Choose Consistent Degree</Key>\r\n<Value>Very Consistent</Value>\r\n</ChoiceInput>\r\n</Root>\r\n', '', '2015-01-29 20:23:11', '181', '5', ''), ('180', 'xiao', '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n<Root>\r\n<TextInput>\r\n<Key>the Price of this computer You Assess</Key>\r\n<Value>5000</Value>\r\n</TextInput>\r\n</Root>\r\n', '', '2015-01-29 20:23:49', '182', '5', '');
COMMIT;

-- ----------------------------
-- Auto increment value for microtask
-- ----------------------------
ALTER TABLE `microtask` AUTO_INCREMENT=183;

-- ----------------------------
-- Auto increment value for workerresponse
-- ----------------------------
ALTER TABLE `workerresponse` AUTO_INCREMENT=181;
