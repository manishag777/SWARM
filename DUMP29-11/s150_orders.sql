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
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_id` int(11) NOT NULL,
  `sub_total` int(11) NOT NULL,
  `gc_discount` int(11) DEFAULT NULL,
  `order_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `store_id` varchar(50) NOT NULL,
  `salestaff_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cust_id` (`cust_id`),
  KEY `store_id` (`store_id`),
  KEY `salestaff_id` (`salestaff_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`salestaff_id`) REFERENCES `employee` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (4,1,575,10,'2015-11-08 18:43:30','ranchi',NULL),(5,1,450,10,'2015-11-08 18:49:07','ranchi',NULL),(6,1,150,10,'2015-11-08 18:50:37','ranchi',NULL),(7,3,150,10,'2015-11-08 18:52:08','ranchi',NULL),(8,3,150,10,'2015-11-08 18:52:11','ranchi',NULL),(9,1,150,10,'2015-11-09 12:03:41','ranchi',NULL),(10,1,150,10,'2015-11-09 12:05:02','ranchi',NULL),(11,1,150,10,'2015-11-09 12:08:10','ranchi',NULL),(12,1,150,10,'2015-11-09 12:32:10','ranchi',NULL),(13,1,150,10,'2015-11-09 12:38:59','ranchi',NULL),(14,1,150,10,'2015-11-09 12:40:13','ranchi',NULL),(15,1,150,10,'2015-11-09 12:40:46','ranchi',NULL),(16,1,150,10,'2015-11-09 12:41:23','ranchi',NULL),(17,3,300,10,'2015-11-09 12:48:39','ranchi',NULL),(18,3,300,10,'2015-11-09 12:49:07','ranchi',NULL),(19,3,300,10,'2015-11-09 12:51:47','ranchi',NULL),(20,3,300,10,'2015-11-09 12:52:30','ranchi',NULL),(21,3,300,10,'2015-11-09 12:53:27','ranchi',NULL),(22,1,150,10,'2015-11-09 12:56:26','ranchi',NULL),(23,1,450,10,'2015-11-09 14:31:58','ranchi',NULL),(24,1,150,10,'2015-11-09 14:34:58','ranchi',NULL),(25,1,75,10,'2015-11-09 14:36:13','ranchi',NULL),(26,1,425,10,'2015-11-09 20:55:42','ranchi',NULL),(27,1,240,10,'2015-11-12 18:07:58','ranchi',NULL),(28,1,1200,10,'2015-11-12 18:08:38','ranchi',NULL),(29,1,240,10,'2015-11-12 18:11:33','ranchi',NULL),(30,1,720,10,'2015-11-12 18:18:34','ranchi',NULL),(31,1,1008,10,'2015-11-12 18:23:27','ranchi',NULL),(32,32,720,10,'2015-11-16 18:34:37','ranchi',NULL),(33,33,1080,10,'2015-11-16 18:45:03','ranchi',NULL),(34,34,720,10,'2015-11-16 18:47:30','ranchi',NULL),(35,35,720,10,'2015-11-16 18:50:32','ranchi',NULL),(36,6,72,10,'2015-11-16 19:57:35','ranchi',NULL),(37,6,180,10,'2015-11-16 20:02:32','ranchi',NULL),(38,1,72,10,'2015-11-16 20:07:15','ranchi',NULL),(39,1,72,10,'2015-11-16 20:09:04','ranchi',NULL),(40,36,720,10,'2015-11-16 20:27:47','ranchi',NULL),(41,1,360,10,'2015-11-16 20:44:03','ranchi',NULL),(42,1,360,10,'2015-11-16 20:44:57','ranchi',NULL),(43,1,360,10,'2015-11-16 20:46:40','ranchi',NULL),(44,1,360,10,'2015-11-16 21:00:50','ranchi',NULL),(45,1,360,10,'2015-11-16 21:02:42','ranchi',NULL),(46,4,720,10,'2015-11-16 21:03:10','ranchi',NULL),(47,1,360,10,'2015-11-16 21:05:06','ranchi',NULL),(48,37,1440,10,'2015-11-16 21:07:08','ranchi',NULL),(49,1,360,10,'2015-11-17 11:12:48','ranchi',NULL),(50,1,360,10,'2015-11-17 11:13:47','ranchi',NULL),(51,1,360,10,'2015-11-17 11:15:11','ranchi',NULL),(52,1,720,10,'2015-11-17 11:16:47','ranchi',NULL),(53,1,360,0,'2015-11-17 12:02:03','ranchi',NULL),(54,1,360,0,'2015-11-17 12:06:56','ranchi',NULL),(55,1,5610,0,'2015-11-17 12:12:18','ranchi',NULL),(56,1,5610,0,'2015-11-17 12:14:28','ranchi',NULL),(57,1,5610,0,'2015-11-17 12:15:04','ranchi',NULL),(58,1,19000,0,'2015-11-17 15:03:47','ranchi',NULL),(59,1,29500,0,'2015-11-17 15:04:32','ranchi',NULL),(60,1,300,0,'2015-11-17 16:54:04','ranchi',NULL),(61,6,17500,0,'2015-11-17 17:31:42','ranchi',NULL),(62,38,9600,0,'2015-11-17 19:20:56','ranchi',NULL),(63,4,315,0,'2015-11-17 22:04:26','ranchi',NULL),(64,7,480,0,'2015-11-17 22:06:08','ranchi',NULL),(65,4,7875,0,'2015-11-17 22:08:01','ranchi',NULL),(66,1,447,0,'2015-11-25 14:55:22','ranchi',NULL),(67,1,369,0,'2015-11-25 14:56:58','ranchi',NULL),(68,4,4250,0,'2015-11-25 15:06:27','ranchi',NULL),(69,1,369,0,'2015-11-25 15:09:20','ranchi',NULL),(70,34,1912,0,'2015-11-25 18:46:14','ranchi',NULL),(71,6,288,0,'2015-11-25 18:52:51','ranchi',NULL),(72,1,369,0,'2015-11-25 19:02:13','ranchi',NULL),(73,23,994,0,'2015-11-25 19:05:09','ranchi',NULL),(74,23,994,0,'2015-11-25 19:10:57','ranchi',NULL),(75,12,147,0,'2015-11-25 19:12:39','ranchi',NULL),(76,1,4250,0,'2015-11-25 19:14:19','ranchi',NULL),(77,1,4250,0,'2015-11-25 19:16:42','ranchi',NULL),(78,1,4250,0,'2015-11-25 19:16:53','ranchi',NULL),(79,1,4250,0,'2015-11-25 19:17:33','ranchi',NULL),(80,1,4250,0,'2015-11-25 19:18:41','ranchi','neeraj');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-29 20:08:48
