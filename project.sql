/*
SQLyog Community v12.09 (32 bit)
MySQL - 5.6.21 : Database - studentdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`studentdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `studentdb`;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `rollno` varchar(10) DEFAULT NULL,
  `faculty` varchar(100) DEFAULT NULL,
  `semester` varchar(50) DEFAULT NULL,
  `collegename` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`address`,`birthdate`,`rollno`,`faculty`,`semester`,`collegename`,`gender`) values (1,'yojan','patan','2049-12-26','22','B.Sc.CSIT','Seven','PMC','Male'),(2,'dinesh','pesicola','1992-12-27','45','science','Seven','PMC','Male'),(4,'sajan','balkhu','2045-12-31','100','management','8','pinnacle','Male'),(5,'sajan','balkhu','2045-12-31','100','management','8','pinnacle','Male'),(6,'sajan','balkhu','2049-12-26','100','management','8','pinnacle','Male'),(7,'sajan','balkhu','1992-12-27','100','management','8','pinnacle','Male'),(8,'asdf','asdf','2049-12-26','asdfsda','asdf','asdf','asdf','Male'),(9,'asdf','asdf','2049-12-26','asdfsda','asdf','sdfasdfasdf','asdf','Male'),(10,'asdf','asdfasdfsadfsad','2049-12-26','asdfsda','asdf','sdfasdfasdf','asdf','Male'),(14,'asdfasdf','asdf','2049-12-26','asdf','asdf','asdf','asdf','Female'),(17,'afd','sdafsdfs','2049-12-26','sdafsdf','asdfasdf','sadfsdf','asdfsdaf','Female'),(18,'yourname','sdafsdfs','2049-12-26','sdafsdf','asdfasdf','sadfsdf','asdfsdaf','Female'),(19,'name','asdfsadfsdf','2049-12-26','fsadf','asdfsadf','asdfsdaf','sadfsadfsd','Female'),(20,'asdf','asdf','2049-12-26','sadf','asdf','Seven','asd','Male'),(22,'asdfsdf','sdfsd','2049-12-26','asdfsd','asdfsdafsdf','Fifth','sadfsdf','Female'),(25,'salil','lagankhel','2049-12-26','22','BIM','Seven','NCIT','Male');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`) values (1,'admin','admin'),(2,'yojan','yojan'),(3,'sajan','sajan'),(4,'abcd','abcd'),(5,'asdf','123'),(11,'salil','salil');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
