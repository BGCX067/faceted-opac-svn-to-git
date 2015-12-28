-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.34-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema faceted_opac
--

CREATE DATABASE IF NOT EXISTS faceted_opac;
USE faceted_opac;

--
-- Definition of table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(40) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`,`username`,`password`,`name`) VALUES 
 (1,'admin','admin','Administrator');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


--
-- Definition of table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `call_number` varchar(45) NOT NULL,
  `format` int(10) unsigned NOT NULL,
  `location` int(10) unsigned NOT NULL,
  `location_link` varchar(256) DEFAULT NULL,
  `imprint` varchar(256) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  `publisher` varchar(128) NOT NULL,
  `language` int(10) unsigned NOT NULL,
  `description` text,
  `image` varchar(256) DEFAULT 'book_cover/default.jpg',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` (`id`,`title`,`call_number`,`format`,`location`,`location_link`,`imprint`,`year`,`publisher`,`language`,`description`,`image`) VALUES 
 (1,'Understanding complex datasets : data mining with matrix decompositions','QA76.9.D343S568',2,2,'http://www.loc.gov/catdir/enhancements/fy0745/2007013096-d.html','Boca Raton : Chapman & Hall/CRC Press, 2007',2007,'CRC Press',1,'xxi, 236 p., [8] p. of plates : ill. (some col.) ; 25 cm. ','book_cover/QA769D343S568001.jpg'),
 (2,'Data mining and knowledge discovery approaches based on rule induction techniques','QA76.9.D343D3834',4,1,'','New York, N.Y. : Springer, 2006',2006,'Springer',1,'xlviii, 748 p. : ill. ; 25 cm. ','book_cover/QA769D343D3834002.jpg'),
 (3,'Using and understanding Java data objects','QA76.73.J38E99',1,1,'','New York : Apress, 2003',2003,'Apress',1,'xxix, 426 p. : ill. ; 24 cm. ','book_cover/QA7673J38E99003.jpg'),
 (4,'Algorithms and data structures in C++','QA76.73.C153P37',1,1,'','Boca Raton, Fla. : CRC Press, 1993',1993,'CRC Press',1,'257 p. : ill. ; 24 cm. ','book_cover/QA7673C153P37004.jpg'),
 (5,'The age of discontinuity : guidelines to our changing society ','HC59.D7',1,1,'','London : Heinemann, 1969',1969,'Heinemann',1,'x, 369 p. ; 22 cm. ','book_cover/HC59D7005.jpg'),
 (6,'A pattern approach to interaction design','QA76.9.H85B673',1,2,'http://www.loc.gov/catdir/toc/onix06/00054570.html','Chichester : John Wiley & Sons, 2001',2001,'John Wiley & Sons',1,'xvii, 246 p. : ill. ; 24 cm. ','book_cover/QA769H85B673006.jpg'),
 (7,'Java persistence for relational databases','QA76.9.D26S63',1,1,'','Berkeley, Calif. : Apress, 2003',2003,'Apress',1,'xxv, 337 p. : ill. ; 24 cm. ','book_cover/QA769D26S63007.jpg'),
 (8,'Solaris solutions for system administrators : time-saving tips, techniques, and workarounds','QA76.76.O63H476',1,1,'','New York : John Wiley & Sons, 2000.',2000,'John Wiley & Sons',1,'xxi, 467 p. : ill. ; 24 cm. ','book_cover/QA7676O63H476008.jpg'),
 (9,' 	 Soft computing and human-centered machines','QA76.9.S63S615',4,1,'','Tokyo : Springer, 2000.',2000,'Springer',1,'xviii, 327 p. : ill. ; 25 cm. ','book_cover/QA769S63S615009.jpg'),
 (10,'Java 2','QA76.73.J38H38',1,1,'','Boston : Addison-Wesley, 2002',2002,'Addison-Wesley',1,'382 p. : ill. ; 24 cm. ','book_cover/QA7673J38H380010.jpg'),
 (11,'Accident prevention manual for business & industry 10th ed','WA485.A171 1992',5,4,'','Spring Lake Dr., Itasca, Il. National Safety Council 1992',1992,'National Safety Council ',1,'v. : ill. ; 27 cm. <br />\r\nContents: vol. 1 -- Administration & programs -- vol. 2 -- Engineering & technology \r\n','book_cover/WA485A171 19920011.jpg'),
 (12,'The 100 best stocks to own in America','HG4963.W35 1996',1,1,'','[S.l.] : Business Information Press, 1996',1996,'Business Information Press',1,'392 p. ; 23 cm. ','book_cover/HG4963W35 19960012.jpg'),
 (13,'LINUX and UNIX programming tools : a primer for software developers','QA76.76.O63S37',1,1,'','Boston, MA : Addison Wesley, 2003',2003,'Addison Wesley',1,'xvi,352 p. : ill. ; 23 cm. ','book_cover/QA7676O63S370013.jpg'),
 (14,'The unofficial guide to ethical hacking','QA76.9.A25F335 2006',1,1,'','Boston, MA : Thomson Course Technology, 2006',2006,'Thomson Course Technology',1,'xxii, 841 p. : ill. ; 24 cm.','book_cover/QA769A25F335 20060014.jpg'),
 (15,'Data structures and the Java collections framework','QA76.73.J38C644',1,2,'http://www.loc.gov/catdir/description/mh021/2001030694.html','Boston : McGraw-Hill, 2001',2001,'McGraw-Hill',1,'xx, 716 p. : ill. ; 24 cm. ','book_cover/QA7673J38C6440015.jpg'),
 (16,'Hacking exposed : network security secrets and solutions','TK5105.59.M48 1999 9HUKM',1,5,'','Berkeley : Osborne/McGraw-Hill , 1999',1999,'McGraw-Hill',1,'484 p. : ill. ; 23 cm. ','book_cover/TK510559M48 1999 9HUKM0016.jpg'),
 (17,'Windows XP unleashed','QA76.76.W56O454',1,1,'','Indianapolis, Ind. : Sams Publishing, 2002',2002,'Sams Publishing',1,'xxii, 880 p. : ill. ; 23 cm. ','book_cover/QA7676W56O4540017.jpg'),
 (18,' 	 Applied economics : an introductory course 8th ed.','HB171.5.A65 1999',1,1,'','Harlow, England : Financial Times, 1999',1999,'Financial Times',1,'xii, 702 p. : ill. ; 25 cm. ','book_cover/HB1715A65 19990018.jpg'),
 (19,'Organisasi maklumat','Z666.5 .J84 8 ',1,6,'','Skudai, Johor : Penerbit Universiti Teknologi Malaysia, 2008',2008,'Penerbit Universiti Teknologi Malaysia',2,'xi, 237 p. : ill. ; 24 cm. ','book_cover/Z6665 J84 80019.jpg'),
 (20,'Sistem maklumat pengurusan di organisasi penyelidikan dan pembangunan: satu kajian khusus di Unit Tenaga Nuklear ','mikrofilem tesis T58.6.A94 2003 ',6,7,'','Bangi : Perpustakaan Tun Seri Lanang , 2003',2003,'Perpustakaan Tun Seri Lanang',2,'1 microfilm reel ; 35 mm . ','book_cover/default.jpg');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;


