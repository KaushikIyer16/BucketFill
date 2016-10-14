INSERT INTO `bucketfill`.`Lab` (`Name`, `DayOfWeek`, `TotalSlots`, `FreeSlots`) VALUES
('CCP', 'MONDAY', '3', '3'),
('CCP', 'TUESDAY', '3', '3'),
('CCP', 'WEDNESDAY', '3', '3'),
('CCP', 'THURSDAY', '3', '3'),
('CCP', 'FRIDAY', '3', '3'),
('CCP', 'SATURDAY', '2', '2');

INSERT INTO `bucketfill`.`Lab` (`Name`, `DayOfWeek`, `TotalSlots`, `FreeSlots`) VALUES
('ISE1', 'MONDAY', '3', '3'),
('ISE1', 'TUESDAY', '3', '3'),
('ISE1', 'WEDNESDAY', '3', '3'),
('ISE1', 'THURSDAY', '3', '3'),
('ISE1', 'FRIDAY', '3', '3'),
('ISE1', 'SATURDAY', '2', '2');


INSERT INTO `bucketfill`.`Lab` (`Name`, `DayOfWeek`, `TotalSlots`, `FreeSlots`) VALUES
('ISE2', 'MONDAY', '3', '3'),
('ISE2', 'TUESDAY', '3', '3'),
('ISE2', 'WEDNESDAY', '3', '3'),
('ISE2', 'THURSDAY', '3', '3'),
('ISE2', 'FRIDAY', '3', '3'),
('ISE2', 'SATURDAY', '2', '2');

ALTER TABLE `Slot` ADD `DayOfWeek` VARCHAR(20) NOT NULL AFTER `StartTime`;

INSERT INTO `bucketfill`.`Slot` (`Name`, `ClassName`, `Subject`, `StartTime`, `DayOfWeek`) VALUES
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
