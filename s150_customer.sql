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
  `referrerId` int(11) DEFAULT NULL,
  `isNewCustomer` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneNo` (`phoneno`),
  UNIQUE KEY `phoneno_2` (`phoneno`),
  UNIQUE KEY `giftcard_id` (`giftcard_id`),
  KEY `refferId` (`referrerId`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`giftcard_id`) REFERENCES `giftcard` (`id`),
  CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`referrerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Mukesh','Agrawal','text2manish@gmail.com',NULL,'1993-10-12',2,649413,'','','Singapore',10,NULL,1),(3,'Manish','Agrawal','magrawal6055@gmail.com',NULL,'2015-11-19',3,649413,'','','Singapore',5,NULL,0),(4,'Manish','Agrawal','manishag777@gmail.com',NULL,NULL,3,649413,'','','Singapore',11,NULL,1),(5,'Manish','Agrawal',NULL,NULL,NULL,3,0,'','','Singapore',NULL,NULL,0),(6,'Ajeet','Agrawal',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),(7,'Ankit','Sambyal','ankit@gmail.com','65-9103-6635',NULL,2,228095,'LAkeside','lakeshore','Singapore',NULL,NULL,0),(8,'Rajesh','Jain','k@gmail.com',NULL,NULL,3,0,'','','',NULL,NULL,0),(9,'Reena','Jain',NULL,NULL,NULL,1,0,NULL,NULL,NULL,NULL,NULL,0),(10,'Lohit','Agrawal',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL,NULL,0),(11,'Lohit','purohit',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL,NULL,0),(12,'Lewing','lisa',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL,NULL,0),(13,'jyotish','Agrawal','magrawals6055@gmail.com',NULL,NULL,3,649413,NULL,NULL,'Singapore',NULL,NULL,0),(14,'Rinku','Agrawal',NULL,NULL,NULL,2,0,NULL,NULL,NULL,NULL,NULL,0),(15,'Rinku','Agrawal',NULL,NULL,'2015-11-16',2,649413,NULL,NULL,'Singapore',NULL,NULL,0),(17,'Akhil','Soni',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL,NULL,0),(18,'Akhil','Soni',NULL,NULL,'2015-11-17',2,0,NULL,NULL,NULL,NULL,NULL,0),(19,'Ajeet','Agrawal','ajit5@gmail.com','123344','2015-11-11',2,649413,'lari','','Singapore',NULL,NULL,0),(22,'Ayushi','Soti','ayushi@gmail.com',NULL,NULL,3,0,NULL,NULL,NULL,NULL,4,0),(23,'Rishika ','Soti','rishika @gmail.com',NULL,NULL,1,0,NULL,NULL,NULL,NULL,4,0),(24,'Mandeep','Agrawal',NULL,NULL,NULL,2,0,NULL,NULL,NULL,NULL,NULL,0),(25,'queue','','Q@gmail.com',NULL,NULL,3,0,NULL,NULL,NULL,NULL,3,0),(28,'jacob','','Q@gmail.com',NULL,'2015-11-25',3,0,NULL,NULL,NULL,NULL,22,0),(31,'Jyotee','Agrawal','magrawal6055@gmail.com',NULL,NULL,3,649413,NULL,NULL,'Singapore',NULL,NULL,0),(32,'Hrithik','Roshan','text2manish@gmail.com',NULL,NULL,3,0,NULL,NULL,NULL,6,1,1),(33,'Rohan','Sharma',NULL,NULL,NULL,3,0,NULL,NULL,NULL,7,1,1),(34,'Alok','',NULL,NULL,NULL,3,0,NULL,NULL,NULL,8,1,1),(35,'Alok','',NULL,NULL,NULL,3,0,NULL,NULL,NULL,9,1,1),(36,'Zaman','Asrar',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL,3,0),(37,'Raman','Singh',NULL,NULL,NULL,3,0,NULL,NULL,NULL,12,3,1);
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

-- Dump completed on 2015-11-16 21:14:11
