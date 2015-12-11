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
-- Table structure for table `sportevent`
--

DROP TABLE IF EXISTS `sportevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sportevent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `detail` text,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sportevent`
--

LOCK TABLES `sportevent` WRITE;
/*!40000 ALTER TABLE `sportevent` DISABLE KEYS */;
INSERT INTO `sportevent` VALUES (7,'The adi Pro Run: Gurgaon Gurgaon HR','<p>\r\n\r\nAfter reaching to more than 15000 runners in India, The adi Pro Run 10K checks in Gurgaon as a part of The 72 Hour Project. The venue of the event is Sector 29 Market, Gurgaon. \r\n\r\n<br></p>','2015-01-25','2015-01-25'),(8,'Mawana Sugars Marathon New Delhi DL','<p>\r\n\r\n<p>The Marathon is open to all men and women above the age of 18 years for Full and Half Marathon.</p><p>The Marathon is an Indian Open Marathon for men and women in the general category and the veteran category</p>\r\n\r\n<br></p>','2015-02-17','2015-02-17'),(9,'Serco ATB Trail-a-Thon Gurgaon HR','<p>\r\n\r\nAn event by Aravali Trail Blazers (where all the NCR running groups come together to do this unique 12hrs trail event)\r\n\r\n<br></p>','2015-03-08','2015-04-08'),(10,'Sunfire Run 10K  New Delhi DL','<p>\r\n\r\n<strong>&nbsp;</strong>Mutthu Marathon started by Konganapuram Foundation and Konganapuram Sports Club as a state level event in honour of our courageous ancestors.\r\n\r\n<br></p>','2015-12-20','2015-12-20'),(11,'Airtel Delhi Half Marathon New Delhi DL','<p>\r\n\r\nThis has been a strange year. I ran a lot of races and surprised myself. I ran Bangalore and Hyderabad without any expectations.\r\n\r\n<br></p>','2015-12-29','2016-11-29'),(12,'Golden jubbliee 50 years marathon','','2015-11-29','2015-12-02'),(13,'Golden jubbliee 50 years marathon','','2015-11-29','2015-12-02');
/*!40000 ALTER TABLE `sportevent` ENABLE KEYS */;
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
