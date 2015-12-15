-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: s150
-- ------------------------------------------------------
-- Server version	5.7.9

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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_feedback`
--

LOCK TABLES `price_feedback` WRITE;
/*!40000 ALTER TABLE `price_feedback` DISABLE KEYS */;
INSERT INTO `price_feedback` VALUES (1,'03RI5M5VN4',1,'2015-12-13',1,1,'flipkart',1,'2015-12-13'),(2,'0DDXTBFV51',10,'2015-12-13',1,0,NULL,0,NULL),(3,'0VGANMX571',100,'2015-12-13',1,0,NULL,0,NULL),(4,'03RI5M5VN4',20,'2015-12-15',1,1,'FlipKart',0,NULL),(5,'10HVLXJKN8',21,'2015-12-15',0,1,NULL,0,NULL),(6,'1NV6ZJUSC9',23,'2015-12-15',1,0,NULL,0,NULL),(7,'1CWB9Y1J1J',111,'2015-12-15',1,1,NULL,0,NULL);
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

-- Dump completed on 2015-12-16  0:41:46