--
-- Definition of table `book_author`
--

DROP TABLE IF EXISTS `book_author`;
CREATE TABLE `book_author` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(10) unsigned NOT NULL,
  `author` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_author`
--

/*!40000 ALTER TABLE `book_author` DISABLE KEYS */;
INSERT INTO `book_author` (`id`,`book_id`,`author`) VALUES 
 (1,1,'Skillicorn, David B.'),
 (2,2,'Triantaphyllou, Evangelos'),
 (3,2,'Felici, Giovanni'),
 (4,3,'Ezzio, David'),
 (5,4,'Parker, Alan'),
 (6,5,'Drucker, Peter F.'),
 (7,6,'Borchers, Jan'),
 (8,7,'Sperko, Richard'),
 (9,8,'Henry-Stocker, Sandra'),
 (10,8,'Marks, Evan R.'),
 (11,9,'Liu, Z.-Q. (Zhi-Qiang)'),
 (12,9,'Miyamoto, Sadaaki'),
 (13,10,'Hawlitzek, Florian'),
 (14,11,'Spring Lake Dr., Itasca'),
 (15,12,'Walden, Gene'),
 (16,13,'Sarwar, Syed Mansoor'),
 (17,14,'Fadia, Ankit'),
 (18,15,'Collins, William J.'),
 (19,16,'McClure, Stuart'),
 (20,16,'Scambray, Joel'),
 (21,16,'Kurtz, George'),
 (22,17,'Ogletree, Terry William'),
 (23,17,'Regas, Rima'),
 (24,17,'Glenn, Walter '),
 (25,18,'Griffiths, Alan'),
 (26,18,'Wall, Stuart'),
 (27,19,'Juhana Salim'),
 (28,19,'Len, Ten Moi'),
 (29,19,'Mohd Shahizan Othman'),
 (30,20,'Azizah binti Jaafar');
/*!40000 ALTER TABLE `book_author` ENABLE KEYS */;


--
-- Definition of table `book_isbn`
--

DROP TABLE IF EXISTS `book_isbn`;
CREATE TABLE `book_isbn` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(10) unsigned NOT NULL,
  `isbn` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_isbn`
--

