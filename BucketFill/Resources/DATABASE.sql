-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Nov 18, 2016 at 11:23 AM
-- Server version: 5.5.42
-- PHP Version: 7.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `bucketfill`
--

-- --------------------------------------------------------

--
-- Table structure for table `Lab`
--

CREATE TABLE `Lab` (
  `Name` varchar(10) DEFAULT NULL,
  `DayOfWeek` varchar(10) DEFAULT NULL,
  `TotalSlots` int(11) DEFAULT NULL,
  `FreeSlots` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Lab`
--

INSERT INTO `Lab` (`Name`, `DayOfWeek`, `TotalSlots`, `FreeSlots`) VALUES
('CCP', 'MONDAY', 3, 3),
('CCP', 'TUESDAY', 3, 3),
('CCP', 'WEDNESDAY', 3, 3),
('CCP', 'THURSDAY', 3, 3),
('CCP', 'FRIDAY', 3, 3),
('CCP', 'SATURDAY', 2, 2),
('ISE1', 'MONDAY', 3, 3),
('ISE1', 'TUESDAY', 3, 3),
('ISE1', 'WEDNESDAY', 3, 3),
('ISE1', 'THURSDAY', 3, 3),
('ISE1', 'FRIDAY', 3, 3),
('ISE1', 'SATURDAY', 2, 2),
('ISE2', 'MONDAY', 3, 3),
('ISE2', 'TUESDAY', 3, 3),
('ISE2', 'WEDNESDAY', 3, 3),
('ISE2', 'THURSDAY', 3, 3),
('ISE2', 'FRIDAY', 3, 3),
('ISE2', 'SATURDAY', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `Section`
--

CREATE TABLE `Section` (
  `TeacherID` int(11) DEFAULT NULL,
  `Subject` varchar(30) DEFAULT NULL,
  `Semester` int(11) DEFAULT NULL,
  `Name` varchar(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Section`
--

INSERT INTO `Section` (`TeacherID`, `Subject`, `Semester`, `Name`) VALUES
(1, 'ACN', 1, 'PG'),
(2, 'WAN', 1, 'PG'),
(3, 'IOT', 1, 'PG'),
(4, 'INS', 1, 'PG'),
(4, 'MIS', 7, 'A'),
(5, 'SNA', 1, 'PG'),
(5, 'DCN', 5, 'A'),
(6, 'CSPA', 1, 'PG'),
(6, 'IE(ML)', 7, '1'),
(7, 'TFCS', 3, 'B'),
(7, 'IE(DS)', 7, '1'),
(8, 'DM', 5, '1'),
(8, 'CCP', 1, 'C'),
(9, 'PCP', 3, 'B'),
(9, 'WP', 5, 'A'),
(10, 'CCP', 1, 'K'),
(10, 'DCN', 5, 'B'),
(11, 'JAVA', 5, 'A'),
(11, 'CNS', 7, 'A'),
(12, 'JAVA', 5, 'B'),
(12, 'E&M', 7, 'B'),
(13, 'DS', 3, 'B'),
(13, 'MIS', 7, 'B'),
(14, 'DS', 3, 'A'),
(14, 'CNS', 7, 'B'),
(15, 'PCP', 3, 'A'),
(15, 'WP', 5, 'B'),
(16, 'CCP', 1, 'A'),
(16, 'E&M', 7, 'A'),
(17, 'DBMS', 5, 'A'),
(17, 'CCP', 1, 'J'),
(18, 'DBMS', 5, 'B'),
(18, 'COES', 3, 'A'),
(19, 'COES', 3, 'B'),
(19, 'AI', 5, '1'),
(20, 'CCP', 1, 'F'),
(20, 'CCP', 1, 'D'),
(21, 'CCP', 1, 'H'),
(21, 'C#', 7, 'B'),
(22, 'PSQ', 1, 'PG'),
(22, 'C#', 7, 'A'),
(23, 'CCP', 1, 'B'),
(23, 'CCP', 1, 'E'),
(24, 'TFCS', 3, 'A'),
(24, 'CCP', 1, 'G'),
(25, 'MULTICORE', 1, 'PG'),
(26, 'Discrete Mathematics', 3, 'A'),
(27, 'Discrete Mathematics', 3, 'B');

-- --------------------------------------------------------

--
-- Table structure for table `SelfStudy`
--

CREATE TABLE `SelfStudy` (
  `CourseCode` varchar(11) DEFAULT NULL,
  `Credits` int(11) DEFAULT NULL,
  `NumberOfTheory` int(11) DEFAULT NULL,
  `NumberOfPractical` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Slot`
--

CREATE TABLE `Slot` (
  `Name` varchar(10) DEFAULT NULL,
  `ClassName` varchar(10) DEFAULT NULL,
  `Subject` varchar(30) DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `DayOfWeek` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Slot`
--

INSERT INTO `Slot` (`Name`, `ClassName`, `Subject`, `StartTime`, `DayOfWeek`) VALUES
('CCP', '1A', 'CCP', '08:55:00', 'TUESDAY'),
('CCP', '1B', 'CCP', '08:55:00', 'SATURDAY'),
('CCP', '1C', 'CCP', '11:15:00', 'THURSDAY'),
('CCP', '1D', 'CCP', '11:15:00', 'SATURDAY'),
('CCP', '1E', 'CCP', '08:55:00', 'WEDNESDAY'),
('CCP', '1F', 'CCP', '11:15:00', 'MONDAY'),
('CCP', '1G', 'CCP', '11:15:00', 'TUESDAY'),
('CCP', '1H', 'CCP', '11:15:00', 'WEDNESDAY'),
('CCP', '1J', 'CCP', '11:15:00', 'FRIDAY'),
('CCP', '1K', 'CCP', '14:55:00', 'TUESDAY');

-- --------------------------------------------------------

--
-- Table structure for table `Subject`
--

CREATE TABLE `Subject` (
  `CourseCode` varchar(11) DEFAULT NULL,
  `Name` varchar(30) DEFAULT NULL,
  `Theory` int(11) DEFAULT NULL,
  `Lab` int(11) DEFAULT NULL,
  `Tutorial` int(11) DEFAULT NULL,
  `Elective` int(11) DEFAULT NULL,
  `SelfStudy` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Subject`
--

INSERT INTO `Subject` (`CourseCode`, `Name`, `Theory`, `Lab`, `Tutorial`, `Elective`, `SelfStudy`) VALUES
('15IS3DCDSC', 'DATA STRUCTURES WITH C', 3, 1, 0, 0, 1),
('15MA3CCDMS', 'DISCRETE MATHEMATICS', 3, 0, 1, 0, 0),
('15IS3DCTFC', 'THEORETICAL FOUNDATIONS OF COM', 4, 0, 1, 0, 0),
('15IS3DCCOE', 'COMPUTER ORG. AND EMBEDDED SYS', 4, 0, 0, 0, 0),
('15CI3GCPCP', 'PROGRAMMING WITH C++', 3, 1, 0, 0, 1),
('16IS5DCJAV', 'PROGRAMMING WITH JAVA', 3, 1, 0, 0, 1),
('16IS5DCDBM', 'DATABASE MANAGEMENT SYSTEMS', 3, 1, 0, 0, 1),
('16IS5DCDCN', 'DATA COMM. AND NETWORKING', 3, 1, 1, 0, 0),
('16IS5DCWEP', 'WEB PROGRAMMING', 3, 1, 0, 0, 0),
('16IS5DEDMG', 'DATA MINING', 3, 1, 0, 1, 0),
('16IS5DEAIN', 'ARTIFICIAL INTELLIGENCE', 3, 1, 0, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Teacher`
--

CREATE TABLE `Teacher` (
  `ID` int(11) NOT NULL DEFAULT '0',
  `Name` varchar(20) DEFAULT NULL,
  `Hours` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Teacher`
--

INSERT INTO `Teacher` (`ID`, `Name`, `Hours`) VALUES
(1, 'DR. GOWRISHANKAR', 8),
(2, 'DR. RADHIKA K R', 8),
(3, 'DR. M DAKSHAYINI', 8),
(4, 'DR. R ASHOK KUMAR', 14),
(5, 'DR. P JAYAREKHA', 14),
(6, 'DR. SHEELA S V', 14),
(7, 'DR. SHAMBHAVI B R', 14),
(8, 'MAMATHA', 16),
(9, 'SHREELATHA R', 16),
(10, 'NALINI M K', 16),
(11, 'SHUBHA RAO V', 16),
(12, 'SINDHU K', 16),
(13, 'PREETHA S', 16),
(14, 'MAHALAKSHMI B S', 16),
(15, 'RAJESHWARI K', 16),
(16, 'NALINA V', 16),
(17, 'CHANDRAKALA G RAJU', 16),
(18, 'ROOPA R', 16),
(19, 'GURURAJA H S', 16),
(20, 'SOWMYA K S', 16),
(21, 'ANITHA H M', 16),
(22, 'DR. SANDEEP VARMA N', 16),
(23, 'SWETHA K', 16),
(24, 'R INDIRA', 16),
(25, 'DR. B G PRASAD', 8),
(26, 'ACM', 16),
(27, 'RS', 16);

-- --------------------------------------------------------

--
-- Table structure for table `TeacherSection`
--

CREATE TABLE `TeacherSection` (
  `TeacherID` int(11) DEFAULT NULL,
  `CourseCode` varchar(11) DEFAULT NULL,
  `Section` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `TeacherSubject`
--

CREATE TABLE `TeacherSubject` (
  `TeacherID` int(11) DEFAULT NULL,
  `CourseCode` varchar(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `TeacherSubject`
--

INSERT INTO `TeacherSubject` (`TeacherID`, `CourseCode`) VALUES
(5, '16IS5DCDCN'),
(7, '15IS3DCTFC'),
(8, '16IS5DEDMG'),
(9, '16IS5DCWEP'),
(10, '16IS5DCDCN'),
(11, '16IS5DCJAV'),
(12, '16IS5DCJAV'),
(13, '15IS3DCDSC'),
(14, '15IS3DCDSC'),
(15, '16IS5DCWEP'),
(17, '16IS5DCDBM'),
(18, '16IS5DCDBM'),
(19, '16IS5DEAIN'),
(24, '15IS3DCTFC'),
(15, '15CI3GCPCP'),
(9, '15CI3GCPCP');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Section`
--
ALTER TABLE `Section`
  ADD KEY `section_teacherid_fk` (`TeacherID`);

--
-- Indexes for table `Teacher`
--
ALTER TABLE `Teacher`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `TeacherSection`
--
ALTER TABLE `TeacherSection`
  ADD KEY `teachersection_tid_fk` (`TeacherID`);

--
-- Indexes for table `TeacherSubject`
--
ALTER TABLE `TeacherSubject`
  ADD KEY `teachersubject_tid_fk` (`TeacherID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Section`
--
ALTER TABLE `Section`
  ADD CONSTRAINT `section_teacherid_fk` FOREIGN KEY (`TeacherID`) REFERENCES `Teacher` (`ID`);

--
-- Constraints for table `TeacherSection`
--
ALTER TABLE `TeacherSection`
  ADD CONSTRAINT `teachersection_tid_fk` FOREIGN KEY (`TeacherID`) REFERENCES `Teacher` (`ID`);

--
-- Constraints for table `TeacherSubject`
--
ALTER TABLE `TeacherSubject`
  ADD CONSTRAINT `teachersubject_tid_fk` FOREIGN KEY (`TeacherID`) REFERENCES `Teacher` (`ID`);
