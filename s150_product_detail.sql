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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (1,'1-2-3','ranchi','small','black',30,25,0,0,301,2),(4,'13445','ranchi','--','--',200,25,0,0,21,4),(5,'1-2-3','ranchi','small','blue',12,25,0,0,20,4),(6,'1-2-3','ranchi','large','black',12,25,0,0,20,2),(7,'1-2-3','ranchi','large','blue',12,25,0,0,20,2),(8,'1899334','ranchi','','',120,25,0,0,31,3),(9,'23234','ranchi','Men RH','black ',200,25,0,0,40,2),(10,'23234','ranchi','Men RH','white',180,25,0,0,35,4),(11,'23234','ranchi',' Men LH','black ',200,25,0,0,40,2),(12,'23234','ranchi',' Men LH','white',180,25,0,0,35,2),(13,'3232','ranchi','','',160,25,0,0,95,4),(14,'3234','ranchi','24','black',400,25,0,0,35,4),(15,'3234','ranchi','24','white',400,25,0,0,35,4),(16,'3234','ranchi','26','black',380,25,0,0,35,4),(17,'3234','ranchi','26','white',40,25,0,0,20,4),(18,'43546','ranchi','','',210,25,0,0,40,4),(19,'934893','ranchi','','',35,25,0,0,52,4),(20,'934893','','','',23,25,0,0,20,4),(21,'1-2-3','','small','black',20,NULL,0,0,21,4),(22,'1-2-3','ramgarh','small','black',30,NULL,0,0,0,4),(23,'B00426AHGA','ranchi','','',90,NULL,0,0,20,4),(24,'B00AIJ3EN8','ranchi','','BLACK',700,NULL,0,0,35,4),(25,'B00I4O9D34','ranchi','','',250,NULL,0,0,20,4),(26,'B00I7QXM2M','ranchi','','',250,NULL,0,0,15,4),(27,'B00I7QXQ96','ranchi','','',240,NULL,0,0,15,4),(28,'B00I7QXSEE','ranchi','','',280,NULL,0,0,25,4),(29,'B00I7QXUJC','ranchi','','Yellow',150,NULL,0,0,25,4),(30,'B00I7QXUJC','ranchi','','white',120,NULL,0,0,25,4),(31,'B00I9Y41TA','ranchi','4','',450,NULL,0,0,25,4),(32,'B00IAP3W3Y','ranchi','','',1443,NULL,0,0,8,4),(33,'B00ICCYF0E','ranchi','5','white',410,NULL,0,0,8,4),(34,'B00ICCYF0E','ranchi','5','black',450,NULL,0,0,8,4),(35,'B00ID6OUH2','ranchi','5','',250,NULL,0,0,12,4),(36,'B00ID6OY4Q','ranchi','Regular','Orange',900,NULL,0,0,15,4),(37,'B00ID6P3V4','ranchi','','',90,NULL,0,0,9,4),(38,'B00ID6PG7K','ranchi','','',120,NULL,0,0,15,4),(39,'B00ID6PGKC','ranchi','','',60,NULL,0,0,14,4),(40,'B00ID8BITS','ranchi','','',210,NULL,0,0,14,4),(41,'B00IE3ANKM','ranchi','','',390,NULL,0,0,24,4),(42,'B00INTOOMK','ranchi','','',45,NULL,0,0,20,4),(43,'B00INTOOUW','ranchi','17 inch','',750,NULL,0,0,20,4),(44,'B00JQK97O0','ranchi','','',110,NULL,0,0,20,4);
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

-- Dump completed on 2015-11-16 21:14:12
