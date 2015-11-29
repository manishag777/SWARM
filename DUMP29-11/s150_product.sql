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
INSERT INTO `product` VALUES ('','football','Nivia Ball Pump Double Action','Nivia',NULL,'','','http://ecx.images-amazon.com/images/I/31LO3nwO0FL._AA160_FMwebp_QL70_.jpg'),('0VGANMX571','table tennis','Table-Tennis Post-Suit-Hi-Quality Training & Competition Table-Tennis Net with Clamp Post Set','Stiga','Table-Tennis Post-Suit-Hi-Quality Training & Competition Table-Tennis Net with Clamp Post Set','','','http://ecx.images-amazon.com/images/I/51IEUzLNxML._AA160_.jpg'),('1-2-3','cricket','PSC Scorer Kashmir Willow Cricket Bat, Short Handle','DSCS','<ul><li>Octoplus grip</li><li>Mid high blade</li><li>12 Piece cane handle</li><li>Contoured edge profile</li></ul>','small_large','black_blue','http://ecx.images-amazon.com/images/I/31MHQ4rkkUL._AA160_FMwebp_QL70_.jpg'),('13445','cricket','Pro Impact Sports - Poly Soft PVC Cricket Ball ','Pro Impact','','--','--','http://ecx.images-amazon.com/images/I/417Jq2sleaL._AA160_.jpg'),('16ONNEWYVM','table tennis','Koxton Table Tennis Table - Club','Koxton','Koxton Table Tennis Table - Club','','','http://ecx.images-amazon.com/images/I/41-Ytw5epTL._AA160_.jpg'),('1899334','cricket','SG Campus Inner Gloves','SG','','','','http://ecx.images-amazon.com/images/I/51O7qLC6zgL._AA160_FMwebp_QL70_.jpg'),('1CWB9Y1J1J','cycling','Vast Day And Night Vision Premium Quality Driving plus Eye Protection Goggles','Vast','Vast Day And Night Vision Premium Quality Driving plus Eye Protection Goggles','','','http://ecx.images-amazon.com/images/I/31sfFo4ZtFL._AA160_.jpg'),('1NV6ZJUSC9','skating','Oxelo Mid-Tattoo Skateboard (Gold)','Oxelo','Oxelo Mid-Tattoo Skateboard (Gold)','','','http://ecx.images-amazon.com/images/I/517acRa5GlL._AA160_.jpg'),('23234','cricket','SG Super League Batting Gloves','Sanspareils Greenlands','','Men RH_ Men LH','black _white','http://ecx.images-amazon.com/images/I/51baUVRfkKL._AA160_.jpg'),('24AE4JQYLC','table tennis','Donic Table Tennis Ball 3 Star 40+ Pack of 3 White','Donic','Donic Table Tennis Ball 3 Star 40+ Pack of 3 White','','','http://ecx.images-amazon.com/images/I/41s2gO-R-TL._AA160_.jpg'),('2T9Y1PL3V5','skating','Nivia Super Inline Skates','Nivia','Nivia Super Inline Skates','','','http://ecx.images-amazon.com/images/I/41T4CwIVmlL._AA160_.jpg'),('2Z1D8X60BT','swimming','Speedo Male Houston Aquashort (Navy)','Speedo','Speedo Male Houston Aquashort (Navy)','','','http://ecx.images-amazon.com/images/I/41wbmKsVepL._AA160_.jpg'),('31JQFINEV1','others','Nivia Radar Bottle','Nivia','Nivia Radar Bottle','','','http://ecx.images-amazon.com/images/I/31urME51bhL._AA160_.jpg'),('3232','cricket','SS College Men\'s Wicket Keeping Gloves (White/Black)','SS','','','','http://ecx.images-amazon.com/images/I/51O7qLC6zgL._AA160_FMwebp_QL70_.jpg'),('3234','cricket','Bas Vampire Super Batting Legguard, Youth','KG','','24_26','black_white','http://ecx.images-amazon.com/images/I/41Vg5VkcHML._AA160_FMwebp_QL70_.jpg'),('3E45584QCQ','board games','Funskool Pictureka Game','Funskool','Funskool Pictureka Game','','','http://ecx.images-amazon.com/images/I/51YfTbmAAgL._AA115_.jpg'),('3VJTGFKN0D','cycling','Hercules Ryders Contour Hi End Bicycle','Hercules','Hercules Ryders Contour Hi End Bicycle','','','http://ecx.images-amazon.com/images/I/51spxPQjosL._AA160_FMwebp_QL70_.jpg'),('43546','cricket','New Balance DC 480 Kashmir Willow Bat','New Balance','','','','http://ecx.images-amazon.com/images/I/31LxLxHO-CL._AA160_FMwebp_QL70_.jpg'),('4VXD8AE20Z','swimming','Viva Sports Viva 130 Swimming Goggles','Viva','Viva Sports Viva 130 Swimming Goggles','','','http://ecx.images-amazon.com/images/I/31QFWvjJj%2BL._AA160_.jpg'),('5RF0Z2LOZU','skating','Inline roller skate, shoe size 34-37 Euro, Medium','Puma','Inline roller skate, shoe size 34-37 Euro, Medium','','','http://ecx.images-amazon.com/images/I/61NimbNoNRL._AA160_.jpg'),('6F9CFUCJTA','swimming','Veloz Girls Swimwear - Frock - Plain Colour','Veloz','Veloz Girls Swimwear - Frock - Plain Colour','','','http://ecx.images-amazon.com/images/I/31J8LP4wUKL._AA160_.jpg'),('7OQCH333YO','table tennis','Stiga Cup Table Tennis Ball, Pack of 6 (Orange)','Stiga','Stiga Cup Table Tennis Ball, Pack of 6 (Orange)','','','http://ecx.images-amazon.com/images/I/41JmK8YDQ1L._AA160_.jpg'),('7STPL5F4T3','table tennis','Cosco Stiga Cup Table Tennis Training Ball, Pack of 6','Cosco','Cosco Stiga Cup Table Tennis Training Ball, Pack of 6','','','http://ecx.images-amazon.com/images/I/412y0g1q%2BHL._AA160_.jpg'),('7YMSNHGA7V','cycling','Bicycle Double sided Utility Bag','BikeStuff','Bicycle Double sided Utility Bag','','','http://ecx.images-amazon.com/images/I/51fnq8uftLL._AA160_.jpg'),('8FW7KQJVFJ','table tennis','Hi-Quality and Innovative Retractable Table-Tennis Net with Adjustable Length and Push Clamps','Stag','Hi-Quality and Innovative Retractable Table-Tennis Net with Adjustable Length and Push Clamps','','','http://ecx.images-amazon.com/images/I/51mPi0ydcCL._AA160_.jpg'),('8S7R63VCX2','board games','Stag Club Plywood C202 A Carrom Board, 4 inch','Stag','Stag Club Plywood C202 A Carrom Board, 4 inch','','','http://ecx.images-amazon.com/images/I/41u1uY2FsuL._AA160_.jpg'),('8T6SHSZC9T','others','Nike Sport Sipper Water Bottle','Nike','Nike Sport Sipper Water Bottle','','','http://ecx.images-amazon.com/images/I/31yytcSyvxL._AA160_.jpg'),('934893','cricket','ball','puma','','','','http://ecx.images-amazon.com/images/I/41COpGZTqeL._AA160_.jpg'),('9797L9ALM8','others','Princeware Aquaflow sports water bottle 750ml, Blue','Princeware','Princeware Aquaflow sports water bottle 750ml, Blue','','','http://ecx.images-amazon.com/images/I/31jLCEjcfmL._AA160_.jpg'),('9BSEVVB9FI','table tennis','GKI Long Life Table Tennis Ball, Pack of 12 (Yellow/White)','GKI','GKI Long Life Table Tennis Ball, Pack of 12 (Yellow/White)','','','http://ecx.images-amazon.com/images/I/419UeMRqjeL._AA160_.jpg'),('9GEJ1HHCLB','others','iShake Steel Water Bottle, 600 ml','Ishake','iShake Steel Water Bottle, 600 ml','','','http://ecx.images-amazon.com/images/I/31PCuMyf79L._AA160_.jpg'),('9IA5L3OUUG','swimming','Vector X Viva Lycra Swimming Cap','Vector X','Vector X Viva Lycra Swimming Cap','','','http://ecx.images-amazon.com/images/I/31EDqbqUjvL._AA160_.jpg'),('9JYU6WRZP4','skating','Cosco Zoomer Roller Skate','Cosco','Cosco Zoomer Roller Skate','','','http://ecx.images-amazon.com/images/I/51LK-vsI64L._AA160_.jpg'),('AMHE8I064B','skating','Hawk Zombie Skate Board, 24x6-inch Junior (Red/White)','Hawk','Hawk Zombie Skate Board, 24x6-inch Junior (Red/White)','','','http://ecx.images-amazon.com/images/I/41UjcsAVsWL._AA160_.jpg'),('B00426AHGA','tennis','Wilson Titanium 3 Tennis Ball, Pack of 3','Wilson',NULL,'','','http://ecx.images-amazon.com/images/I/41aKLWVPDKL._AA160_FMwebp_QL70_.jpg'),('B00AIJ3EN8','tennis','Babolat 140123-142 Aeropro Drive GT Strung Tennis Racquet,','Babolat',NULL,'','BLACK_YELLOW','http://ecx.images-amazon.com/images/I/51NmiSo4fgL._AA160_.jpg'),('B00I4O9D34','cricket','SM Rafter Leather Cricket Ball (Red)','SM',NULL,'','','http://ecx.images-amazon.com/images/I/51l6Mmr9thL._AC_UL160_SR160,160_.jpg'),('B00I7QXM2M','tennis','GKI Offensive Rago Table Tennis Racquet','GKI',NULL,'','','http://ecx.images-amazon.com/images/I/41NnqWFRFnL._AC_UL160_SR160,160_.jpg'),('B00I7QXQ96','tennis','GKI Nano Force Table Tennis Racquet','GKI',NULL,'','','http://ecx.images-amazon.com/images/I/41rWa-9xZjL._AC_UL160_SR160,160_.jpg'),('B00I7QXSEE','tennis','GKI Kung Fu DX Table Tennis Racquet','GKI',NULL,'','','http://ecx.images-amazon.com/images/I/51O%2BKot24dL._AC_UL160_SR160,160_.jpg'),('B00I7QXUJC','tennis','GKI Long Life Table Tennis Ball, Pack of 12','GKI',NULL,'','Yellow_white','http://ecx.images-amazon.com/images/I/419UeMRqjeL._AC_UL160_SR160,160_.jpg'),('B00I9Y41TA','football','Nivia Vega Football','Nivia',NULL,'4_5','','http://ecx.images-amazon.com/images/I/51CJFtMmQnL._AA160_FMwebp_QL70_.jpg'),('B00IAP3W3Y','tennis','Head Ti Reward Club Series Tennis Racquet','HEAD',NULL,'','','http://ecx.images-amazon.com/images/I/41nYsgHxRTL._AA160_.jpg'),('B00ICCYF0E','football','Nivia Storm Football','Nivia',NULL,'5_6','white_black','http://ecx.images-amazon.com/images/I/51uPBBOwGuL._AA160_FMwebp_QL70_.jpg'),('B00ID6OUH2','football','Cosco Mundial Foot Ball','Cosco',NULL,'5','','http://ecx.images-amazon.com/images/I/41zvE6FcEIL._AA160_FMwebp_QL70_.jpg'),('B00ID6OY4Q','tennis','Wilson Match Point XL 3 Tennis Racquet','Wilson',NULL,'Regular','Orange','http://ecx.images-amazon.com/images/I/410FPS2aktL._AA160_.jpg'),('B00ID6P3V4','football','Cosco Hand Pump','Cosco',NULL,'','','http://ecx.images-amazon.com/images/I/21DK3Kw-V4L._AA160_FMwebp_QL70_.jpg'),('B00ID6PG7K','tennis','Cosco Stiga Cup Table Tennis Training Ball, Pack of 6','Cosco','','','','http://ecx.images-amazon.com/images/I/41JmK8YDQ1L._AC_UL160_SR160,160_.jpg'),('B00ID6PGKC','tennis','Cosco Stiga Competition Table Tennis Ball, Pack of 3','Cosco',NULL,'','','http://ecx.images-amazon.com/images/I/413uXOcvGTL._AC_UL160_SR160,160_.jpg'),('B00ID8BITS','cricket','SG Club Leather Ball (White)','SG','','','','http://ecx.images-amazon.com/images/I/4122JucO4QL._AC_UL160_SR160,160_.jpg'),('B00IE3ANKM','cricket','Cosco Club Batting Leg Guard','Cosco','','','','http://ecx.images-amazon.com/images/I/51z8yvmt0QL._AC_UL160_SR130,160_.jpg'),('B00INTOOMK','cricket','GM Glass Fibre Bat Tape, 25mmX10m','GM',NULL,'','','http://ecx.images-amazon.com/images/I/51Xvo-AyXNL._AC_UL160_SR129,160_.jpg'),('B00INTOOUW','cricket','GM Octane Autograph Cricket Bat, 17 inch','GM',NULL,'17 inch','','http://ecx.images-amazon.com/images/I/21baJ5EcgqL._AA160_.jpg'),('B00JQK97O0','tennis','Wilson Australian Open Tennis Ball ','Wilson',NULL,'','','http://ecx.images-amazon.com/images/I/31yUyLcIgtL._AA160_FMwebp_QL70_.jpg'),('B00KBLUJX6','football','Adidas Brazuca Train Pro Football','Adidas',NULL,'4_5','white_yellow','http://ecx.images-amazon.com/images/I/51W%2BzkW4ZtL._AA160_FMwebp_QL70_.jpg'),('B00LN59I7C','football','Nivia Street Football','Nivia',NULL,'4_5','black_blue','http://ecx.images-amazon.com/images/I/615qc1ulY0L._AA160_FMwebp_QL70_.jpg'),('B00LTE6I98','cricket','Ceela Sports League Special Cricket Ball(White)','Ceela Sports',NULL,'','','http://ecx.images-amazon.com/images/I/41XA1ILJJTL._AC_UL160_SR153,160_.jpg'),('B00LTP988A','cricket','SG T20i Pro Cricket Helmet','SG',NULL,'','','http://ecx.images-amazon.com/images/I/51rXwbIPZjL._AA160_FMwebp_QL70_.jpg'),('B00MWOJTA4','cricket','SS Magnum English Willow Cricket Bat',' SS',NULL,'','','http://ecx.images-amazon.com/images/I/318yCfUkYOL._AC_UL160_SR160,160_.jpg'),('B00N2U8DY0','football','Nivia Web Goal-keeper Gloves ','',NULL,'7_8','white_blue','http://ecx.images-amazon.com/images/I/51pSNDxGijL._AC_UL160_SR160,160_.jpg'),('B00ORFTLAE','tennis','NIVIA G-21 Aluminium Tennis Racquet, 21-Inch','NIVIA',NULL,'','green_black','http://ecx.images-amazon.com/images/I/31lVuL1t3wL._AA160_.jpg'),('B00P0IJLRA','football','Cosco Rio Football','cosco','','4_5','green_yellow','http://ecx.images-amazon.com/images/I/51MHBpTD8hL._AA160_FMwebp_QL70_.jpg'),('B00PUHPIAK','tennis','Stag Three Star Table Tennis Balls , 40mm Pack of 3',' DONIC',NULL,'40 mm','Orange','http://ecx.images-amazon.com/images/I/51IH-3FpTWL._AC_UL160_SR137,160_.jpg'),('B00Q83H6GE','tennis','Stag Play Anywhere TT Set','Stag',NULL,'','','http://ecx.images-amazon.com/images/I/51SgmNW98sL._AA160_FMwebp_QL70_.jpg'),('B00R11KAAQ','cricket','SS Heritage Cricket Helmet','SS',NULL,'','','http://ecx.images-amazon.com/images/I/511uvdkfqGL._AA160_FMwebp_QL70_.jpg'),('B00S1VN8RM','football','Nike Strike AEROWTRAC','Nike',NULL,'5','','http://ecx.images-amazon.com/images/I/51dd-8dRv9L._AA160_FMwebp_QL70_.jpg'),('B00UVB7MMC','cricket','Shrey Junior Cricket Helmet with Mild Steel Visor','Shrey',NULL,'','','http://ecx.images-amazon.com/images/I/41DOVFm232L._AA160_FMwebp_QL70_.jpg'),('B00WSAY4NE','tennis','Vicky Supreme Light Cricket Tennis Ball','Vicky',NULL,'','','http://ecx.images-amazon.com/images/I/41ZoGZxPlFL._AA160_FMwebp_QL70_.jpg'),('B00WWRXK7E','football','Nike AC1923F4-001 Ball Pump','Nike',NULL,'','black_blue','https://images-eu.ssl-images-amazon.com/images/I/411lGqlWzRL._SL500_SL130_.jpg'),('B0107R4EHU','football','Vector X Chaser-II Football shoes','Vector X',NULL,'9_10','Green','http://ecx.images-amazon.com/images/I/41GtB9ybsBL._AA160_FMwebp_QL70_.jpg'),('B0107R54HE','football','Vector X Dragon cricket shoes','Vector X',NULL,'7_8_9','','http://ecx.images-amazon.com/images/I/41JdKB6LTRL._AA160_FMwebp_QL70_.jpg'),('B0111YDWIK','cricket','Track Wood Cricket Stumps Full Size','TRACK',NULL,'','','http://ecx.images-amazon.com/images/I/415GvwsXX8L._AA160_.jpg'),('B013EADCCK','tennis','Wilson Federer Team 105 Tennis Racquet','Wilson',NULL,'12-inch','white','http://ecx.images-amazon.com/images/I/41JAVW0a%2BDL._AA160_.jpg'),('B015OV4NDO','cricket','Jaguar Men\'s Cricket Wickets Standard Light Brown','Jaguar',NULL,'','','http://ecx.images-amazon.com/images/I/31jMC5Q1wPL._AC_UL160_SR160,160_.jpg'),('B016K6929M','football','Kipsta F300 Goalkeeper, Gloves','kipsta',NULL,'8_11','','http://ecx.images-amazon.com/images/I/51wAK%2BqQurL._AC_UL160_SR160,160_.jpg'),('B017HH2CI6','football','Hytex D-Style Eva Sheet Football Shin Guard with Anklet, Medium ','Hytex',NULL,'medium_large','florescent-yellow_florescent-black','http://ecx.images-amazon.com/images/I/51r4xfspgvL._AA160_.jpg'),('B017HH2EL6','football','Hytex Explorer Tetron Football Shin Guard','Hytex','','medium_large','Florescent-green','http://ecx.images-amazon.com/images/I/51hs3iJreWL._AA160_.jpg'),('B017HH2IA8','football','Hytex Explorer Goalkeeper Gloves','Hytex',NULL,'medium_large','Florescent Green_Florescent Red','http://ecx.images-amazon.com/images/I/61lx34-gxhL._AA160_.jpg'),('BEXXXHLGFP','swimming','Zobello Men\'s Colorblock Quick Dry Nylon Swim Shorts','Zobello','Zobello Men\'s Colorblock Quick Dry Nylon Swim Shorts','','','http://ecx.images-amazon.com/images/I/41P4fe12HwL._AA160_.jpg'),('BM8JYYNYYJ','swimming','Soft Silicone Ear Plugs','SAJ','Soft Silicone Ear Plugs','','','http://ecx.images-amazon.com/images/I/41JCO%2BbkBwL._AA160_FMwebp_QL70_.jpg'),('C2EU89DOQR','board games','Hasbro Games Taboo Board Game','Hasbro','Hasbro Games Taboo Board Game','','','http://ecx.images-amazon.com/images/I/41tmBDix-lL._AA115_.jpg'),('CNLKWTFYDU','board games','Funskool the Game of Life','Funskool','Funskool the Game of Life','','','http://ecx.images-amazon.com/images/I/51j8gGIfUlL._AA115_.jpg'),('CXIGMQQY31','table tennis','Stag Power Drive Plus Table Tennis Racquet','Stag','Stag Power Drive Plus Table Tennis Racquet','','','http://ecx.images-amazon.com/images/I/51hJWKJmXEL._AA160_.jpg'),('DDH95KHX3G','swimming','Speedo Adult Plain Flat Silicone Cap','Speedo','Speedo Adult Plain Flat Silicone Cap','','','http://ecx.images-amazon.com/images/I/31%2BAk6qnvlL._AA160_.jpg'),('DDYJ5YUAIL','board games','KDM M-24 Unisex Carrom Board Wooden 23 x 23 Inch','KDM','KDM M-24 Unisex Carrom Board Wooden 23 x 23 Inch','','','http://ecx.images-amazon.com/images/I/51TPK3igu4L._AA160_.jpg'),('FDYP6OGRYH','table tennis','Tennex Table Tennis Racquet T444','Tennex','Tennex Table Tennis Racquet T444','','','http://ecx.images-amazon.com/images/I/515O96VSnnL._AA160_.jpg'),('GNQISTT3PJ','swimming','Speedo Legsuit, Women\'s Size 34 (Black/Ecstatic )','Speedo','Speedo Legsuit, Women\'s Size 34 (Black/Ecstatic )','','','http://ecx.images-amazon.com/images/I/31DVjPRCjDL._AA160_.jpg'),('GSME8Y22HH','skating','Nivia Skateboard','Nivia','Nivia Skateboard','','','http://ecx.images-amazon.com/images/I/31CkZn9bSxL._AA160_.jpg'),('GWVKWNN8ZH','cycling','Hero Xcello Integra 26T Single Speed Mountain Bike, Men\'s (Red/Black)','Hero','Hero Xcello Integra 26T Single Speed Mountain Bike, Men\'s (Red/Black)','','','http://ecx.images-amazon.com/images/I/51JGH8dbi3L._AA160_FMwebp_QL70_.jpg'),('HB2UMZSUSX','skating','Cosco Tenacity Super Roller Skate, Junior','Cosco','Cosco Tenacity Super Roller Skate, Junior','','','http://ecx.images-amazon.com/images/I/51q2rTa101L._AA160_.jpg'),('HJ8NA2E937','board games','World Safari: An Exciting Expedition across the World','Funskool','World Safari: An Exciting Expedition across the World','','','http://ecx.images-amazon.com/images/I/61znjoumEoL._AA115_.jpg'),('HPQ77D2W47','cycling','Hero Cycles Ranger Dtb Vx 6 Speed Mountain Bike','Hero','Hero Cycles Ranger Dtb Vx 6 Speed Mountain Bike','','','http://ecx.images-amazon.com/images/I/51HwTu9LqyL._AA160_FMwebp_QL70_.jpg'),('HVGNP5FLPY','swimming','Quiksilver Men\'s Board Shorts','QuickSilver','Quiksilver Men\'s Board Shorts','','','http://ecx.images-amazon.com/images/I/317c%2BDgnV1L._AA160_.jpg'),('I0F0SLAI64','swimming','SPEEDO ENDURANCE+ JAMMER - BLACK (Male)','Speedo','SPEEDO ENDURANCE+ JAMMER - BLACK (Male)','','','http://ecx.images-amazon.com/images/I/41h2sMpoaxL._AA160_.jpg'),('IFOXXHA6VE','board games','Shopaholic Portable Folding Magnetic Chess Board - 3810','Shopaholic','Shopaholic Portable Folding Magnetic Chess Board - 3810','','','http://ecx.images-amazon.com/images/I/51UopexrJjL._AA160_.jpg'),('IYG5K4USU4','table tennis','Stag Play Set Table Tennis','Stag','Stag Play Set Table Tennis','','','http://ecx.images-amazon.com/images/I/51oTITNG4VL._AA160_.jpg'),('J3Q7BBSFPV','table tennis','GKI Kung Fu DX Table Tennis Racquet','GKi','GKI Kung Fu DX Table Tennis Racquet','','','http://ecx.images-amazon.com/images/I/511lTW2vwAL._AA160_.jpg'),('JJBH8D6OP3','skating','Strauss Bronx BW Skate Board','Puma','Strauss Bronx BW Skate Board','','','http://ecx.images-amazon.com/images/I/41wY%2BsQQjdL._AA160_.jpg'),('JLNW66YSPJ','swimming','Cosco Aqua Star Swimming Goggle, Senior','Cosco','Cosco Aqua Star Swimming Goggle, Senior','','','http://ecx.images-amazon.com/images/I/31ncp%2B%2BnHCL._AA160_.jpg'),('JM661E17VX','board games','Hasbro Monopoly Electronic Banking Board Game','hasbro','Hasbro Monopoly Electronic Banking Board Game','','','http://ecx.images-amazon.com/images/I/61BEJO7Vq9L._AA115_.jpg'),('K1KTS4IWPM','table tennis','GKI Fasto Table Tennis Racquet','GKI','GKI Fasto Table Tennis Racquet','','','http://ecx.images-amazon.com/images/I/31XE3bZkJrL._AA160_.jpg'),('KPTQ1AAX3H','skating','2015 New Design Two-wheel Smart Self Drifting Scooter Electric Scooter in Black','Rolling Stone','2015 New Design Two-wheel Smart Self Drifting Scooter Electric Scooter in Black','','','http://ecx.images-amazon.com/images/I/41jWE5JhTGL._AA160_.jpg'),('KUF21LIXWN','skating','Cosco Tenacity Super Roller Skate, Senior','Cosco','Cosco Tenacity Super Roller Skate, Senior','','','http://ecx.images-amazon.com/images/I/51rwK4V68XL._AA160_.jpg'),('KVU3UQ3UTR','table tennis','Cosco Stiga Competition Table Tennis Ball, Pack of 3','Cosco','Cosco Stiga Competition Table Tennis Ball, Pack of 3','','','http://ecx.images-amazon.com/images/I/413uXOcvGTL._AA160_.jpg'),('KZE50LQ3X4','board games','Funskool Chess','Funskool','Funskool Chess','','','http://ecx.images-amazon.com/images/I/51OWA974ckL._AA160_.jpg'),('ME14GL9L9U','table tennis','Butterfly Table Tennis Racket Wakaba 1000','Butterfly','Butterfly Table Tennis Racket Wakaba 1000','','','http://ecx.images-amazon.com/images/I/51BW62M2AbL._AA160_.jpg'),('MHJJ0D4XRT','cycling','Sigma Sport Germany Sigma Sport Micro Ii, White/Led White Cycle Safety Light','Sigma','Sigma Sport Germany Sigma Sport Micro Ii, White/Led White Cycle Safety Light','','','http://ecx.images-amazon.com/images/I/41mmK6x9pDL._AA160_FMwebp_QL70_.jpg'),('NOPJ19F5G7','cycling','Montra Rock1.1 Hi End Bicycle (Blue)','Montra','Montra Rock1.1 Hi End Bicycle (Blue)','','','http://ecx.images-amazon.com/images/I/510ZqNiVCFL._AA160_FMwebp_QL70_.jpg'),('OM2SD1G4FD','cycling','Btwin My Bike Mountain Bike, White','Btwin','Btwin My Bike Mountain Bike, White','','','http://ecx.images-amazon.com/images/I/41cBSVLy-NL._AA160_FMwebp_QL70_.jpg'),('OVGAL9RO8F','board games','Mattel Scrabble Board Game, Multi Color','Mattel','Mattel Scrabble Board Game, Multi Color','','','http://ecx.images-amazon.com/images/I/41X62RYBqOL._AA115_.jpg'),('P49RYN0Z3P','cycling','Hawk Cycling Helmet Kids','Hawk','Hawk Cycling Helmet Kids','','','http://ecx.images-amazon.com/images/I/41XQzt3O0eL._AA160_FMwebp_QL70_.jpg'),('P80VTKU4DH','table tennis','DEUCE 801 IN TABLE TENNIS TABLE','FieldSheer','DEUCE 801 IN TABLE TENNIS TABLE','','','http://ecx.images-amazon.com/images/I/41vFCuukAOL._AA160_.jpg'),('Q0PDU4I94B','board games','Funskool Cluedo','Funskool','Funskool Cluedo','','','http://ecx.images-amazon.com/images/I/513clniRO3L._AA115_.jpg'),('T3ZZ8KL5L1','others','Pigeon Playboy Sport Water Bottle, 750ml','Pigeon','Pigeon Playboy Sport Water Bottle, 750ml','','','http://ecx.images-amazon.com/images/I/31MSWSbOy%2BL._AA160_.jpg'),('T89OENSY5I','table tennis','Stag 2 Star Table Tennis Racquet','Stag','Stag 2 Star Table Tennis Racquet','','','http://ecx.images-amazon.com/images/I/41SfCLF7qwL._AA160_.jpg'),('TMZGWZ6AG8','swimming','Womens Swimwear - Frock With Half Leg With Sleeves - (EG) Plain','Champ','Womens Swimwear - Frock With Half Leg With Sleeves - (EG) Plain','','','http://ecx.images-amazon.com/images/I/41bANcdUjiL._AA160_.jpg'),('TVLY554LN3','skating','Hawk Senior Bat Skate Board, 31x8-inch (Orange)','Hawk','Hawk Senior Bat Skate Board, 31x8-inch (Orange)','','','http://ecx.images-amazon.com/images/I/4136GMNoDZL._AA160_.jpg'),('U1AHQPPZAQ','board games','Wood O Plast Chess Box Set 12 inch','Nivia','Wood O Plast Chess Box Set 12 inch','','','http://ecx.images-amazon.com/images/I/41S4D%2BqbsWL._AA160_.jpg'),('UHKS4WIZXD','board games','17\'\' x 17\'\' Tournament Vinyl Foldable Chess Set With Plastic Staunton Pieces','StoneKraft','17\'\' x 17\'\' Tournament Vinyl Foldable Chess Set With Plastic Staunton Pieces','','','http://ecx.images-amazon.com/images/I/416R0w298LL._AA160_.jpg'),('UIX7XUOROU','cycling','Burn 400745 Bicycle Stand (Black)','Burn','Burn 400745 Bicycle Stand (Black)','','','http://ecx.images-amazon.com/images/I/31Wx5oWLLDL._AA160_.jpg'),('UQRNF0E9WX','swimming','Cosco 25003 Silicone Swimming Cap (Multicolor)','Cosco','Cosco 25003 Silicone Swimming Cap (Multicolor)','','','http://ecx.images-amazon.com/images/I/41wcuLFt3iL._AA160_.jpg'),('VD3Y9889KD','swimming','Speedo Futura Plus Goggles, Men\'s Free Size (Clear/Blue)','Speedo','Speedo Futura Plus Goggles, Men\'s Free Size (Clear/Blue)','','','http://ecx.images-amazon.com/images/I/41lwWoanjJL._AA160_.jpg'),('VV1ZGBMQ8J','board games','Carrom Board 24mm by OSEL','Osel','Carrom Board 24mm by OSEL','','','http://ecx.images-amazon.com/images/I/518SuULMEjL._AA160_.jpg'),('W88ZSKFBTL','table tennis','Artengo Artengo Fb800 Table Tennis Balls Pack Of 6 White','Artengo','Artengo Artengo Fb800 Table Tennis Balls Pack Of 6 White','','','http://ecx.images-amazon.com/images/I/41kyKlkLUgL._AA160_.jpg'),('WKMSH931YZ','board games','Funskool Chess Classic','Funskool','Funskool Chess Classic','','','http://ecx.images-amazon.com/images/I/51OhRzxw%2BpL._AA160_.jpg'),('XE2BV9J6D6','board games','Koxton Carrom Board','Koxton','Koxton Carrom Board','','','http://ecx.images-amazon.com/images/I/41xKBNgwg2L._AA160_.jpg'),('XGLVZBGG24','skating','Jonex Tenacity Roller Skates','Jonex','Jonex Tenacity Roller Skates','','','http://ecx.images-amazon.com/images/I/41iwWXpWNCL._AA160_.jpg'),('XLKDMV3PMQ','table tennis','Stag 5 Star Table Tennis Racquet','Stag','Stag 5 Star Table Tennis Racquet','','','http://ecx.images-amazon.com/images/I/411dX5RwajL._AA160_.jpg'),('Y5KH5ENL4A','cycling','Hercules Roadeo A100 Vx 21 Speed Bicycle','Hercules','Hercules Roadeo A100 Vx 21 Speed Bicycle','','','http://ecx.images-amazon.com/images/I/51Xl3YaRGmL._AA160_FMwebp_QL70_.jpg'),('Y7PMYH942E','skating','Cosco Sprint Roller Skates','Cosco','Cosco Sprint Roller Skates','','','http://ecx.images-amazon.com/images/I/51sBsIHXS9L._AA160_.jpg'),('YB1Z1BDG6V','swimming','Speedo Biofuse Aquatic Earplug, Free Size (Grey/Blue)','Speedo','Speedo Biofuse Aquatic Earplug, Free Size (Grey/Blue)','','','http://ecx.images-amazon.com/images/I/31h3c14onIL._AA160_FMwebp_QL70_.jpg'),('YJ84NK8W1S','cycling','High Quality Bicycle Silicone Saddle Seat & Cycling Cushion Pad Bike Gel Cover','pandaWill','High Quality Bicycle Silicone Saddle Seat & Cycling Cushion Pad Bike Gel Cover','','','http://ecx.images-amazon.com/images/I/41yYB5IJmrL._AA160_.jpg'),('YUKQPK9BPL','cycling','Hero Sprint 20T Elite 6 Speed Junior Cycle, Boy\'s','Hero','Hero Sprint 20T Elite 6 Speed Junior Cycle, Boy\'s','','','http://ecx.images-amazon.com/images/I/51SGwM5NY6L._AA160_FMwebp_QL70_.jpg'),('ZERTSW5QMZ','swimming','Speedo Essential Spliced Kneesuit for Women','Speedo','Speedo Essential Spliced Kneesuit for Women','','','http://ecx.images-amazon.com/images/I/4100Ci6GJ%2BL._AA160_.jpg');
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

-- Dump completed on 2015-11-29 20:08:47
