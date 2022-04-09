-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: localhost    Database: hospital
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `inpatient_ward`
--

DROP TABLE IF EXISTS `inpatient_ward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inpatient_ward` (
  `ino` int(4) NOT NULL AUTO_INCREMENT,
  `iname` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `location` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ino`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inpatient_ward`
--

LOCK TABLES `inpatient_ward` WRITE;
/*!40000 ALTER TABLE `inpatient_ward` DISABLE KEYS */;
INSERT INTO `inpatient_ward` VALUES (1,'重症(ICU)','东302'),(2,'负压病房','西226'),(3,'普通病房','西209'),(4,'观察病房','东201');
/*!40000 ALTER TABLE `inpatient_ward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `pno` int(8) NOT NULL AUTO_INCREMENT,
  `pname` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `sex` int(1) NOT NULL,
  `age` int(3) NOT NULL,
  `ino` int(4) NOT NULL,
  `doctor` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`pno`),
  KEY `ino` (`ino`),
  CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`ino`) REFERENCES `inpatient_ward` (`ino`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (1,'杜瑶',2,14,1,'王强'),(2,'牛凌云',1,20,1,'王强'),(3,'李强',1,19,2,'刘国庆'),(4,'刘一聪',1,23,2,'刘国庆'),(5,'张津铭',1,16,3,'王强'),(6,'赵倩',2,15,3,'王强'),(7,'张贺贺',2,22,4,'高梦云'),(8,'宋璐璐',2,21,4,'高梦云'),(9,'啊啊啊',1,19,1,'啊');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test` (
  `tno` int(4) NOT NULL AUTO_INCREMENT,
  `tname` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `text` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`tno`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (1,'王刚','是个哈批'),(2,'李春','是个人');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-09 17:03:02
