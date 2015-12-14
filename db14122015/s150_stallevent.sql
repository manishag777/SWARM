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
-- Table structure for table `stallevent`
--

DROP TABLE IF EXISTS `stallevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stallevent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eventName` varchar(100) NOT NULL,
  `placeEvent` varchar(500) NOT NULL,
  `pincode` int(11) DEFAULT NULL,
  `detail` text,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL,
  `coName` varchar(500) NOT NULL,
  `cophone` varchar(15) DEFAULT NULL,
  `coEmail` varchar(500) DEFAULT NULL,
  `cmStatus` int(11) DEFAULT NULL,
  `cmTime` varchar(50) DEFAULT NULL,
  `reason` varchar(500) DEFAULT NULL,
  `sport` varchar(50) DEFAULT NULL,
  `mStatus` int(11) DEFAULT '0',
  `fees` int(11) DEFAULT NULL,
  `stallNo` int(11) DEFAULT NULL,
  `trainingTime` varchar(50) DEFAULT NULL,
  `discount` int(11) DEFAULT '0',
  `eventStatus` int(11) DEFAULT '0',
  `rCustomer` int(11) DEFAULT '0',
  `profit` int(11) DEFAULT '0',
  `revenue` int(11) DEFAULT '0',
  `aCustomer` int(11) DEFAULT '0',
  `maddress` varchar(500) DEFAULT '0',
  `taskStatus` int(11) DEFAULT '0',
  `particpantCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stallevent`
--

LOCK TABLES `stallevent` WRITE;
/*!40000 ALTER TABLE `stallevent` DISABLE KEYS */;
INSERT INTO `stallevent` VALUES (3,'Day Breaker Half Marathon Gurgaon HR','South City -1 Gurgaon',NULL,NULL,'2015-07-19','2015-07-19','Mr. Akhil Joy','+918800837388','akhil@gmail.com',1,'07/16/2015 6:00 PM',NULL,'athletics',1,10000,5,'07/08/2015 9:30 PM',0,-1,2245,43540,232280,246,'0',1,NULL),(4,'The Great Delhi Run New Delhi DL','Green Park Delhi',NULL,NULL,'2015-07-05','2015-07-05','Rahul Garg','+91-971328100','rahulgarg@gmail.com',1,'07/04/2015 2:00 PM',NULL,'athletics',1,NULL,NULL,'',0,0,0,0,0,0,'0',1,NULL),(5,'Golden Jubilee Half  New Delhi DL','Hauz Khas Delhi',NULL,NULL,'2015-12-14','2015-12-14','Deepak Kapoor','+91-8800837371','deepak@gmail.com',1,'12/10/2015 5:30 PM',NULL,'athletics',1,12000,2,'12/13/2015 7:45 PM',0,1,1230,0,0,0,'0',1,NULL),(12,'Sunfire Run 10K  New Delhi DL','Connaught place delhi - 110015',NULL,NULL,'2015-12-29','2015-12-29','Rakesh Malhotra','+91-8800837388','rmalhotra@gmail.com',1,'12/13/2015 1:00 PM',NULL,'athletics',1,2200,10,'12/17/2015 12:00 AM',0,0,0,0,0,0,'0',1,3000),(13,'Sunfire Run 10K  New Delhi DL','Connaught place delhi - 110015',NULL,NULL,'2015-12-29','2015-12-29','Rakesh Malhotra','+91-8800837388','rmalhotra@gmail.com',NULL,NULL,NULL,'athletics',0,NULL,NULL,NULL,0,0,0,0,0,0,'0',0,NULL);
/*!40000 ALTER TABLE `stallevent` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-14 19:49:59
