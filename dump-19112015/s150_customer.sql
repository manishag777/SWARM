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
  `lng` double DEFAULT NULL,
  `lat` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneNo` (`phoneno`),
  UNIQUE KEY `phoneno_2` (`phoneno`),
  UNIQUE KEY `giftcard_id` (`giftcard_id`),
  KEY `refferId` (`referrerId`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`giftcard_id`) REFERENCES `giftcard` (`id`),
  CONSTRAINT `customer_ibfk_2` FOREIGN KEY (`referrerId`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Manish','Agrawal','text2manish@gmail.com','829122','1993-10-12',1,110001,'Delhi','Delhi','India',10,NULL,1,77.2195969,28.6327426),(3,'Manish','Agrawal','magrawal6055@gmail.com',NULL,'2015-11-19',3,110002,'Delhi','Delhi','Singapore',5,NULL,0,77.2468858,28.6352202),(4,'Manish','Agrawal','manishag777@gmail.com',NULL,NULL,3,110003,NULL,'Delhi','Singapore',11,NULL,1,77.2317863,28.5916468),(5,'Ranjit','Agrawal',NULL,NULL,NULL,3,110007,NULL,'Delhi','Singapore',NULL,NULL,0,77.19158399999999,28.6785828),(6,'Ajeet','Agrawal',NULL,NULL,NULL,0,110008,NULL,'Delhi',NULL,13,NULL,1,77.1640718,28.6482162),(7,'Ankit','Sambyal','ankit@gmail.com','65-9103-6635',NULL,2,110004,'LAkeside','lakeshore','Singapore',15,NULL,1,77.1959622,28.6141527),(8,'Rajesh','Jain','k@gmail.com',NULL,NULL,3,110005,NULL,'Delhi',NULL,NULL,NULL,0,77.1874601,28.6556793),(9,'Reena','Jain',NULL,NULL,NULL,1,110011,NULL,'Delhi',NULL,NULL,NULL,0,77.2116108,28.604077),(10,'Lohit','Agrawal',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL),(11,'Lohit','purohit',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL),(12,'Lewing','lisa',NULL,NULL,NULL,3,110054,NULL,'Delhi',NULL,NULL,NULL,0,77.2275394,28.6903983),(13,'jyotish','Agrawal','magrawals6055@gmail.com',NULL,NULL,3,649413,NULL,NULL,'Singapore',NULL,NULL,0,NULL,NULL),(14,'Rinku','Agrawal',NULL,NULL,NULL,2,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL),(15,'Rinku','Agrawal',NULL,NULL,'2015-11-16',2,649413,NULL,NULL,'Singapore',NULL,NULL,0,NULL,NULL),(17,'Akhil','Soni',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL),(18,'Akhil','Soni',NULL,NULL,'2015-11-17',2,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL),(19,'Ajeet','Agrawal','ajit5@gmail.com','123344','2015-11-11',2,649413,'lari','','Singapore',NULL,NULL,0,NULL,NULL),(22,'Ayushi','Soti','ayushi@gmail.com',NULL,NULL,3,0,NULL,NULL,NULL,NULL,4,0,NULL,NULL),(23,'Rishika ','Soti','rishika @gmail.com',NULL,NULL,1,0,NULL,NULL,NULL,NULL,4,0,NULL,NULL),(24,'Mandeep','Agrawal',NULL,NULL,NULL,2,0,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL),(25,'queue','','Q@gmail.com',NULL,NULL,3,0,NULL,NULL,NULL,NULL,3,0,NULL,NULL),(28,'jacob','','Q@gmail.com',NULL,'2015-11-25',3,0,NULL,NULL,NULL,NULL,22,0,NULL,NULL),(31,'Jyotee','Agrawal','magrawal6055@gmail.com',NULL,NULL,3,649413,NULL,NULL,'Singapore',NULL,NULL,0,NULL,NULL),(32,'Hrithik','Roshan','text2manish@gmail.com',NULL,NULL,3,0,NULL,NULL,NULL,6,1,1,NULL,NULL),(33,'Rohan','Sharma',NULL,NULL,NULL,3,0,NULL,NULL,NULL,7,1,1,NULL,NULL),(34,'Alok','',NULL,NULL,NULL,3,0,NULL,NULL,NULL,8,1,1,NULL,NULL),(35,'Alok','',NULL,NULL,NULL,3,0,NULL,NULL,NULL,9,1,1,NULL,NULL),(36,'Zaman','Asrar',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL,3,0,NULL,NULL),(37,'Raman','Singh',NULL,NULL,NULL,3,0,NULL,NULL,NULL,12,3,1,NULL,NULL),(38,'Piyush','Dane',NULL,NULL,NULL,3,0,NULL,NULL,NULL,14,4,1,NULL,NULL),(39,'Lav ','Khanna',NULL,NULL,NULL,3,829122,NULL,'Jharkhand','India',NULL,7,0,NULL,NULL);
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

-- Dump completed on 2015-11-19 19:38:30
