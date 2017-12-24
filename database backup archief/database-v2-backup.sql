CREATE DATABASE  IF NOT EXISTS `breakout_v2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `breakout_v2`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: smashitdb.c8yw74jpzcli.eu-central-1.rds.amazonaws.com    Database: breakout_v2
-- ------------------------------------------------------
-- Server version	5.6.37-log

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
-- Table structure for table `brick`
--

DROP TABLE IF EXISTS `brick`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brick` (
  `name` varchar(30) NOT NULL,
  `brick_points` int(11) DEFAULT NULL,
  `brick_strength` int(11) DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL,
  `powerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`),
  KEY `powerID_idx` (`powerID`),
  CONSTRAINT `powerID` FOREIGN KEY (`powerID`) REFERENCES `power` (`powerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brick`
--

LOCK TABLES `brick` WRITE;
/*!40000 ALTER TABLE `brick` DISABLE KEYS */;
INSERT INTO `brick` VALUES ('FASTER',5,1,'U',7),('HARD_BRICK',12,3,'N',NULL),('LARGER_BALL',5,1,'U',3),('LARGER_PALET',5,1,'U',5),('LESS_POINTS',0,1,'D',2),('MEDIUM_BRICK',10,2,'N',NULL),('MORE_POINTS',5,2,'U',1),('SIMPLE_BRICK',5,1,'N',NULL),('SLOWER',0,2,'D',8),('SMALLER_BALL',0,2,'D',4),('SMALLER_PALET',0,2,'D',6);
/*!40000 ALTER TABLE `brick` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `difficulty`
--

DROP TABLE IF EXISTS `difficulty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `difficulty` (
  `difficultyID` int(11) NOT NULL,
  `number_powerups` int(11) DEFAULT NULL,
  `number_powerdowns` int(11) DEFAULT NULL,
  `rows` int(11) DEFAULT NULL,
  `speed_ball` double DEFAULT NULL,
  PRIMARY KEY (`difficultyID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `difficulty`
--

LOCK TABLES `difficulty` WRITE;
/*!40000 ALTER TABLE `difficulty` DISABLE KEYS */;
INSERT INTO `difficulty` VALUES (1,2,1,3,-7),(2,2,2,4,-9),(3,3,2,5,-9),(4,3,3,6,-10),(5,4,3,7,-11),(6,3,4,8,-12);
/*!40000 ALTER TABLE `difficulty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `niveau`
--

DROP TABLE IF EXISTS `niveau`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `niveau` (
  `niveauID` int(11) NOT NULL,
  `difficultyID` int(11) DEFAULT NULL,
  PRIMARY KEY (`niveauID`),
  KEY `difficultyID_idx` (`difficultyID`),
  CONSTRAINT `difficultyID` FOREIGN KEY (`difficultyID`) REFERENCES `difficulty` (`difficultyID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `niveau`
--

LOCK TABLES `niveau` WRITE;
/*!40000 ALTER TABLE `niveau` DISABLE KEYS */;
INSERT INTO `niveau` VALUES (1,1),(2,1),(3,2),(4,2),(5,3),(6,3),(7,3),(8,3),(9,3),(10,3),(11,4),(12,4),(13,4),(14,4),(15,4),(16,4),(17,4),(18,4),(19,4),(20,4),(21,4),(22,4),(23,4),(24,4),(25,4),(26,4),(27,4),(28,4),(29,4),(30,4),(31,4),(32,4),(33,4),(34,4),(35,4),(36,4),(37,4),(38,4),(39,4),(40,4),(46,4),(41,5),(42,5),(43,5),(44,5),(45,5),(47,5),(48,5),(49,5),(50,5),(51,5),(52,5),(53,5),(54,5),(55,5),(56,5),(57,5),(58,5),(59,5),(60,5),(61,5),(62,5),(63,5),(64,5),(65,5),(66,5),(67,5),(68,5),(69,5),(70,5),(71,5),(72,5),(73,5),(74,5),(75,5),(76,6),(77,6),(78,6),(79,6),(80,6),(81,6),(82,6),(83,6),(84,6),(85,6),(86,6),(87,6),(88,6),(89,6),(90,6),(91,6),(92,6),(93,6),(94,6),(95,6),(96,6),(97,6),(98,6),(99,6),(100,6);
/*!40000 ALTER TABLE `niveau` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `power`
--

DROP TABLE IF EXISTS `power`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `power` (
  `powerID` int(11) NOT NULL AUTO_INCREMENT,
  `power_type` varchar(60) NOT NULL,
  `value` double NOT NULL,
  PRIMARY KEY (`powerID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `power`
--

LOCK TABLES `power` WRITE;
/*!40000 ALTER TABLE `power` DISABLE KEYS */;
INSERT INTO `power` VALUES (1,'POINTS',2),(2,'POINTS',0.5),(3,'BALL_DIAMETER',15),(4,'BALL_DIAMETER',8),(5,'PALET_SIZE',80),(6,'PALET_SIZE',30),(7,'START_SPEED',-12),(8,'START_SPEED',-5);
/*!40000 ALTER TABLE `power` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scores`
--

DROP TABLE IF EXISTS `scores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scores` (
  `scoreID` int(11) NOT NULL AUTO_INCREMENT,
  `userWonID` varchar(30) NOT NULL,
  `userLostID` varchar(30) NOT NULL,
  `points` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`scoreID`),
  KEY `userWonID_idx` (`userWonID`),
  KEY `userLostID_idx` (`userLostID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scores`
--

LOCK TABLES `scores` WRITE;
/*!40000 ALTER TABLE `scores` DISABLE KEYS */;
/*!40000 ALTER TABLE `scores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userID` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `smashbit` int(11) DEFAULT '0',
  `username` varchar(20) NOT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `country` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('102140474039746410339','mattias.de.wael@howest.be',0,'Mattias De Wael','https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg','BE'),('102538855014174588423','republicempire21@gmail.com',0,'Brian D','https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg','BE'),('103854203354264371927','koen.cornelis@howest.be',0,'Koen Cornelis','https://lh4.googleusercontent.com/-TrJwbjO57lo/AAAAAAAAAAI/AAAAAAAAAAc/rV7U0tMsYfg/photo.jpg','BE'),('106218183858922771083','tomson842@gmail.com',0,'Gertje','https://lh3.googleusercontent.com/-kINGmjOnORI/AAAAAAAAAAI/AAAAAAAACyI/7g8AOt75Plg/photo.jpg','BE'),('106309359480181868182','jonas.anseel@gmail.com',0,'Jonas Anseel','https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg','BE'),('109172776861964489005','digosompyy@gmail.com',0,'Digo Gustavo','https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg','BE'),('112423951292529512066','wingcrowny@gmail.com',0,'Wing crony','https://lh4.googleusercontent.com/-FQo4xf1_vXA/AAAAAAAAAAI/AAAAAAAAAFo/jxdOpvrvBho/photo.jpg','BE'),('113984755971293914921','quinten.bombeke@student.howest.be',0,'Quinten Bombeke','https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg','BE'),('114113669011011242559','gensouh@gmail.com',0,'Ishan Winkel','https://lh5.googleusercontent.com/-s311KVGKfVA/AAAAAAAAAAI/AAAAAAAAAVk/VzdRCv5-9ew/s96-c/photo.jpg','BE'),('116744707883475130146','jonas.anseel@student.howest.be',0,'Jonas Anseel','https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg','BE'),('116930038354506290766','sven.depickere@gmail.com',0,'Steven Depickere','https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg','BE'),('118005811720494413254','lode.anseel@gmail.com',0,'LODE ANSEEL','https://lh3.googleusercontent.com/-XdUIqdMkCWA/AAAAAAAAAAI/AAAAAAAAAAA/4252rscbv5M/photo.jpg','BE');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-18 11:13:02
