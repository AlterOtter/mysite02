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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `no` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `contents` text NOT NULL,
  `hit` int(11) NOT NULL DEFAULT '0',
  `g_no` bigint(20) NOT NULL DEFAULT '0',
  `o_no` int(11) NOT NULL DEFAULT '0',
  `depth` int(11) NOT NULL DEFAULT '0',
  `reg_date` datetime NOT NULL,
  `user_no` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `no_idx` (`user_no`),
  CONSTRAINT `no` FOREIGN KEY (`user_no`) REFERENCES `user` (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (51,'안녕하세요 날씨가 좋네요','오늘은 금요일 날씨가 좋네요~!',2,1,1,1,'2022-01-19 19:20:12',8),(52,'저녁에는 춥데요!','감기 조심하세요!',2,1,5,2,'2022-01-19 19:20:38',8),(53,'오늘 저녁 뭐먹?','돈까스 ㄱㄱ?',2,1,3,2,'2022-01-19 19:21:00',8),(54,'피자 맛집 ㄱㄱ?','ㄱㄱ?',0,1,4,3,'2022-01-19 19:21:26',8),(55,'잘 먹고 잘 살아라 난 간다','ㅂㅂ2',1,2,1,1,'2022-01-19 19:21:48',8),(56,'어디가 맛집인가요?','강남 맛집 ㅇㄷ?',1,3,1,1,'2022-01-19 19:22:10',8),(57,'스프링 책 추천점','스프링 책 볼만한 거 있음?',1,4,1,1,'2022-01-19 19:22:41',8),(58,'Redirect 와 Forward의 차이가 뭔가요?','뭥미',1,5,1,1,'2022-01-19 19:23:29',8),(59,'Redirect는 req 값 못 넘김 ','그렇슴 ㅇㅇ',1,5,2,2,'2022-01-19 19:23:56',8),(60,'만화책 추천점','액션 만화 추천점',1,6,1,1,'2022-01-19 19:24:33',8),(61,'만갤로 ','ㅇ',1,6,2,2,'2022-01-19 19:24:45',8),(62,'ㅋㅋㅋㅋㅋㅋ 복권 1등 당첨됨 ㅋㅋㅋㅋ','ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ \r\n메타버스에서',2,7,1,1,'2022-01-19 19:50:24',9),(63,'파스타 맛집 추천점','제발료',0,1,2,2,'2022-01-21 15:24:42',8);
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
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
