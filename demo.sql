-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: demo
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement` (
  `anncId` int NOT NULL AUTO_INCREMENT COMMENT '公告ID,主键自增加',
  `publisher` varchar(100) NOT NULL COMMENT '发布者',
  `avatar` varchar(255) NOT NULL COMMENT '发布者头像',
  `message` varchar(1024) NOT NULL COMMENT '公告内容',
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`anncId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
INSERT INTO `announcement` VALUES (1,'SysAdmin','https://ts1.cn.mm.bing.net/th/id/R-C.8fc4a6ee4a21185c12ba17b780b5bd76?rik=xb2r7iZh5OuOIA&riu=http%3a%2f%2fimg.ourschools.cn%2fimage%2fschool_logo%2fp00030165.jpeg&ehk=nT1dnPPNW4ixHhMTXSRsyQwV2hcTfrJN%2f77umSe%2fRjo%3d&risl=&pid=ImgRaw&r=0','123','2024-11-18 16:35:15'),(2,'SysAdmin','https://ts1.cn.mm.bing.net/th/id/R-C.8fc4a6ee4a21185c12ba17b780b5bd76?rik=xb2r7iZh5OuOIA&riu=http%3a%2f%2fimg.ourschools.cn%2fimage%2fschool_logo%2fp00030165.jpeg&ehk=nT1dnPPNW4ixHhMTXSRsyQwV2hcTfrJN%2f77umSe%2fRjo%3d&risl=&pid=ImgRaw&r=0','这是第二条公告','2024-11-18 16:35:15'),(3,'DormAdmin','https://www.emojiall.com/images/120/microsoft/windows-11-november-2021-update/1f975.png','玛卡巴卡，玛卡巴卡','2024-11-18 16:35:15'),(4,'DormAdmin','https://www.emojiall.com/images/120/microsoft/windows-11-november-2021-update/1f975.png','玛卡巴卡，玛卡巴卡','2024-11-18 19:33:44'),(5,'SysAdmin','https://ts1.cn.mm.bing.net/th/id/R-C.8fc4a6ee4a21185c12ba17b780b5bd76?rik=xb2r7iZh5OuOIA&riu=http%3a%2f%2fimg.ourschools.cn%2fimage%2fschool_logo%2fp00030165.jpeg&ehk=nT1dnPPNW4ixHhMTXSRsyQwV2hcTfrJN%2f77umSe%2fRjo%3d&risl=&pid=ImgRaw&r=0','这是第5条公告','2024-11-18 19:33:44');
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT COMMENT '用户ID,主键自增加',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `token` varchar(255) DEFAULT NULL COMMENT '令牌',
  `avatar` varchar(255) DEFAULT 'https://www.emojiall.com/images/120/microsoft/windows-11-november-2021-update/1f975.png' COMMENT '头像',
  `roles` varchar(100) NOT NULL COMMENT '权限角色',
  `gender` varchar(32) NOT NULL DEFAULT '男' COMMENT '性别',
  `dorm` varchar(255) NOT NULL COMMENT '宿舍号',
  `major` varchar(255) NOT NULL COMMENT '年级专业',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'SysAdmin','111111',NULL,'https://ts1.cn.mm.bing.net/th/id/R-C.8fc4a6ee4a21185c12ba17b780b5bd76?rik=xb2r7iZh5OuOIA&riu=http%3a%2f%2fimg.ourschools.cn%2fimage%2fschool_logo%2fp00030165.jpeg&ehk=nT1dnPPNW4ixHhMTXSRsyQwV2hcTfrJN%2f77umSe%2fRjo%3d&risl=&pid=ImgRaw&r=0','SysAdmin','男','后勤信息服务部','系统管理员'),(2,'DormAdmin','111111',NULL,'https://www.emojiall.com/images/120/microsoft/windows-11-november-2021-update/1f975.png','DormAdmin','男','舍管办公室','宿舍管理员'),(3,'Student','111111',NULL,'https://www.emojiall.com/images/240/microsoft/1f605.png','Student','男','11-515','计算机科学与技术22(3)班');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-25  9:14:50
