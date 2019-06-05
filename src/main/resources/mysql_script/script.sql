-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: quiz_db
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `testingId` int(11) NOT NULL,
  `optionId` int(11) NOT NULL,
  `option_id` int(11) DEFAULT NULL,
  `testing_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_answer_testing1_idx` (`testingId`),
  KEY `fk_answer_options1_idx` (`optionId`),
  CONSTRAINT `fk_answer_options1` FOREIGN KEY (`optionId`) REFERENCES `options` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_answer_testing1` FOREIGN KEY (`testingId`) REFERENCES `testing` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `combination`
--

DROP TABLE IF EXISTS `combination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `combination` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `test_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_combination_question1_idx` (`question_id`),
  KEY `fk_combination_test1_idx` (`test_id`),
  CONSTRAINT `fk_combination_question1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `fk_combination_test1` FOREIGN KEY (`test_id`) REFERENCES `test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combination`
--

LOCK TABLES `combination` WRITE;
/*!40000 ALTER TABLE `combination` DISABLE KEYS */;
INSERT INTO `combination` (`id`, `question_id`, `test_id`) VALUES (1,12,1),(2,13,1),(3,14,1),(4,12,2),(5,14,2),(6,15,2);
/*!40000 ALTER TABLE `combination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `options` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `is_true` tinyint(1) NOT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_options_question1_idx` (`question_id`),
  CONSTRAINT `fk_options_question1` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` (`id`, `text`, `is_true`, `question_id`) VALUES (12,'da',0,12),(13,'ne',1,12),(14,'proba',0,13),(15,'proba2',0,13),(16,'proba3',1,13),(17,'asddsa',1,14),(18,'asdasd',0,14),(19,'aqqwe',0,14),(20,'test1',0,15),(21,'test2',0,15),(22,'test3',0,15),(23,'test4',1,15),(24,'odgovor1',0,25),(25,'asdasd',0,26),(26,'aaasd',0,27),(27,'test1',0,28),(28,'wer,test1',0,29),(29,'qwe,wer',0,30),(30,'12345',0,31),(31,'23456',0,31),(32,'aass',0,32),(33,'aaa',0,32),(34,'zxczxcz',0,33),(35,'zxczxc',0,33);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `level` enum('1','2','3','4','5') DEFAULT NULL,
  `topic_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_topic1_idx` (`topic_id`),
  CONSTRAINT `fk_question_topic1` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` (`id`, `text`, `level`, `topic_id`) VALUES (12,'prasanje1',NULL,1),(13,'prasanje2',NULL,1),(14,'prasanje3',NULL,1),(15,'prasanje4',NULL,2),(16,'prasanje7',NULL,3),(17,'prasanje8',NULL,3),(18,'prasanje9',NULL,4),(19,'prasanje10',NULL,4),(20,'prasanje11',NULL,4),(21,'prasanje12',NULL,4),(22,'prasanje13',NULL,2),(23,'prasanje14',NULL,3),(24,'asdasd','4',2),(25,'prasanjeTest','3',9),(26,'asdasd','3',8),(27,'aaaaa','4',9),(28,'testtest','3',7),(29,'qwert,testtest','3',9),(30,'asd','4',9),(31,'asdfdsgfh','3',9),(32,'asdasdasd','5',9),(33,'qweqweqwe','4',9);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` (`id`, `name`, `teacher_id`) VALUES (1,'subject1',2),(2,'suasda',2),(3,'asddsa',2);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`id`) VALUES (1),(2);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testing`
--

DROP TABLE IF EXISTS `testing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `testing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(11) NOT NULL,
  `testId` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `test_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_testing_user1_idx` (`studentId`),
  KEY `fk_testing_test1_idx` (`testId`),
  CONSTRAINT `fk_testing_test1` FOREIGN KEY (`testId`) REFERENCES `test` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_testing_user1` FOREIGN KEY (`studentId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testing`
--

LOCK TABLES `testing` WRITE;
/*!40000 ALTER TABLE `testing` DISABLE KEYS */;
/*!40000 ALTER TABLE `testing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `topic`
--

DROP TABLE IF EXISTS `topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `subject_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_topic_subject1_idx` (`subject_id`),
  CONSTRAINT `fk_topic_subject1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `topic`
--

LOCK TABLES `topic` WRITE;
/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
INSERT INTO `topic` (`id`, `name`, `subject_id`) VALUES (1,'1 od 1',1),(2,'2 od 1',1),(3,'3 od 1 ',1),(4,'1 od 2',2),(5,'2 od 2',2),(6,'1 od 3',3),(7,'2 od 3',3),(8,'3 od 3',3),(9,'4 od 3',3);
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` enum('Student','Teacher','Admin') DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `br_index` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `ime_prezime` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `role`, `email`, `br_index`, `ime_prezime`) VALUES (2,'teacher1234','$2a$11$HJCn288VmEcueL31eg6cHOPxFS/00A3ZoyABTEKSOIzWR0imswBqm','Teacher','teacher@gmail.com',NULL,'Teacher Teacher'),(3,'hristijan112','$2a$11$HJCn288VmEcueL31eg6cHOPxFS/00A3ZoyABTEKSOIzWR0imswBqm','Admin','emailhristijan@gmail.com',NULL,'Hristijan Stojkovski'),(5,'student1','$2a$11$HJCn288VmEcueL31eg6cHOPxFS/00A3ZoyABTEKSOIzWR0imswBqm','Student','emailstudent@gmail.com','123','Student Student'),(6,'hristijan12345','$2a$11$6adGKxBRjLlmsDRAtwzbAu4F1dCcqI/9Uoce7ZwoFcgUVioQBRfMy','Student','hristijan0stojkovski@gmail.com','INKI97','Hristijan Stojkovski'),(7,'hristijan12345','$2a$11$cg11CvFIaeHulfqawSAB7O3nuAbURQl4xyNSwGaOjIH0V4mFiUPnG','Student','hristijan0stojkovski@gmail.com','INKI97','Hristijan Stojkovski'),(8,'hristijan12345','$2a$11$Hw3KU/mLsCmP3BIN2cBXR.phCeuG5Gdn3DfRclloF.tI/0gc95S6m','Student','hristijan0stojkovski@gmail.com','INKI97','Hristijan Stojkovski'),(9,'proba123','$2a$11$jSkLhVubP1JdCijK8/7ISeZz8S.3pfTa9Bee2QULufkJ5Afb9MbXO','Student','proba@gmail.com','inkiProba','proba proba'),(10,'qwwer','$2a$11$xC8GmTyRfs7MdvaIq8A2FeVtsAsJorKlpAjBQTypNP//NkjkQQPzW','Student','asdasdas','inki123','proba test'),(11,'asdasda','$2a$11$RSm.O05h6eC4u59QDkWo4e/2SmDuwl0hjEBi4cMo4jMSzSTEV7CvG','Student','asdasdas','asdasd','asdasd'),(12,'asdasda','$2a$11$utKwG0vAXdUhbQRoHYvbNehoz7j2SlTZF3kMIOwUUwKnhLFGSNOjm','Student','asdasda','asdasd','asdasd'),(13,'dasdasd','$2a$11$5lyB/2HIO11PEGzZFakF8OUXdBd5jSCXjam5h.yglTerpXimNbYdq','Student','sdadas','asdasda','asdfas'),(14,'asdasdas','$2a$11$HJCn288VmEcueL31eg6cHOPxFS/00A3ZoyABTEKSOIzWR0imswBqm','Teacher','asdasdas',NULL,'asdasdas'),(15,'imeprezime123','$2a$11$O4iPqZtOg4uUyVWjWt8deu0mygcVVUdz2ub/bwZT9xiQ.7Brc1/cm','Teacher','ime.prezime@gmail.com',NULL,'Ime Prezime');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-27 23:07:53
