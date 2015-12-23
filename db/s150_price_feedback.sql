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
-- Table structure for table `price_feedback`
--

DROP TABLE IF EXISTS `price_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `price_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(40) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `date_added` date NOT NULL,
  `higher_than_amazon` tinyint(1) NOT NULL DEFAULT '0',
  `higher_than_ebay` tinyint(1) NOT NULL DEFAULT '0',
  `higher_than_others` varchar(40) DEFAULT NULL,
  `resolved` tinyint(1) NOT NULL DEFAULT '0',
  `date_resolved` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `feedback_key` (`pid`),
  KEY `price_feedback_ibfk_2` (`customer_id`),
  CONSTRAINT `price_feedback_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `price_feedback_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_feedback`
--

LOCK TABLES `price_feedback` WRITE;
/*!40000 ALTER TABLE `price_feedback` DISABLE KEYS */;
INSERT INTO `price_feedback` VALUES (29,'11NP5I6YS3',23,'2015-12-16',1,1,NULL,0,NULL),(30,'16ONNEWYVM',43,'2015-12-16',1,1,NULL,0,NULL),(31,'0DDXTBFV51',12,'2015-12-16',1,0,NULL,0,NULL),(33,'1DQ953ZOBD',23,'2015-12-17',1,1,NULL,1,'2015-12-17'),(35,'48P1M4FTPA',12,'2015-12-17',0,1,NULL,0,NULL);
/*!40000 ALTER TABLE `price_feedback` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-19 11:37:50
