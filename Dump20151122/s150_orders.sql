-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: s150
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
  PRIMARY KEY (`id`),
  KEY `cust_id` (`cust_id`),
  KEY `store_id` (`store_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (4,1,575,10,'2015-11-08 18:43:30','ranchi'),(5,1,450,10,'2015-11-08 18:49:07','ranchi'),(6,1,150,10,'2015-11-08 18:50:37','ranchi'),(7,3,150,10,'2015-11-08 18:52:08','ranchi'),(8,3,150,10,'2015-11-08 18:52:11','ranchi'),(9,1,150,10,'2015-11-09 12:03:41','ranchi'),(10,1,150,10,'2015-11-09 12:05:02','ranchi'),(11,1,150,10,'2015-11-09 12:08:10','ranchi'),(12,1,150,10,'2015-11-09 12:32:10','ranchi'),(13,1,150,10,'2015-11-09 12:38:59','ranchi'),(14,1,150,10,'2015-11-09 12:40:13','ranchi'),(15,1,150,10,'2015-11-09 12:40:46','ranchi'),(16,1,150,10,'2015-11-09 12:41:23','ranchi'),(17,3,300,10,'2015-11-09 12:48:39','ranchi'),(18,3,300,10,'2015-11-09 12:49:07','ranchi'),(19,3,300,10,'2015-11-09 12:51:47','ranchi'),(20,3,300,10,'2015-11-09 12:52:30','ranchi'),(21,3,300,10,'2015-11-09 12:53:27','ranchi'),(22,1,150,10,'2015-11-09 12:56:26','ranchi'),(23,1,450,10,'2015-11-09 14:31:58','ranchi'),(24,1,150,10,'2015-11-09 14:34:58','ranchi'),(25,1,75,10,'2015-11-09 14:36:13','ranchi'),(26,1,425,10,'2015-11-09 20:55:42','ranchi'),(27,1,240,10,'2015-11-12 18:07:58','ranchi'),(28,1,1200,10,'2015-11-12 18:08:38','ranchi'),(29,1,240,10,'2015-11-12 18:11:33','ranchi'),(30,1,720,10,'2015-11-12 18:18:34','ranchi'),(31,1,1008,10,'2015-11-12 18:23:27','ranchi'),(32,32,720,10,'2015-11-16 18:34:37','ranchi'),(33,33,1080,10,'2015-11-16 18:45:03','ranchi'),(34,34,720,10,'2015-11-16 18:47:30','ranchi'),(35,35,720,10,'2015-11-16 18:50:32','ranchi'),(36,6,72,10,'2015-11-16 19:57:35','ranchi'),(37,6,180,10,'2015-11-16 20:02:32','ranchi'),(38,1,72,10,'2015-11-16 20:07:15','ranchi'),(39,1,72,10,'2015-11-16 20:09:04','ranchi'),(40,36,720,10,'2015-11-16 20:27:47','ranchi'),(41,1,360,10,'2015-11-16 20:44:03','ranchi'),(42,1,360,10,'2015-11-16 20:44:57','ranchi'),(43,1,360,10,'2015-11-16 20:46:40','ranchi'),(44,1,360,10,'2015-11-16 21:00:50','ranchi'),(45,1,360,10,'2015-11-16 21:02:42','ranchi'),(46,4,720,10,'2015-11-16 21:03:10','ranchi'),(47,1,360,10,'2015-11-16 21:05:06','ranchi'),(48,37,1440,10,'2015-11-16 21:07:08','ranchi'),(49,1,360,10,'2015-11-17 11:12:48','ranchi'),(50,1,360,10,'2015-11-17 11:13:47','ranchi'),(51,1,360,10,'2015-11-17 11:15:11','ranchi'),(52,1,720,10,'2015-11-17 11:16:47','ranchi'),(53,1,360,0,'2015-11-17 12:02:03','ranchi'),(54,1,360,0,'2015-11-17 12:06:56','ranchi'),(55,1,5610,0,'2015-11-17 12:12:18','ranchi'),(56,1,5610,0,'2015-11-17 12:14:28','ranchi'),(57,1,5610,0,'2015-11-17 12:15:04','ranchi'),(58,1,19000,0,'2015-11-17 15:03:47','ranchi'),(59,1,29500,0,'2015-11-17 15:04:32','ranchi'),(60,1,300,0,'2015-11-17 16:54:04','ranchi'),(61,6,17500,0,'2015-11-17 17:31:42','ranchi'),(62,38,9600,0,'2015-11-17 19:20:56','ranchi'),(63,4,315,0,'2015-11-17 22:04:26','ranchi'),(64,7,480,0,'2015-11-17 22:06:08','ranchi'),(65,4,7875,0,'2015-11-17 22:08:01','ranchi');
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

-- Dump completed on 2015-11-22 22:30:31
