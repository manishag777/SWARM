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
-- Table structure for table `new_events`
--

DROP TABLE IF EXISTS `new_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `new_events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `address` varchar(500) NOT NULL,
  `detail` text,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL,
  `pincode` int(11) DEFAULT NULL,
  `particpantCount` int(11) DEFAULT '0',
  `revenueGenerated` int(11) DEFAULT '0',
  `customerVisited` int(11) DEFAULT '0',
  `expectedRevenue` int(11) DEFAULT '0',
  `expectedCustomerVisit` int(11) DEFAULT '0',
  `targetedRevenue` int(11) DEFAULT '0',
  `targetedCustomer` int(11) DEFAULT NULL,
  `task1` tinyint(4) DEFAULT '0',
  `task2` tinyint(4) DEFAULT '0',
  `task3` tinyint(4) DEFAULT '0',
  `sportsType` varchar(60) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_events`
--

LOCK TABLES `new_events` WRITE;
/*!40000 ALTER TABLE `new_events` DISABLE KEYS */;
INSERT INTO `new_events` VALUES (3,'Mawana Sugars Marathon New Delhi DL','Kirti Mahal, Rajendra Place, New Delhi','<p>\r\n\r\n<p>The Marathon is open to all men and women above the age of 18 years as on 17th February 2013 for Full and Half Marathon.</p><p>The Marathon is an Indian Open Marathon for men and women in the general category and the veteran category.</p>\r\n\r\n<br></p>','2015-02-17','2015-02-17',0,4000,1703214,1824,1703214,1824,NULL,NULL,0,0,0,'athletics'),(5,'Millennium City Marathon Gurgaon HR',' Tau Devi Lal Stadium Sector 38 Gugrgaon','<p>\r\n\r\n<p>The long awaited Mega running event in the Millennium city is here. Run to support a \"Clean Gurgaon, Green Gurgaon\" and construct a healthy community. </p><p>Lets Change the way people see this Millennium City.</p>\r\n\r\n<br></p>','2015-11-01','2015-11-01',0,2800,1334363,1363,1334363,1363,NULL,NULL,0,0,0,'athletics'),(6,'Airtel Delhi Half Marathon New Delhi DL','Hauz khas Delhi','<p>\r\n\r\n<em>Airtel Delhi Half Marathon</em>&nbsp;(<em>ADHM</em>) is an annual half marathon foot-race held in <em>New Delhi</em>, India.\r\n\r\n<br></p>','2015-11-29','2015-11-29',110006,3800,1516541,1570,1516541,1570,NULL,NULL,0,0,0,'athletics'),(7,'Sunfire Run 10K  New Delhi DL','Green Park, Delhi','<p>Get ready for the mega running events.</p>','2015-12-10','2015-12-10',110014,3900,1200637,840,1400637,1410,2052176,1410,1,1,1,'athletics'),(8,'The Great Delhi half marathom','33 jurong west street 41','<p>ee</p>','2015-12-22','2015-12-22',110016,3000,0,0,2094599,1153,1675681,1153,1,1,1,'athletics');
/*!40000 ALTER TABLE `new_events` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-15 19:19:03
