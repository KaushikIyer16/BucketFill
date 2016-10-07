-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Oct 04, 2016 at 01:28 PM
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
(25, 'MULTICORE', 1, 'PG');

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
  `StartTime` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `Subject`
--

CREATE TABLE `Subject` (
  `CourseCode` varchar(11) DEFAULT NULL,
  `Name` varchar(30) DEFAULT NULL,
  `Theory` int(11) DEFAULT NULL,
  `Lab` tinyint(1) DEFAULT NULL,
  `Tutorial` tinyint(1) DEFAULT NULL,
  `Elective` tinyint(1) DEFAULT NULL,
  `SelfStudy` tinyint(1) DEFAULT NULL
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
(25, 'DR. B G PRASAD', 8);

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
-- Constraints for dumped tables
--

--
-- Constraints for table `Section`
--
ALTER TABLE `Section`
  ADD CONSTRAINT `section_teacherid_fk` FOREIGN KEY (`TeacherID`) REFERENCES `Teacher` (`ID`);
