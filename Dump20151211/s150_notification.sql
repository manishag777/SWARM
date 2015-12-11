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
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (17,'manish','NIVIA G-21 Aluminium Tennis Racquet&Nivia&0DDXTBFV51&1296&junior&yellow',0,'2015-12-07 07:33:29'),(42,'mukesh','Event Task Assigned',0,'2015-12-11 03:40:34'),(43,'mukesh','Meeting for event training at 12/30/2015 12:00 AM',0,'2015-12-11 03:40:34'),(44,'rishab','Event Task Assigned',0,'2015-12-11 03:40:34'),(45,'rishab','Meeting for event training at 12/30/2015 12:00 AM',0,'2015-12-11 03:40:34'),(46,'mukesh','Event Task Assigned',0,'2015-12-11 04:02:27'),(47,'mukesh','Meeting for event training at 12/01/2015 1:00 AM',0,'2015-12-11 04:02:27'),(48,'rishab','Event Task Assigned',0,'2015-12-11 04:02:27'),(49,'rishab','Meeting for event training at 12/01/2015 1:00 AM',0,'2015-12-11 04:02:27'),(50,'mukesh','Event Task Assigned',0,'2015-12-11 04:03:53'),(51,'mukesh','Meeting for event training at 12/07/2015 12:00 AM',0,'2015-12-11 04:03:53'),(52,'rishab','Event Task Assigned',0,'2015-12-11 04:03:53'),(53,'rishab','Meeting for event training at 12/07/2015 12:00 AM',0,'2015-12-11 04:03:53'),(54,'mukesh','Event Task Assigned',0,'2015-12-11 04:04:57'),(55,'mukesh','Meeting for event training at 12/01/2015 12:00 AM',0,'2015-12-11 04:04:57'),(56,'rishab','Event Task Assigned',0,'2015-12-11 04:04:57'),(57,'rishab','Meeting for event training at 12/01/2015 12:00 AM',0,'2015-12-11 04:04:57'),(58,'mukesh','Event Task Assigned',0,'2015-12-11 04:16:02'),(59,'mukesh','Meeting for event training at 12/21/2015 1:00 AM',0,'2015-12-11 04:16:02'),(60,'rishab','Event Task Assigned',0,'2015-12-11 04:16:02'),(61,'rishab','Meeting for event training at 12/21/2015 1:00 AM',0,'2015-12-11 04:16:02'),(62,'mukesh','Event Task Assigned',0,'2015-12-11 07:09:01'),(63,'mukesh','Meeting for event training at 12/15/2015 1:00 AM',0,'2015-12-11 07:09:01'),(64,'rishab','Event Task Assigned',0,'2015-12-11 07:09:01'),(65,'rishab','Meeting for event training at 12/15/2015 1:00 AM',0,'2015-12-11 07:09:01'),(66,'mukesh','Event Task Assigned',0,'2015-12-11 07:09:25'),(67,'mukesh','Meeting for event training at 12/15/2015 1:00 AM',0,'2015-12-11 07:09:26'),(68,'rishab','Event Task Assigned',0,'2015-12-11 07:09:26'),(69,'rishab','Meeting for event training at 12/15/2015 1:00 AM',0,'2015-12-11 07:09:26'),(70,'mukesh','Event Task Assigned',0,'2015-12-11 08:11:38'),(71,'mukesh','Meeting for event training at 07/08/2015 9:30 PM',0,'2015-12-11 08:11:38'),(72,'rishab','Event Task Assigned',0,'2015-12-11 08:11:38'),(73,'rishab','Meeting for event training at 07/08/2015 9:30 PM',0,'2015-12-11 08:11:38'),(74,'mandeep','Event Task Assigned',0,'2015-12-11 08:55:10'),(75,'mandeep','Meeting for event training at 12/13/2015 7:45 PM',0,'2015-12-11 08:55:10'),(76,'piyush','Event Task Assigned',0,'2015-12-11 08:55:10'),(77,'piyush','Meeting for event training at 12/13/2015 7:45 PM',0,'2015-12-11 08:55:10'),(78,'mukesh','Event Task Assigned',0,'2015-12-11 09:27:04'),(79,'mukesh','Meeting for event training at ',0,'2015-12-11 09:27:04'),(80,'rishab','Event Task Assigned',0,'2015-12-11 09:27:04'),(81,'rishab','Meeting for event training at ',0,'2015-12-11 09:27:04'),(82,'mukesh','Event Task Assigned',0,'2015-12-11 11:06:31'),(83,'mukesh','Meeting for event training at 12/16/2015 1:00 PM',0,'2015-12-11 11:06:31'),(84,'rishab','Event Task Assigned',0,'2015-12-11 11:06:31'),(85,'rishab','Meeting for event training at 12/16/2015 1:00 PM',0,'2015-12-11 11:06:31');
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

-- Dump completed on 2015-12-11 20:19:01
