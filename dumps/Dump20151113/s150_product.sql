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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `pid` varchar(50) NOT NULL,
  `sport_id` varchar(50) NOT NULL,
  `name` varchar(300) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `info` text,
  `aval_size` varchar(100) DEFAULT 'undefined',
  `aval_color` varchar(100) DEFAULT 'undefined',
  `iurl` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`pid`),
  KEY `sport_id` (`sport_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1-2-3','cricket','PSC Scorer Kashmir Willow Cricket Bat, Short Handle','DSCS','Size: Full Willow: English Willow Pressed and shaped into a huge defined profile to produce an unprecedented \'sweet spot\' Thick edges and an imposing swell to showcase the power contained Well balanced blade to produce an unrivalled pick - up and a devastating hitting power','small_large','black_blue','http://ecx.images-amazon.com/images/I/31MHQ4rkkUL._AA160_FMwebp_QL70_.jpg'),('13445','cricket','Pro Impact Sports - Poly Soft PVC Cricket Ball ','Pro Impact','','--','--','http://ecx.images-amazon.com/images/I/417Jq2sleaL._AA160_.jpg'),('1899334','cricket','SG Campus Inner Gloves','SG','','','','http://ecx.images-amazon.com/images/I/51O7qLC6zgL._AA160_FMwebp_QL70_.jpg'),('23234','cricket','SG Super League Batting Gloves','Sanspareils Greenlands','','Men RH_ Men LH','black _white','http://ecx.images-amazon.com/images/I/51baUVRfkKL._AA160_.jpg'),('3232','cricket','SS College Men\'s Wicket Keeping Gloves (White/Black)','SS','','','','http://ecx.images-amazon.com/images/I/51O7qLC6zgL._AA160_FMwebp_QL70_.jpg'),('3234','cricket','Bas Vampire Super Batting Legguard, Youth','KG','','24_26','black_white','http://ecx.images-amazon.com/images/I/41Vg5VkcHML._AA160_FMwebp_QL70_.jpg'),('43546','cricket','New Balance DC 480 Kashmir Willow Bat','New Balance','','','','http://ecx.images-amazon.com/images/I/31LxLxHO-CL._AA160_FMwebp_QL70_.jpg'),('934893','cricket','bhal','puma','','','','http://ecx.images-amazon.com/images/I/41COpGZTqeL._AA160_.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-13 19:50:36
