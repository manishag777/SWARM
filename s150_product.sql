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
INSERT INTO `product` VALUES ('','football','Nivia Ball Pump Double Action','Nivia',NULL,'','','http://ecx.images-amazon.com/images/I/31LO3nwO0FL._AA160_FMwebp_QL70_.jpg'),('1-2-3','cricket','PSC Scorer Kashmir Willow Cricket Bat, Short Handle','DSCS','<ul><li>Octoplus grip</li><li>Mid high blade</li><li>12 Piece cane handle</li><li>Contoured edge profile</li></ul>','small_large','black_blue','http://ecx.images-amazon.com/images/I/31MHQ4rkkUL._AA160_FMwebp_QL70_.jpg'),('13445','cricket','Pro Impact Sports - Poly Soft PVC Cricket Ball ','Pro Impact','','--','--','http://ecx.images-amazon.com/images/I/417Jq2sleaL._AA160_.jpg'),('1899334','cricket','SG Campus Inner Gloves','SG','','','','http://ecx.images-amazon.com/images/I/51O7qLC6zgL._AA160_FMwebp_QL70_.jpg'),('23234','cricket','SG Super League Batting Gloves','Sanspareils Greenlands','','Men RH_ Men LH','black _white','http://ecx.images-amazon.com/images/I/51baUVRfkKL._AA160_.jpg'),('3232','cricket','SS College Men\'s Wicket Keeping Gloves (White/Black)','SS','','','','http://ecx.images-amazon.com/images/I/51O7qLC6zgL._AA160_FMwebp_QL70_.jpg'),('3234','cricket','Bas Vampire Super Batting Legguard, Youth','KG','','24_26','black_white','http://ecx.images-amazon.com/images/I/41Vg5VkcHML._AA160_FMwebp_QL70_.jpg'),('43546','cricket','New Balance DC 480 Kashmir Willow Bat','New Balance','','','','http://ecx.images-amazon.com/images/I/31LxLxHO-CL._AA160_FMwebp_QL70_.jpg'),('934893','cricket','ball','puma','','','','http://ecx.images-amazon.com/images/I/41COpGZTqeL._AA160_.jpg'),('B00426AHGA','tennis','Wilson Titanium 3 Tennis Ball, Pack of 3','Wilson',NULL,'','','http://ecx.images-amazon.com/images/I/41aKLWVPDKL._AA160_FMwebp_QL70_.jpg'),('B00AIJ3EN8','tennis','Babolat 140123-142 Aeropro Drive GT Strung Tennis Racquet,','Babolat',NULL,'','BLACK_YELLOW','http://ecx.images-amazon.com/images/I/51NmiSo4fgL._AA160_.jpg'),('B00I4O9D34','cricket','SM Rafter Leather Cricket Ball (Red)','SM',NULL,'','','http://ecx.images-amazon.com/images/I/51l6Mmr9thL._AC_UL160_SR160,160_.jpg'),('B00I7QXM2M','tennis','GKI Offensive Rago Table Tennis Racquet','GKI',NULL,'','','http://ecx.images-amazon.com/images/I/41NnqWFRFnL._AC_UL160_SR160,160_.jpg'),('B00I7QXQ96','tennis','GKI Nano Force Table Tennis Racquet','GKI',NULL,'','','http://ecx.images-amazon.com/images/I/41rWa-9xZjL._AC_UL160_SR160,160_.jpg'),('B00I7QXSEE','tennis','GKI Kung Fu DX Table Tennis Racquet','GKI',NULL,'','','http://ecx.images-amazon.com/images/I/51O%2BKot24dL._AC_UL160_SR160,160_.jpg'),('B00I7QXUJC','tennis','GKI Long Life Table Tennis Ball, Pack of 12','GKI',NULL,'','Yellow_white','http://ecx.images-amazon.com/images/I/419UeMRqjeL._AC_UL160_SR160,160_.jpg'),('B00I9Y41TA','football','Nivia Vega Football','Nivia',NULL,'4_5','','http://ecx.images-amazon.com/images/I/51CJFtMmQnL._AA160_FMwebp_QL70_.jpg'),('B00IAP3W3Y','tennis','Head Ti Reward Club Series Tennis Racquet','HEAD',NULL,'','','http://ecx.images-amazon.com/images/I/41nYsgHxRTL._AA160_.jpg'),('B00ICCYF0E','football','Nivia Storm Football','Nivia',NULL,'5_6','white_black','http://ecx.images-amazon.com/images/I/51uPBBOwGuL._AA160_FMwebp_QL70_.jpg'),('B00ID6OUH2','football','Cosco Mundial Foot Ball','Cosco',NULL,'5','','http://ecx.images-amazon.com/images/I/41zvE6FcEIL._AA160_FMwebp_QL70_.jpg'),('B00ID6OY4Q','tennis','Wilson Match Point XL 3 Tennis Racquet','Wilson',NULL,'Regular','Orange','http://ecx.images-amazon.com/images/I/410FPS2aktL._AA160_.jpg'),('B00ID6P3V4','football','Cosco Hand Pump','Cosco',NULL,'','','http://ecx.images-amazon.com/images/I/21DK3Kw-V4L._AA160_FMwebp_QL70_.jpg'),('B00ID6PG7K','tennis','Cosco Stiga Cup Table Tennis Training Ball, Pack of 6','Cosco','','','','http://ecx.images-amazon.com/images/I/41JmK8YDQ1L._AC_UL160_SR160,160_.jpg'),('B00ID6PGKC','tennis','Cosco Stiga Competition Table Tennis Ball, Pack of 3','Cosco',NULL,'','','http://ecx.images-amazon.com/images/I/413uXOcvGTL._AC_UL160_SR160,160_.jpg'),('B00ID8BITS','cricket','SG Club Leather Ball (White)','SG','','','','http://ecx.images-amazon.com/images/I/4122JucO4QL._AC_UL160_SR160,160_.jpg'),('B00IE3ANKM','cricket','Cosco Club Batting Leg Guard','Cosco','','','','http://ecx.images-amazon.com/images/I/51z8yvmt0QL._AC_UL160_SR130,160_.jpg'),('B00INTOOMK','cricket','GM Glass Fibre Bat Tape, 25mmX10m','GM',NULL,'','','http://ecx.images-amazon.com/images/I/51Xvo-AyXNL._AC_UL160_SR129,160_.jpg'),('B00INTOOUW','cricket','GM Octane Autograph Cricket Bat, 17 inch','GM',NULL,'17 inch','','http://ecx.images-amazon.com/images/I/21baJ5EcgqL._AA160_.jpg'),('B00JQK97O0','tennis','Wilson Australian Open Tennis Ball ','Wilson',NULL,'','','http://ecx.images-amazon.com/images/I/31yUyLcIgtL._AA160_FMwebp_QL70_.jpg'),('B00KBLUJX6','football','Adidas Brazuca Train Pro Football','Adidas',NULL,'4_5','white_yellow','http://ecx.images-amazon.com/images/I/51W%2BzkW4ZtL._AA160_FMwebp_QL70_.jpg'),('B00LN59I7C','football','Nivia Street Football','Nivia',NULL,'4_5','black_blue','http://ecx.images-amazon.com/images/I/615qc1ulY0L._AA160_FMwebp_QL70_.jpg'),('B00LTE6I98','cricket','Ceela Sports League Special Cricket Ball(White)','Ceela Sports',NULL,'','','http://ecx.images-amazon.com/images/I/41XA1ILJJTL._AC_UL160_SR153,160_.jpg'),('B00LTP988A','cricket','SG T20i Pro Cricket Helmet','SG',NULL,'','','http://ecx.images-amazon.com/images/I/51rXwbIPZjL._AA160_FMwebp_QL70_.jpg'),('B00MWOJTA4','cricket','SS Magnum English Willow Cricket Bat',' SS',NULL,'','','http://ecx.images-amazon.com/images/I/318yCfUkYOL._AC_UL160_SR160,160_.jpg'),('B00N2U8DY0','football','Nivia Web Goal-keeper Gloves ','',NULL,'7_8','white_blue','http://ecx.images-amazon.com/images/I/51pSNDxGijL._AC_UL160_SR160,160_.jpg'),('B00ORFTLAE','tennis','NIVIA G-21 Aluminium Tennis Racquet, 21-Inch','NIVIA',NULL,'','green_black','http://ecx.images-amazon.com/images/I/31lVuL1t3wL._AA160_.jpg'),('B00P0IJLRA','football','Cosco Rio Football','cosco','','4_5','green_yellow','http://ecx.images-amazon.com/images/I/51MHBpTD8hL._AA160_FMwebp_QL70_.jpg'),('B00PUHPIAK','tennis','Stag Three Star Table Tennis Balls , 40mm Pack of 3',' DONIC',NULL,'40 mm','Orange','http://ecx.images-amazon.com/images/I/51IH-3FpTWL._AC_UL160_SR137,160_.jpg'),('B00Q83H6GE','tennis','Stag Play Anywhere TT Set','Stag',NULL,'','','http://ecx.images-amazon.com/images/I/51SgmNW98sL._AA160_FMwebp_QL70_.jpg'),('B00R11KAAQ','cricket','SS Heritage Cricket Helmet','SS',NULL,'','','http://ecx.images-amazon.com/images/I/511uvdkfqGL._AA160_FMwebp_QL70_.jpg'),('B00S1VN8RM','football','Nike Strike AEROWTRAC','Nike',NULL,'5','','http://ecx.images-amazon.com/images/I/51dd-8dRv9L._AA160_FMwebp_QL70_.jpg'),('B00UVB7MMC','cricket','Shrey Junior Cricket Helmet with Mild Steel Visor','Shrey',NULL,'','','http://ecx.images-amazon.com/images/I/41DOVFm232L._AA160_FMwebp_QL70_.jpg'),('B00WSAY4NE','tennis','Vicky Supreme Light Cricket Tennis Ball','Vicky',NULL,'','','http://ecx.images-amazon.com/images/I/41ZoGZxPlFL._AA160_FMwebp_QL70_.jpg'),('B00WWRXK7E','football','Nike AC1923F4-001 Ball Pump','Nike',NULL,'','black_blue','https://images-eu.ssl-images-amazon.com/images/I/411lGqlWzRL._SL500_SL130_.jpg'),('B0107R4EHU','football','Vector X Chaser-II Football shoes','Vector X',NULL,'9_10','Green','http://ecx.images-amazon.com/images/I/41GtB9ybsBL._AA160_FMwebp_QL70_.jpg'),('B0107R54HE','football','Vector X Dragon cricket shoes','Vector X',NULL,'7_8_9','','http://ecx.images-amazon.com/images/I/41JdKB6LTRL._AA160_FMwebp_QL70_.jpg'),('B0111YDWIK','cricket','Track Wood Cricket Stumps Full Size','TRACK',NULL,'','','http://ecx.images-amazon.com/images/I/415GvwsXX8L._AA160_.jpg'),('B013EADCCK','tennis','Wilson Federer Team 105 Tennis Racquet','Wilson',NULL,'12-inch','white','http://ecx.images-amazon.com/images/I/41JAVW0a%2BDL._AA160_.jpg'),('B015OV4NDO','cricket','Jaguar Men\'s Cricket Wickets Standard Light Brown','Jaguar',NULL,'','','http://ecx.images-amazon.com/images/I/31jMC5Q1wPL._AC_UL160_SR160,160_.jpg'),('B016K6929M','football','Kipsta F300 Goalkeeper, Gloves','kipsta',NULL,'8_11','','http://ecx.images-amazon.com/images/I/51wAK%2BqQurL._AC_UL160_SR160,160_.jpg'),('B017HH2CI6','football','Hytex D-Style Eva Sheet Football Shin Guard with Anklet, Medium ','Hytex',NULL,'medium_large','florescent-yellow_florescent-black','http://ecx.images-amazon.com/images/I/51r4xfspgvL._AA160_.jpg'),('B017HH2EL6','football','Hytex Explorer Tetron Football Shin Guard','Hytex','','medium_large','Florescent-green','http://ecx.images-amazon.com/images/I/51hs3iJreWL._AA160_.jpg'),('B017HH2IA8','football','Hytex Explorer Goalkeeper Gloves','Hytex',NULL,'medium_large','Florescent Green_Florescent Red','http://ecx.images-amazon.com/images/I/61lx34-gxhL._AA160_.jpg');
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

-- Dump completed on 2015-11-16 21:14:10