/*!40000 ALTER TABLE `book_isbn` DISABLE KEYS */;
INSERT INTO `book_isbn` (`id`,`book_id`,`isbn`) VALUES 
 (1,1,'9781584888321'),
 (2,1,'1584888326'),
 (3,2,'038734294X (hbk.)'),
 (4,2,'9780387342948 (hbk.) '),
 (5,3,'1590590430 '),
 (6,4,'0849371716 '),
 (7,5,'0434903957 '),
 (8,6,'0471498289'),
 (9,7,'1590590716 '),
 (10,8,'0471348104 '),
 (11,9,'4431702792'),
 (12,10,'0201758806 '),
 (13,11,'0879121556 '),
 (14,12,'9838997471'),
 (15,13,'0201773457 '),
 (16,14,'1598630628 '),
 (17,15,'0072369647 '),
 (18,16,'0072121270 '),
 (19,17,'0672322803 '),
 (20,18,'0582414547 '),
 (21,19,'9789835204562 (pbk.) '),
 (22,20,'-');
/*!40000 ALTER TABLE `book_isbn` ENABLE KEYS */;


--
-- Definition of table `book_subject`
--

DROP TABLE IF EXISTS `book_subject`;
CREATE TABLE `book_subject` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(10) unsigned NOT NULL,
  `subject` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_subject`
--

/*!40000 ALTER TABLE `book_subject` DISABLE KEYS */;
INSERT INTO `book_subject` (`id`,`book_id`,`subject`) VALUES 
 (1,1,'Data mining'),
 (2,1,'Data structures (Computer science)'),
 (3,1,'Computer algorithms'),
 (4,2,'Data mining'),
 (5,3,'Java (Computer program language)'),
 (6,3,'Application program interfaces (Computer software)'),
 (7,3,'Object-oriented programming (Computer science)'),
 (8,4,'C++ (Computer program language)'),
 (9,4,'Computer algorithms'),
 (10,4,'Data structures (Computer science)'),
 (11,5,'Economic history 1945-1971'),
 (12,5,'International economic relations'),
 (13,5,'Economic development'),
 (14,5,'Organization -- Research'),
 (15,5,'Education'),
 (16,6,'Human-computer interaction\r\n'),
 (17,6,' 	 Computer software -- Development'),
 (18,7,'Java (Computer program language)'),
 (19,7,'Relational databases'),
 (20,7,'Database design'),
 (21,8,'Solaris (Computer file)'),
 (22,8,'Operating systems (Computers)'),
 (23,9,'Soft computing'),
 (24,9,'Human-computer interaction'),
 (25,10,'Java (Computer program language)'),
 (26,10,''),
 (27,11,'Accidents, Occupational -- Prevention and control'),
 (28,12,'Stocks'),
 (29,12,'Venture capital'),
 (30,13,'UNIX (Computer file)'),
 (31,13,'Linux'),
 (32,13,'Operating systems (Computers)'),
 (33,14,'Computer security'),
 (34,14,'Computer hackers'),
 (35,15,'Java (Computer program language)'),
 (36,15,'Data structures (Computer science)'),
 (37,16,'Computer networks -- Security measures'),
 (38,16,'Computer security'),
 (39,17,'Microsoft Windows (Computer file)'),
 (40,17,'Operating systems (Computers)'),
 (41,18,'Economics'),
 (42,18,'United Kingdom -- Economic conditions 1945-1993'),
 (43,19,'Information organization'),
 (44,19,'Information storage and retrieval systems'),
 (45,19,'Information science'),
 (46,20,'Management information systems');
/*!40000 ALTER TABLE `book_subject` ENABLE KEYS */;


--
-- Definition of table `ref_format`
--

DROP TABLE IF EXISTS `ref_format`;
CREATE TABLE `ref_format` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `format` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ref_format`
--

/*!40000 ALTER TABLE `ref_format` DISABLE KEYS */;
INSERT INTO `ref_format` (`id`,`format`) VALUES 
 (1,'Book'),
 (2,'Online'),
 (3,'Thesis'),
 (4,'Journal'),
 (5,'Manual'),
 (6,'microfilm');
/*!40000 ALTER TABLE `ref_format` ENABLE KEYS */;


--
-- Definition of table `ref_language`
--

DROP TABLE IF EXISTS `ref_language`;
CREATE TABLE `ref_language` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `language` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ref_language`
--

/*!40000 ALTER TABLE `ref_language` DISABLE KEYS */;
INSERT INTO `ref_language` (`id`,`language`) VALUES 
 (1,'English'),
 (2,'Bahasa Melayu');
/*!40000 ALTER TABLE `ref_language` ENABLE KEYS */;


--
-- Definition of table `ref_location`
--

DROP TABLE IF EXISTS `ref_location`;
CREATE TABLE `ref_location` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `location` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ref_location`
--

/*!40000 ALTER TABLE `ref_location` DISABLE KEYS */;
INSERT INTO `ref_location` (`id`,`location`) VALUES 
 (1,'PTSL-KOLEKSI AM'),
 (2,'Online'),
 (3,'PTSL-KOLEKSI ASIA'),
 (4,'PHUKM-KOLEKSI AM'),
 (5,'PHUKM-KOLEKSI AM'),
 (6,'PTSL-ARKIB'),
 (7,'PTSL-MEDIA');
/*!40000 ALTER TABLE `ref_location` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
