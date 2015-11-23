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
-- Table structure for table `marking`
--

DROP TABLE IF EXISTS `marking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marking` (
  `pid` int(11) NOT NULL,
  `preDate` date DEFAULT NULL,
  `preProfitType` varchar(50) DEFAULT NULL,
  `preProfitPercent` int(11) DEFAULT NULL,
  `preDiscountType` varchar(50) DEFAULT NULL,
  `preDiscountPercent` int(11) DEFAULT NULL,
  `currDate` date NOT NULL,
  `currProfitType` varchar(50) NOT NULL,
  `currProfitPercent` int(11) NOT NULL,
  `currDiscountType` varchar(50) NOT NULL,
  `currDiscountPercent` int(11) NOT NULL,
  UNIQUE KEY `pid` (`pid`),
  CONSTRAINT `marking_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `product_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marking`
--

LOCK TABLES `marking` WRITE;
/*!40000 ALTER TABLE `marking` DISABLE KEYS */;
INSERT INTO `marking` VALUES (1,'2015-11-08','moderate',45,'moderate',30,'2015-11-21','low',30,'low',15),(4,'2015-11-01','Default',25,'low',15,'2015-11-09','moderate',45,'moderate',30),(5,'2015-10-29','Default',25,'default',0,'2015-11-08','low',30,'low',15),(6,'2015-11-20','high',60,'low',15,'2015-11-23','moderate',45,'moderate',30),(7,'2015-11-22','low',30,'low',15,'2015-11-23','moderate',45,'moderate',30),(8,'2015-10-26','Default',25,'default',0,'2015-11-09','high',60,'low',15),(9,'2015-11-22','high',60,'low',15,'2015-11-23','moderate',45,'default',0),(10,'2015-11-09','high',60,'moderate',30,'2015-11-16','moderate',45,'moderate',30),(11,'2015-11-07','high',60,'default',0,'2015-11-23','moderate',45,'moderate',30),(12,'2015-10-31','high',60,'moderate',30,'2015-11-12','moderate',45,'low',15),(13,'2015-11-21','low',30,'low',15,'2015-11-23','moderate',45,'moderate',30),(14,'2015-11-06','high',60,'default',0,'2015-11-22','high',60,'low',15),(15,'2015-11-16','low',30,'default',0,'2015-11-17','Default',25,'low',15),(16,'2015-11-06','low',30,'low',15,'2015-11-14','low',30,'default',0),(17,'2015-10-30','moderate',45,'moderate',30,'2015-11-16','moderate',45,'default',0),(18,'2015-11-15','high',60,'moderate',30,'2015-11-20','high',60,'low',15),(19,'2015-10-27','high',60,'moderate',30,'2015-11-12','Default',25,'default',0),(20,'2015-11-09','low',30,'default',0,'2015-11-11','high',60,'default',0),(21,'2015-10-31','moderate',45,'moderate',30,'2015-11-19','high',60,'low',15),(22,'2015-11-18','low',30,'default',0,'2015-11-23','high',60,'default',0),(23,'2015-10-24','Default',25,'low',15,'2015-11-01','low',30,'low',15),(24,'2015-11-18','low',30,'low',15,'2015-11-22','Default',25,'low',15),(25,'2015-11-08','high',60,'default',0,'2015-11-09','high',60,'moderate',30),(26,'2015-11-11','high',60,'low',15,'2015-11-19','Default',25,'default',0),(27,'2015-10-29','moderate',45,'low',15,'2015-11-16','moderate',45,'moderate',30),(28,'2015-11-12','high',60,'default',0,'2015-11-15','moderate',45,'low',15),(29,'2015-11-15','high',60,'default',0,'2015-11-20','moderate',45,'default',0),(30,'2015-10-26','low',30,'default',0,'2015-10-30','Default',25,'low',15),(31,'2015-11-08','high',60,'default',0,'2015-11-23','moderate',45,'moderate',30),(32,'2015-11-08','Default',25,'default',0,'2015-11-13','moderate',45,'moderate',30),(33,'2015-11-11','high',60,'low',15,'2015-11-16','high',60,'moderate',30),(34,'2015-10-27','moderate',45,'default',0,'2015-11-11','Default',25,'low',15),(35,'2015-11-15','moderate',45,'low',15,'2015-11-19','low',30,'low',15),(36,'2015-11-09','high',60,'moderate',30,'2015-11-15','high',60,'default',0),(37,'2015-11-14','moderate',45,'moderate',30,'2015-11-17','high',60,'default',0),(38,'2015-10-31','Default',25,'default',0,'2015-11-14','low',30,'default',0),(39,'2015-11-16','moderate',45,'low',15,'2015-11-20','low',30,'low',15),(40,'2015-11-22','low',30,'low',15,'2015-11-23','high',60,'default',0),(41,'2015-11-10','moderate',45,'default',0,'2015-11-13','moderate',45,'moderate',30),(42,'2015-11-16','Default',25,'default',0,'2015-11-18','moderate',45,'default',0),(43,'2015-11-16','high',60,'moderate',30,'2015-11-18','low',30,'low',15),(44,'2015-11-03','high',60,'low',15,'2015-11-04','low',30,'default',0);
/*!40000 ALTER TABLE `marking` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-23 21:31:10
