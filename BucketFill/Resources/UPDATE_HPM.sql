-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Host: localhost:8889
-- Generation Time: Oct 30, 2016 at 02:23 PM
-- Server version: 5.5.42
-- PHP Version: 7.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `bucketfill`
--

-- --------------------------------------------------------

--
-- Table structure for table `TeacherSection`
--

CREATE TABLE `TeacherSection` (
  `TeacherID` int(11) DEFAULT NULL,
  `CourseCode` varchar(11) DEFAULT NULL,
  `Section` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `TeacherSection`
--
ALTER TABLE `TeacherSection`
  ADD KEY `teachersection_tid_fk` (`TeacherID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `TeacherSection`
--
ALTER TABLE `TeacherSection`
  ADD CONSTRAINT `teachersection_tid_fk` FOREIGN KEY (`TeacherID`) REFERENCES `Teacher` (`ID`);


  -- phpMyAdmin SQL Dump
  -- version 4.4.10
  -- http://www.phpmyadmin.net
  --
  -- Host: localhost:8889
  -- Generation Time: Oct 30, 2016 at 02:24 PM
  -- Server version: 5.5.42
  -- PHP Version: 7.0.0

  SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
  SET time_zone = "+00:00";

  --
  -- Database: `bucketfill`
  --

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
  -- Indexes for table `TeacherSubject`
  --
  ALTER TABLE `TeacherSubject`
    ADD KEY `teachersubject_tid_fk` (`TeacherID`);

  --
  -- Constraints for dumped tables
  --

  --
  -- Constraints for table `TeacherSubject`
  --
  ALTER TABLE `TeacherSubject`
    ADD CONSTRAINT `teachersubject_tid_fk` FOREIGN KEY (`TeacherID`) REFERENCES `Teacher` (`ID`);


INSERT INTO `bucketfill`.`Section` (`TeacherID`, `Subject`, `Semester`, `Name`) VALUES ('26', 'Discrete Mathematics', '3', 'A');
INSERT INTO `bucketfill`.`Section` (`TeacherID`, `Subject`, `Semester`, `Name`) VALUES ('27', 'Discrete Mathematics', '3', 'B');
