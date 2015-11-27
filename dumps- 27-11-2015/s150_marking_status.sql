-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: s150
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `marking_status`
--

DROP TABLE IF EXISTS `marking_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marking_status` (
  `pid` int(11) NOT NULL,
  `lmarking` int(11) DEFAULT NULL,
  `ldate` date DEFAULT NULL,
  `cmarking` int(11) DEFAULT NULL,
  `cdate` date DEFAULT NULL,
  UNIQUE KEY `pid` (`pid`),
  KEY `lmarking` (`lmarking`),
  KEY `cmarking` (`cmarking`),
  CONSTRAINT `marking_status_ibfk_1` FOREIGN KEY (`lmarking`) REFERENCES `profit` (`id`),
  CONSTRAINT `marking_status_ibfk_2` FOREIGN KEY (`cmarking`) REFERENCES `profit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marking_status`
--

LOCK TABLES `marking_status` WRITE;
/*!40000 ALTER TABLE `marking_status` DISABLE KEYS */;
INSERT INTO `marking_status` VALUES (1,3,'2015-11-01',2,'2015-11-22'),(4,2,'2015-11-18',1,'2015-11-20'),(5,1,'2015-11-02',1,'2015-11-20'),(6,3,'2015-10-27',2,'2015-11-05'),(7,4,'2015-10-24',4,'2015-11-10'),(8,1,'2015-11-15',1,'2015-11-20'),(9,2,'2015-11-11',4,'2015-11-14'),(10,4,'2015-11-11',3,'2015-11-13'),(11,2,'2015-11-18',1,'2015-11-22'),(12,2,'2015-11-17',2,'2015-11-18'),(13,3,'2015-11-15',1,'2015-11-18'),(14,1,'2015-11-23',4,'2015-11-23'),(15,1,'2015-10-28',4,'2015-11-18'),(16,1,'2015-10-26',2,'2015-11-10'),(17,1,'2015-11-09',2,'2015-11-14'),(18,4,'2015-11-02',2,'2015-11-13'),(19,4,'2015-11-16',4,'2015-11-21'),(20,1,'2015-11-17',3,'2015-11-20'),(21,2,'2015-11-05',3,'2015-11-12'),(22,2,'2015-10-23',1,'2015-11-11'),(23,4,'2015-11-01',4,'2015-11-02'),(24,4,'2015-10-26',1,'2015-11-04'),(25,2,'2015-10-27',3,'2015-10-29'),(26,1,'2015-11-08',3,'2015-11-17'),(27,2,'2015-10-28',2,'2015-11-04'),(28,2,'2015-11-03',3,'2015-11-17'),(29,1,'2015-11-10',4,'2015-11-16'),(30,4,'2015-11-05',2,'2015-11-22'),(31,4,'2015-11-01',4,'2015-11-03'),(32,3,'2015-11-18',3,'2015-11-22'),(33,3,'2015-11-03',4,'2015-11-06'),(34,2,'2015-10-25',3,'2015-11-12'),(35,4,'2015-11-18',1,'2015-11-21'),(36,2,'2015-11-18',3,'2015-11-22'),(37,3,'2015-11-08',1,'2015-11-14'),(38,2,'2015-11-11',2,'2015-11-17'),(39,1,'2015-10-24',2,'2015-11-15'),(40,3,'2015-11-20',3,'2015-11-21'),(41,4,'2015-11-17',4,'2015-11-20'),(42,3,'2015-11-21',2,'2015-11-22'),(43,1,'2015-10-23',4,'2015-11-16'),(44,2,'2015-10-28',3,'2015-11-09');
/*!40000 ALTER TABLE `marking_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-27 20:30:28
