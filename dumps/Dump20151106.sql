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
INSERT INTO `customer` VALUES (1,'Mukesh','Agrawal','magrawal6055@gmail.com',NULL,'1993-10-12',2,649413,'','','Singapore',1),(3,'Manish','Agrawal','magrawalv6055@gmail.com',NULL,'2015-11-19',3,649413,'','','Singapore',NULL),(4,'Manish','Agrawal',NULL,NULL,NULL,3,649413,'','','Singapore',NULL),(5,'Manish','Agrawal',NULL,NULL,NULL,3,0,'','','Singapore',NULL),(6,'Ajeet','Agrawal',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'Ankit','Sambyal','ankit@gmail.com','65-9103-6635',NULL,2,228095,'LAkeside','lakeshore','Singapore',NULL),(8,'Rajesh','Jain','k@gmail.com',NULL,NULL,3,0,'','','',NULL),(9,'Reena','Jain',NULL,NULL,NULL,1,0,NULL,NULL,NULL,NULL),(10,'Lohit','Agrawal',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL),(11,'Lohit','purohit',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL),(12,'Lewing','lisa',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL),(13,'jyotish','Agrawal','magrawals6055@gmail.com',NULL,NULL,3,649413,NULL,NULL,'Singapore',NULL),(14,'Rinku','Agrawal',NULL,NULL,NULL,2,0,NULL,NULL,NULL,NULL),(15,'Rinku','Agrawal',NULL,NULL,'2015-11-16',2,649413,NULL,NULL,'Singapore',NULL),(17,'Akhil','Soni',NULL,NULL,NULL,3,0,NULL,NULL,NULL,NULL),(18,'Akhil','Soni',NULL,NULL,'2015-11-17',2,0,NULL,NULL,NULL,NULL),(19,'Ajeet','Agrawal','ajit5@gmail.com','123344','2015-11-11',2,649413,'lari','','Singapore',NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `customer_sport` VALUES (9,'cricket'),(14,'football'),(15,'football'),(9,'tennis'),(10,'tennis'),(11,'tennis'),(12,'tennis'),(13,'tennis'),(17,'tennis'),(18,'tennis'),(19,'tennis');
/*!40000 ALTER TABLE `customer_sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `username` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phoneno` varchar(15) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('johnsson','jhonsson','baby','6848d756da66e55b42f79c0728e351ad','yo@g.com','565644'),('lohit','','','3e5baabb435facf0aa87d58858118bdd','',''),('mahesh','mahesh','babu','37d1703157da260a648d24613032bc8f','',''),('mandeep','Manish','Agrawal','2865a5b14e5a70273a7d311bfc150f4f','magrawal6055@gmail.com','9103-6636'),('mandeepmoh','f','sss','2865a5b14e5a70273a7d311bfc150f4f','ffe','ee'),('manish','Manish','Agrawal','1bac0a5858ac8406c02374b9e36a4e72','magrawal6055@gmail.com','+65-91036635'),('mohan','mohan','chand','aeda6d66c337fa09f185719baa2334f9','mohan@gmail.com','89078456734'),('piyush','Piyush','Dane','70bb67c31f32594966075a9f74b0858a','piyush@gmail.com','+65-91036634'),('ravi','ravi','bopara','80334ba77903a4c0ffaedb50d57695c8','ffe','');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_role`
--

DROP TABLE IF EXISTS `employee_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_role` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  UNIQUE KEY `username_role` (`username`,`role`),
  CONSTRAINT `employee_role_ibfk_1` FOREIGN KEY (`username`) REFERENCES `employee` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
INSERT INTO `employee_role` VALUES ('johnsson','cashier',1),('johnsson','sm',1),('lohit','cashier',1),('lohit','sm',1),('mahesh','sm',1),('mahesh','ss',1),('mandeep','CSO',1),('mandeep','SM',1),('manish','MD',1),('piyush','SM',1);
/*!40000 ALTER TABLE `employee_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_sport`
--

DROP TABLE IF EXISTS `employee_sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_sport` (
  `username` varchar(50) NOT NULL,
  `sport_id` varchar(50) NOT NULL,
  UNIQUE KEY `username_id` (`username`,`sport_id`),
  KEY `sport_id` (`sport_id`),
  CONSTRAINT `employee_sport_ibfk_1` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`),
  CONSTRAINT `employee_sport_ibfk_2` FOREIGN KEY (`username`) REFERENCES `employee` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_sport`
--

LOCK TABLES `employee_sport` WRITE;
/*!40000 ALTER TABLE `employee_sport` DISABLE KEYS */;
INSERT INTO `employee_sport` VALUES ('johnsson','cricket'),('lohit','tennis'),('mahesh','tennis');
/*!40000 ALTER TABLE `employee_sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_store`
--

DROP TABLE IF EXISTS `employee_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_store` (
  `username` varchar(50) NOT NULL,
  `store_id` varchar(50) NOT NULL,
  UNIQUE KEY `username_id` (`username`,`store_id`),
  KEY `store_id` (`store_id`),
  CONSTRAINT `employee_store_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `employee_store_ibfk_2` FOREIGN KEY (`username`) REFERENCES `employee` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_store`
--

LOCK TABLES `employee_store` WRITE;
/*!40000 ALTER TABLE `employee_store` DISABLE KEYS */;
INSERT INTO `employee_store` VALUES ('lohit','ranchi');
/*!40000 ALTER TABLE `employee_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giftcard`
--

DROP TABLE IF EXISTS `giftcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `giftcard` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expiry` date NOT NULL,
  `amt` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giftcard`
--

LOCK TABLES `giftcard` WRITE;
/*!40000 ALTER TABLE `giftcard` DISABLE KEYS */;
INSERT INTO `giftcard` VALUES (1,'2015-11-06',1000);
/*!40000 ALTER TABLE `giftcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `giftcard_specification`
--

DROP TABLE IF EXISTS `giftcard_specification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `giftcard_specification` (
  `issue_amt` int(11) NOT NULL,
  `amount1` int(11) NOT NULL,
  `threshold_amt` int(11) NOT NULL,
  `amount2` int(11) NOT NULL,
  `expiry_extension` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giftcard_specification`
--

LOCK TABLES `giftcard_specification` WRITE;
/*!40000 ALTER TABLE `giftcard_specification` DISABLE KEYS */;
INSERT INTO `giftcard_specification` VALUES (1000,10,120,10,12);
/*!40000 ALTER TABLE `giftcard_specification` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `product` VALUES ('1-2-3','cricket','DSC Scorer Kashmir Willow Cricket Bat, Short Handle','DSC','','small_large','black_blue','http://ecx.images-amazon.com/images/I/31MHQ4rkkUL._AA160_FMwebp_QL70_.jpg'),('13445','cricket','Pro Impact Sports - Poly Soft PVC Cricket Ball ','Pro Impact','','--','--','http://ecx.images-amazon.com/images/I/417Jq2sleaL._AA160_.jpg'),('1899334','cricket','SG Campus Inner Gloves','SG','','','','http://ecx.images-amazon.com/images/I/51O7qLC6zgL._AA160_FMwebp_QL70_.jpg'),('23234','cricket','SG Super League Batting Gloves','Sanspareils Greenlands','','Men RH_ Men LH','black _white','http://ecx.images-amazon.com/images/I/51baUVRfkKL._AA160_.jpg'),('3232','cricket','SS College Men\'s Wicket Keeping Gloves (White/Black)','SS','','','','http://ecx.images-amazon.com/images/I/51O7qLC6zgL._AA160_FMwebp_QL70_.jpg'),('3234','cricket','Bas Vampire Super Batting Legguard, Youth','KG','','24_26','black_white','http://ecx.images-amazon.com/images/I/41Vg5VkcHML._AA160_FMwebp_QL70_.jpg'),('43546','cricket','New Balance DC 480 Kashmir Willow Bat','New Balance','','','','http://ecx.images-amazon.com/images/I/31LxLxHO-CL._AA160_FMwebp_QL70_.jpg'),('934893','cricket','bhal','puma','','','','http://ecx.images-amazon.com/images/I/41COpGZTqeL._AA160_.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

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
  `margin` int(11) DEFAULT '25',
  `discount` int(11) DEFAULT '0',
  `warning_qty` int(11) DEFAULT '0',
  `qty` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cust_pid` (`pid`,`store_id`,`size`,`color`),
  CONSTRAINT `product_detail_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_detail`
--

LOCK TABLES `product_detail` WRITE;
/*!40000 ALTER TABLE `product_detail` DISABLE KEYS */;
INSERT INTO `product_detail` VALUES (1,'1-2-3','ranchi','small','black',12,25,0,0,60);
/*!40000 ALTER TABLE `product_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sport`
--

DROP TABLE IF EXISTS `sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sport` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport`
--

LOCK TABLES `sport` WRITE;
/*!40000 ALTER TABLE `sport` DISABLE KEYS */;
INSERT INTO `sport` VALUES ('cricket','Cricket'),('football','Football'),('tennis','Tennis');
/*!40000 ALTER TABLE `sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store`
--

DROP TABLE IF EXISTS `store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store`
--

LOCK TABLES `store` WRITE;
/*!40000 ALTER TABLE `store` DISABLE KEYS */;
INSERT INTO `store` VALUES ('gola','Gola'),('ramgarh','Ramgarh'),('ranchi','Ranchi');
/*!40000 ALTER TABLE `store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `address` varchar(200) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('mandeep','mandeep','singh','502b7dfff6fb4dc619b4933e1cb122df34605430','punjab'),('manish','manish','agrawal','abdf933375ec9351a3291e2c827182eebe93847c','jharkhand'),('piyush','piyush','dane','7caf4fada912172ae38c6555f73b0fcf5f41c218','mp');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  UNIQUE KEY `role_username` (`username`,`role`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('mandeep','CSO',1),('manish','MD',1),('piyush','SM',1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-06 18:45:55
