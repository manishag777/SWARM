-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 172.26.147.131    Database: s150
-- ------------------------------------------------------
-- Server version	5.6.26

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
-- Table structure for table `product_detail`
--

DROP TABLE IF EXISTS `product_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(50) NOT NULL,
  `store_id` varchar(50) NOT NULL,
  `size` varchar(50) DEFAULT 'undefined',
  `color` varchar(50) DEFAULT 'undefined',
  `price` int(11) NOT NULL,
  `margin_old` int(11) DEFAULT NULL,
  `discount` int(11) DEFAULT '0',
  `warning_qty` int(11) DEFAULT '0',
  `qty` int(11) DEFAULT '0',
  `profit_id` int(11) DEFAULT '4',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cust_pid` (`pid`,`store_id`,`size`,`color`),
  KEY `profit_id` (`profit_id`),
  CONSTRAINT `product_detail_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`),
  CONSTRAINT `product_detail_ibfk_2` FOREIGN KEY (`profit_id`) REFERENCES `profit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (1,'1-2-3','ranchi','small','black',20,25,0,0,479,2),(4,'13445','ranchi','--','--',10,25,0,0,20,4),(5,'1-2-3','ranchi','small','blue',12,25,0,0,20,4),(6,'1-2-3','ranchi','large','black',12,25,0,0,20,2),(7,'1-2-3','ranchi','large','blue',12,25,0,0,20,2),(8,'1899334','ranchi','','',12,25,0,0,20,3),(9,'23234','ranchi','Men RH','black ',24,25,0,0,20,2),(10,'23234','ranchi','Men RH','white',24,25,0,0,20,4),(11,'23234','ranchi',' Men LH','black ',24,25,0,0,20,2),(12,'23234','ranchi',' Men LH','white',30,25,0,0,20,2),(13,'3232','ranchi','','',30,25,0,0,20,4),(14,'3234','ranchi','24','black',30,25,0,0,20,4),(15,'3234','ranchi','24','white',30,25,0,0,20,4),(16,'3234','ranchi','26','black',30,25,0,0,20,4),(17,'3234','ranchi','26','white',40,25,0,0,20,4),(18,'43546','ranchi','','',40,25,0,0,20,4),(19,'934893','ranchi','','',23,25,0,0,32,4),(20,'934893','','','',23,25,0,0,20,4),(21,'1-2-3','','small','black',20,NULL,0,0,21,4);
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-13 19:50:38
