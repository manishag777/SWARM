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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phoneno` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` tinyint(4) DEFAULT NULL,
  `pincode` int(11) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `giftcard_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `emailId` (`email`),
  UNIQUE KEY `phoneNo` (`phoneno`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phoneno_2` (`phoneno`),
  UNIQUE KEY `giftcard_id` (`giftcard_id`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`giftcard_id`) REFERENCES `giftcard` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Mukesh','Agrawal','text2manish@gmail.com',NULL,'1993-10-12',2,649413,'','','Singapore',1),(3,'Manish','Agrawal','magrawal6055@gmail.com',NULL,'2015-11-19',3,649413,'','','Singapore',5),(4,'Manish','Agrawal','manishag777@gmail.com',NULL,NULL,3,649413,'','','Singapore',NULL),(5,'Manish','Agrawal',NULL,NULL,NULL,3,0,'','','Singapore',NULL),(6,'Ajeet','Agrawal',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'Ankit','Sambyal','ankit@gmail.com','65-9103-6635',NULL,2,228095,'LAkeside','lakeshore','Singapore',NULL),(8,'Rajesh','Jain','k@gmail.com',NULL,NULL,3,0,'','','',NULL),(9,'Reena','Jain',NULL,NULL,NULL,1,0,NULL,NULL,NULL,NULL),(10,'Lohit','Agrawal',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL),(11,'Lohit','purohit',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL),(12,'Lewing','lisa',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL),(13,'jyotish','Agrawal','magrawals6055@gmail.com',NULL,NULL,3,649413,NULL,NULL,'Singapore',NULL),(14,'Rinku','Agrawal',NULL,NULL,NULL,2,0,NULL,NULL,NULL,NULL),(15,'Rinku','Agrawal',NULL,NULL,'2015-11-16',2,649413,NULL,NULL,'Singapore',NULL),(17,'Akhil','Soni',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL),(18,'Akhil','Soni',NULL,NULL,'2015-11-17',2,0,NULL,NULL,NULL,NULL),(19,'Ajeet','Agrawal','ajit5@gmail.com','123344','2015-11-11',2,649413,'lari','','Singapore',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-13 19:50:37
