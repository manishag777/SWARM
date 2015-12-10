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
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `events` (
  `sport_id` varchar(30) NOT NULL,
  `start_date` varchar(40) NOT NULL,
  `end_date` varchar(40) NOT NULL,
  `event_details` text NOT NULL,
  `location` varchar(40) NOT NULL,
  `interested_store_id` varchar(40) NOT NULL,
  KEY `event_index` (`sport_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
INSERT INTO `events` VALUES ('athletics','2015-01-25','2015-01-25','The adi Pro Run: Gurgaon','Gurgaon','delhi'),('football','2015-02-12','2015-02-12','Football Camp','Delhi','delhi'),('cycling','2016-03-13','2016-03-13','cyclathlon: Phulgaon','Gurgaon','delhi'),('athletics','2015-03-29','2015-03-29','8th Delhi Cross Country Race New Delhi','Delhi','delhi'),('skating','2015-04-04','2015-04-04','National Skates Event','Delhi','delhi'),('athletics','2015-05-03','2015-05-03','Run for Nepal','Gurgaon','delhi'),('board games','2015-05-10','2015-05-10','Chess For Charity','Delhi','delhi'),('cycling','2014-06-01','2014-06-01','de Delhi Tour Cycling','Delhi','delhi'),('cricket','2015-07-04','2015-07-04','Junior Cricket training camp','Delhi','delhi'),('swimming','2105-07-20','2105-07-20','Swimming Camp For Beginners','Delhi','delhi'),('athletics','2015-08-09','2015-08-09','Run for the nation','Delhi','delhi'),('cycling','2015-08-15','2015-08-15','Freedom Cycle Ride','Noida','delhi'),('skating','2015-09-01','2015-09-01','Delhi Skates ChampionShip','Delhi','delhi'),('athletics','2015-10-18','2015-10-18','The Great Delhi Half Marathon','Delhi','delhi'),('cricket','2015-11-01','2015-11-01','Cricket Learning Camp','Delhi','delhi');
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-10 23:23:44
