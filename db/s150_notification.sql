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
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `message` text NOT NULL,
  `seen` tinyint(4) DEFAULT '0',
  `post_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (17,'manish','NIVIA G-21 Aluminium Tennis Racquet&Nivia&0DDXTBFV51&1296&junior&yellow',0,'2015-12-07 07:33:29'),(188,'mukesh','Event Task Assigned',0,'2015-12-17 08:47:14'),(189,'mukesh','Meeting for event training at 12/27/2015 8:30 PM',0,'2015-12-17 08:47:14'),(190,'rishab','Event Task Assigned',0,'2015-12-17 08:47:14'),(191,'rishab','Meeting for event training at 12/27/2015 8:30 PM',0,'2015-12-17 08:47:14'),(192,'mandeep','Event Task Assigned',0,'2015-12-17 08:47:14'),(193,'mandeep','Meeting for event training at 12/27/2015 8:30 PM',0,'2015-12-17 08:47:14'),(194,'piyush','Event Task Assigned',0,'2015-12-17 08:47:14'),(195,'piyush','Meeting for event training at 12/27/2015 8:30 PM',0,'2015-12-17 08:47:14'),(196,'mukesh','Event Task Assigned',0,'2015-12-17 09:16:53'),(197,'mukesh','Meeting for event training at 12/27/2015 8:30 PM',0,'2015-12-17 09:16:53'),(198,'rishab','Event Task Assigned',0,'2015-12-17 09:16:53'),(199,'rishab','Meeting for event training at 12/27/2015 8:30 PM',0,'2015-12-17 09:16:53'),(200,'mandeep','Event Task Assigned',0,'2015-12-17 09:16:53'),(201,'mandeep','Meeting for event training at 12/27/2015 8:30 PM',0,'2015-12-17 09:16:53'),(202,'piyush','Event Task Assigned',0,'2015-12-17 09:16:53'),(203,'piyush','Meeting for event training at 12/27/2015 8:30 PM',0,'2015-12-17 09:16:53');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-19 11:37:49
