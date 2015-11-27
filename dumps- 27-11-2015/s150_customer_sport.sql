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
-- Table structure for table `customer_sport`
--

DROP TABLE IF EXISTS `customer_sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_sport` (
  `cust_id` int(11) NOT NULL,
  `sport_id` varchar(50) NOT NULL,
  UNIQUE KEY `cust_sport` (`cust_id`,`sport_id`),
  KEY `sport_id` (`sport_id`),
  CONSTRAINT `customer_sport_ibfk_1` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `customer_sport_ibfk_2` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_sport`
--

LOCK TABLES `customer_sport` WRITE;
/*!40000 ALTER TABLE `customer_sport` DISABLE KEYS */;
INSERT INTO `customer_sport` VALUES (1,'cricket'),(3,'cricket'),(7,'cricket'),(9,'cricket'),(25,'cricket'),(31,'cricket'),(32,'cricket'),(33,'cricket'),(34,'cricket'),(35,'cricket'),(36,'cricket'),(37,'cricket'),(38,'cricket'),(39,'cricket'),(45,'cricket'),(46,'cricket'),(14,'football'),(15,'football'),(25,'football'),(31,'football'),(32,'football'),(33,'football'),(34,'football'),(35,'football'),(36,'football'),(37,'football'),(39,'football'),(46,'football'),(1,'tennis'),(3,'tennis'),(9,'tennis'),(10,'tennis'),(11,'tennis'),(12,'tennis'),(13,'tennis'),(17,'tennis'),(18,'tennis'),(19,'tennis'),(22,'tennis'),(23,'tennis'),(24,'tennis'),(28,'tennis'),(45,'tennis');
/*!40000 ALTER TABLE `customer_sport` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-27 20:30:27
