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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stallevent`
--

LOCK TABLES `stallevent` WRITE;
/*!40000 ALTER TABLE `stallevent` DISABLE KEYS */;
INSERT INTO `stallevent` VALUES (2,' ffddf','ddfsd',NULL,NULL,'2015-12-18','2015-12-18','dfd','w1223','df',1,'',NULL,'athletics',-1,12,1,'12/10/2015 12:00 AM',0,0,0,0,0,0,'0',0);
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

-- Dump completed on 2015-12-10 23:23:41
