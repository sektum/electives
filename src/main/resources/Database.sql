-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.23


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema electives_database
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ electives_database;
USE electives_database;

--
-- Table structure for table `electives_database`.`contract`
--

DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `comment` text,
  `mark` int(10) unsigned DEFAULT NULL,
  `idCourse` int(10) unsigned NOT NULL DEFAULT '0',
  `idStudent` int(10) unsigned NOT NULL DEFAULT '0',
  `finishedPercent` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `contract-course` (`idCourse`),
  KEY `contract-student` (`idStudent`),
  CONSTRAINT `contract-course` FOREIGN KEY (`idCourse`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `contract-student` FOREIGN KEY (`idStudent`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `electives_database`.`contract`
--

/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` (`id`,`comment`,`mark`,`idCourse`,`idStudent`,`finishedPercent`) VALUES 
 (0000000001,NULL,NULL,1,1,12),
 (0000000002,NULL,NULL,2,1,34),
 (0000000003,'The most worst work ever',0,3,2,100),
 (0000000004,'Very bad',4,1,3,100),
 (0000000005,NULL,NULL,2,3,32),
 (0000000006,NULL,NULL,4,4,65);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;


--
-- Table structure for table `electives_database`.`course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  `idLecturer` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `course-lecturer` (`idLecturer`),
  CONSTRAINT `course-lecturer` FOREIGN KEY (`idLecturer`) REFERENCES `lecturer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `electives_database`.`course`
--

/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` (`id`,`name`,`idLecturer`) VALUES 
 (0000000001,'Матанpp',1),
 (0000000002,'АППЗp',2),
 (0000000003,'Диск',1),
 (0000000004,'ФП',3);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;


--
-- Table structure for table `electives_database`.`lecturer`
--

DROP TABLE IF EXISTS `lecturer`;
CREATE TABLE `lecturer` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fullName` varchar(45) NOT NULL DEFAULT '',
  `pwd` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `electives_database`.`lecturer`
--

/*!40000 ALTER TABLE `lecturer` DISABLE KEYS */;
INSERT INTO `lecturer` (`id`,`fullName`,`pwd`) VALUES 
 (0000000001,'Псаша','81dc9bdb52d04dc2036dbd8313ed055'), /*Pass=1234*/
 (0000000002,'Пвася','289dff7669d7a23deef88d2f7129e7'),	 /*Pass=234*/
 (0000000003,'Пвлад','e369853df766fa44e1edff613f563bd'); /*Pass=34*/
/*!40000 ALTER TABLE `lecturer` ENABLE KEYS */;



-- Table structure for table `electives_database`.`student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `fullName` varchar(45) NOT NULL DEFAULT '',
  `pwd` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `electives_database`.`student`
--

/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`,`fullName`,`pwd`) VALUES 
 (0000000001,'Саша','d93591bdf786e1e4ee2fca799911215'), /*Pass=4321*/
 (0000000002,'Вася','caf1a3dfb55ffedd24130f58c5cfa'),	/*Pass=321*/
 (0000000003,'Петя','3c59dc48e8850243be879a5c74d079'),	/*Pass=21*/
 (0000000004,'Влад','c4ca4238a0b92382dcc509a6f75849b');	/*Pass=1*/
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
