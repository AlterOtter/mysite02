CREATE DATABASE  IF NOT EXISTS `webdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `webdb`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: webdb
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment_tb`
--

DROP TABLE IF EXISTS `comment_tb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_tb` (
  `comm_sn` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '댓글 번호',
  `comm_mem_sn` bigint(20) NOT NULL COMMENT '작성자 번호',
  `comm_bd_sn` bigint(20) NOT NULL COMMENT '게시물 번호',
  `comm_content` varchar(250) DEFAULT NULL COMMENT '댓글 내용',
  `comm_date` datetime DEFAULT NULL COMMENT '댓글 등록일자',
  PRIMARY KEY (`comm_sn`),
  KEY `comment_tb_ibfk_1` (`comm_bd_sn`),
  KEY `comment_tb_ibfk_2` (`comm_mem_sn`),
  CONSTRAINT `comment_tb_ibfk_1` FOREIGN KEY (`comm_bd_sn`) REFERENCES `board` (`no`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `comment_tb_ibfk_2` FOREIGN KEY (`comm_mem_sn`) REFERENCES `user` (`no`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_tb`
--

LOCK TABLES `comment_tb` WRITE;
/*!40000 ALTER TABLE `comment_tb` DISABLE KEYS */;
INSERT INTO `comment_tb` VALUES (26,8,57,'토비의 스프링','2022-01-19 19:23:00'),(27,8,60,'요즘 볼꺼없음','2022-01-19 19:25:51'),(28,8,59,'댓글 테스트 중인데 잘되네','2022-01-19 19:26:03'),(29,8,56,'여기 맛집없음','2022-01-19 19:26:12'),(30,8,55,'ㅂㅂ','2022-01-19 19:26:16'),(31,9,51,'영상 10도네요','2022-01-19 19:49:53'),(32,8,61,'231','2022-01-21 15:24:20');
/*!40000 ALTER TABLE `comment_tb` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-23 12:13:38
