-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: online-grading-system
-- ------------------------------------------------------
-- Server version	8.0.16

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
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assignments` (
  `assignment_id` int(11) NOT NULL,
  `assignment_title` varchar(45) DEFAULT NULL,
  `posted_date` date DEFAULT NULL,
  `deadline_date` date DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `notes` varchar(200) DEFAULT NULL,
  `file_path` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `fk_assignments_1_idx` (`subject_id`),
  CONSTRAINT `fk_assignments_1` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_title` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course_title_UNIQUE` (`course_title`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `professors`
--

DROP TABLE IF EXISTS `professors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professors` (
  `professor_id` int(11) NOT NULL AUTO_INCREMENT,
  `professor_name` varchar(45) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `contact_number` char(10) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`professor_id`),
  KEY `fk_professors_1_idx` (`user_id`),
  CONSTRAINT `fk_professors_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `question_id` int(11) NOT NULL,
  `question_text` varchar(200) DEFAULT NULL,
  `assignment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `fk_questions_1_idx` (`assignment_id`),
  CONSTRAINT `fk_questions_1` FOREIGN KEY (`assignment_id`) REFERENCES `assignments` (`assignment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_courses`
--

DROP TABLE IF EXISTS `student_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_courses` (
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`student_id`,`course_id`),
  KEY `fk_student_courses_1_idx` (`course_id`),
  KEY `fk_student_courses_2_idx` (`student_id`),
  CONSTRAINT `fk_student_courses_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `fk_student_courses_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(45) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `contact_number` char(10) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  CONSTRAINT `fk_students_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjects` (
  `subject_id` int(11) NOT NULL ,
  `subject_name` varchar(45) NOT NULL,
  `course_id` int(11) NOT NULL,
  `professor_id` int(11) NOT NULL,
  PRIMARY KEY (`subject_id`),
  KEY `fk_subjects_1_idx` (`professor_id`),
  KEY `fk_subjects_2_idx` (`course_id`),
  CONSTRAINT `fk_subjects_1` FOREIGN KEY (`professor_id`) REFERENCES `professors` (`professor_id`),
  CONSTRAINT `fk_subjects_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `submissions`
--

DROP TABLE IF EXISTS `submissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submissions` (
  `submission_id` int(11) NOT NULL,
  `assignment_id` int(11) DEFAULT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `marks` float DEFAULT NULL,
  `file_path` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`submission_id`),
  KEY `fk_submissions_1_idx` (`assignment_id`),
  KEY `fk_submissions_2_idx` (`student_id`),
  KEY `fk_submissions_3` (`subject_id`),
  CONSTRAINT `fk_submissions_1` FOREIGN KEY (`assignment_id`) REFERENCES `assignments` (`assignment_id`),
  CONSTRAINT `fk_submissions_2` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_submissions_3` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `user_type` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'online-grading-system'
--

--
-- Dumping routines for database 'online-grading-system'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-09-14  0:04:10
