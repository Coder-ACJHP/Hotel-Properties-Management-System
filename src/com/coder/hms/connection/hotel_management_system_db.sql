-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: hotel_managment_system
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `CustomerId` bigint(100) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `Country` varchar(45) NOT NULL,
  `Document` varchar(45) DEFAULT NULL,
  `DocumentNo` varchar(45) DEFAULT NULL,
  `DateOfBirth` datetime DEFAULT NULL,
  `Gender` varchar(45) DEFAULT NULL,
  `MaritalStatus` varchar(45) DEFAULT 'SINGLE',
  `FatherName` varchar(45) DEFAULT NULL,
  `MotherName` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `ReservationId` bigint(100) NOT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CustomerId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'AÇELYA','IŞIK','Iraq',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,NULL),(2,'FATİH','PORTAKAL','TURKEY','PASSPORT','X7AS096H','1990-08-05 00:00:00','MALE','SINGLE',NULL,NULL,NULL,1,NULL),(3,'HASAN','ÖZEN','TURKEY','PASSPORT','C769JHY8','1988-09-14 00:00:00','MALE','MARRIED',NULL,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Hotel`
--

DROP TABLE IF EXISTS `Hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Hotel` (
  `Name` varchar(50) NOT NULL,
  `Owner` varchar(45) DEFAULT NULL,
  `Address` varchar(100) NOT NULL,
  `PhoneNumber` varchar(45) NOT NULL,
  `RoomCapacity` int(11) NOT NULL,
  `RoomTypes` varchar(50) NOT NULL,
  `Id` bigint(25) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Hotel`
--

LOCK TABLES `Hotel` WRITE;
/*!40000 ALTER TABLE `Hotel` DISABLE KEYS */;
INSERT INTO `Hotel` VALUES ('Coder ACJHP Hotel','Onur Işik','Gümüşsuyu Mahallesi, Taksim Myd. No:21, 34437 Istanbul/Beyoğlu/İstanbul','(0212) 243 89 00',48,'SINGLE DOUBLE TWIN TRIPLE',1);
/*!40000 ALTER TABLE `Hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Payment`
--

DROP TABLE IF EXISTS `Payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Payment` (
  `Id` bigint(100) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `paymentType` varchar(45) DEFAULT NULL,
  `price` varchar(45) DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `explanation` varchar(45) DEFAULT NULL,
  `roomNumber` varchar(45) DEFAULT NULL,
  `dateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Payment`
--

LOCK TABLES `Payment` WRITE;
/*!40000 ALTER TABLE `Payment` DISABLE KEYS */;
INSERT INTO `Payment` VALUES (1,'BALANCE','CASH PAYMENT','150','TURKISH LIRA','','8003','2017-08-05 04:17:00'),(2,'BALANCE','CASH PAYMENT','150','TURKISH LIRA','DEPOSİTE','1001','2017-08-05 04:23:13');
/*!40000 ALTER TABLE `Payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Posting`
--

DROP TABLE IF EXISTS `Posting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Posting` (
  `Id` bigint(100) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `postType` varchar(45) DEFAULT 'SYSTEM',
  `price` varchar(45) DEFAULT NULL,
  `currency` varchar(45) DEFAULT NULL,
  `explanation` varchar(45) DEFAULT NULL,
  `roomNumber` varchar(45) DEFAULT NULL,
  `dateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Posting`
--

LOCK TABLES `Posting` WRITE;
/*!40000 ALTER TABLE `Posting` DISABLE KEYS */;
/*!40000 ALTER TABLE `Posting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reservation`
--

DROP TABLE IF EXISTS `Reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reservation` (
  `Id` bigint(100) NOT NULL AUTO_INCREMENT,
  `theNumber` varchar(45) NOT NULL,
  `checkinDate` date NOT NULL,
  `checkoutDate` date NOT NULL,
  `paymentStatus` tinyint(4) NOT NULL,
  `hostType` varchar(45) NOT NULL,
  `groupName` varchar(75) NOT NULL,
  `totalDays` int(11) DEFAULT NULL,
  `agency` varchar(50) NOT NULL,
  `creditType` varchar(45) NOT NULL,
  `bookStatus` varchar(45) NOT NULL,
  `note` varchar(500) DEFAULT NULL,
  `isCheckedIn` varchar(45) NOT NULL DEFAULT 'NO',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id_UNIQUE` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reservation`
--

LOCK TABLES `Reservation` WRITE;
/*!40000 ALTER TABLE `Reservation` DISABLE KEYS */;
INSERT INTO `Reservation` VALUES (1,'1004','2017-08-05','2017-08-06',0,'B.B','ŞEVKAT YERİMDAR',1,'WALKIN','STANDART CUSTOMER CREDIT','GUARANTEE','','YES'),(2,'1001','2017-08-05','2017-08-06',1,'B.B','AÇELYA IŞIK',1,'WALKIN','STANDART CUSTOMER CREDIT','GUARANTEE','','NO');
/*!40000 ALTER TABLE `Reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Room`
--

DROP TABLE IF EXISTS `Room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Room` (
  `theRoomId` bigint(100) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) NOT NULL,
  `type` varchar(50) NOT NULL,
  `price` double DEFAULT '0',
  `totalPrice` double DEFAULT '0',
  `balance` double DEFAULT '0',
  `cleaningStatus` varchar(100) DEFAULT 'CLEAN',
  `usageStatus` varchar(45) DEFAULT 'EMPTY',
  `personCount` int(20) DEFAULT '0',
  `customerGrupName` varchar(150) DEFAULT NULL,
  `ReservationId` bigint(100) NOT NULL,
  `currency` varchar(45) DEFAULT 'TURKISH LIRA',
  `remainingDebt` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`theRoomId`),
  UNIQUE KEY `theRoomId_UNIQUE` (`theRoomId`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Room`
--

LOCK TABLES `Room` WRITE;
/*!40000 ALTER TABLE `Room` DISABLE KEYS */;
INSERT INTO `Room` VALUES (1,'1001','DOUBLE',150,150,150,'CLEAN','FULL',1,'AÇELYA IŞIK',2,'TURKISH LIRA',NULL),(2,'1002','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(3,'1003','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(4,'2001','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(5,'2002','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(6,'2003','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(7,'3001','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(8,'3002','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(9,'3003','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(10,'4001','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(11,'4002','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(12,'4003','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(13,'5001','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(14,'5002','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(15,'5003','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(16,'6001','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(17,'6002','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(18,'6003','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(19,'7001','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(20,'7002','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(21,'7003','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(22,'8001','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(23,'8002','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(24,'8003','DOUBLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(25,'1004','TWIN',300,300,0,'DIRTY','FULL',2,'ŞEVKAT YERİMDAR',1,'TURKISH LIRA',NULL),(26,'1005','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(27,'2004','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(28,'2005','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(29,'3004','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(30,'3005','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(31,'4004','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(32,'4005','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(33,'5004','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(34,'5005','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(35,'6004','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(36,'6005','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(37,'7004','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(38,'7005','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(39,'8004','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(40,'8005','TWIN',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(41,'1006','TRIPLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(42,'2006','TRIPLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(43,'3006','TRIPLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(44,'4006','TRIPLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(45,'5006','TRIPLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(46,'6006','TRIPLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(47,'7006','TRIPLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL),(48,'8006','TRIPLE',0,0,0,'CLEAN','EMPTY',0,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `Room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `Id` bigint(100) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `NickName` varchar(60) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Email` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,'Onur','Işik','Coder ACJHP','coder958','hexa.octabin@gmaik.com'),(2,'System','Creator','admin','admin','system@gmail.com');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-05  5:25:34
