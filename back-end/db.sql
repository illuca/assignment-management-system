-- MySQL dump 10.13  Distrib 8.0.23, for osx10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: homework_management_system
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '文件名',
  `md5` varchar(32) DEFAULT NULL COMMENT 'MD5值',
  `path` varchar(100) NOT NULL COMMENT '文件路径',
  `upload_time` datetime(3) NOT NULL COMMENT '上传时间',
  `ext` varchar(255) DEFAULT NULL COMMENT '文件后缀名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (58,'demo.zip',NULL,'./upload/23f03191-5899-4a77-87bc-3a843d9d74f5','2021-05-28 10:39:10.350','zip'),(59,'王思聪-Lua课程设计.zip',NULL,'./upload/dc39e965-c298-45c4-8c37-cb73860415b8','2021-05-28 15:51:04.200','zip'),(60,'王思聪_Java课程设计.zip',NULL,'./upload/afaf892f-fde3-4066-ab08-4ec0cc57e66b','2021-05-31 01:02:53.556','zip'),(62,'王思聪的php作业.zip',NULL,'./upload/faac66d7-41e8-4cff-96ff-3769c0da3e05','2021-06-13 18:16:12.054','zip'),(64,'鸣人的C语言程序设计.zip',NULL,'./upload/17875937-e043-419a-a27a-d715e4e2668c','2021-06-15 01:49:56.527','zip');
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homework`
--

DROP TABLE IF EXISTS `homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `homework` (
  `homework_id` int NOT NULL AUTO_INCREMENT,
  `teacher_id` int NOT NULL,
  `homework_title` varchar(30) NOT NULL,
  `homework_content` text NOT NULL,
  PRIMARY KEY (`homework_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework`
--

LOCK TABLES `homework` WRITE;
/*!40000 ALTER TABLE `homework` DISABLE KEYS */;
INSERT INTO `homework` VALUES (11,1001,'Java课程设计','完成旅游网站设计'),(12,1001,'C语言程序设计','学生管理系统'),(13,1001,'Lua课程设计','Lua课程设计内容'),(14,1001,'php课程设计','php课程设计大作业'),(15,1001,'操作系统设计','操作系统设计大作业'),(16,1001,'计算机组成原理','完成组成原理设计');
/*!40000 ALTER TABLE `homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `student_id` int NOT NULL,
  `student_name` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (17001,'鸣人','50b7a5e22f9b57cb1a664d143272bf32'),(17002,'王思聪','78a5a62acccb0b2ea4f48b94adb57a8d'),(17003,'蒋介石','c347b150005a8f3d45d0b86bd85cebc1');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_homework`
--

DROP TABLE IF EXISTS `student_homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_homework` (
  `student_homework_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `homework_id` int NOT NULL,
  `title` varchar(30) NOT NULL,
  `content` text NOT NULL,
  `teacher_comment` text,
  PRIMARY KEY (`student_homework_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_homework`
--

LOCK TABLES `student_homework` WRITE;
/*!40000 ALTER TABLE `student_homework` DISABLE KEYS */;
INSERT INTO `student_homework` VALUES (16,17002,11,'Java课程设计','采用MVC架构','写的不错,再接再厉'),(18,17002,14,'王思聪的php课程设计','运用了php相关技术','需要继续努力'),(20,17002,12,'王思聪的C语言程序设计','运用了C的相关技术','做的不错'),(21,17001,12,'鸣人的C语言程序设计','使用了C的相关技术',NULL);
/*!40000 ALTER TABLE `student_homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_homework_file`
--

DROP TABLE IF EXISTS `student_homework_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_homework_file` (
  `student_homework_file_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `homework_id` int NOT NULL,
  `file_id` int NOT NULL,
  `file_name` varchar(100) NOT NULL,
  `file_path` varchar(100) NOT NULL,
  PRIMARY KEY (`student_homework_file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_homework_file`
--

LOCK TABLES `student_homework_file` WRITE;
/*!40000 ALTER TABLE `student_homework_file` DISABLE KEYS */;
INSERT INTO `student_homework_file` VALUES (5,17002,11,60,'王思聪_Java课程设计.zip','./upload/afaf892f-fde3-4066-ab08-4ec0cc57e66b'),(7,17002,14,62,'王思聪的php作业.zip','./upload/faac66d7-41e8-4cff-96ff-3769c0da3e05'),(8,17002,12,63,'王思聪的C语言程序设计.zip','./upload/04c3f749-3fa5-4004-b448-553d24491b76'),(9,17001,12,64,'鸣人的C语言程序设计.zip','./upload/17875937-e043-419a-a27a-d715e4e2668c');
/*!40000 ALTER TABLE `student_homework_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `teacher_id` int NOT NULL,
  `teacher_name` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1001,'卡卡西','aafff6c5c7e4b112feeaba74b5d05fe3'),(1002,'迈特凯','f48118a16a50e95033c73cc47e46393d');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-16 21:12:01
