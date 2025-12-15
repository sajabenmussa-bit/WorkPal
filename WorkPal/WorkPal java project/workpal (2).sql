-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2025 at 08:53 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `workpal`
--

-- --------------------------------------------------------

--
-- Table structure for table `goal`
--

CREATE TABLE `goal` (
  `goal_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL DEFAULT current_timestamp(),
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `due_date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `goal`
--

INSERT INTO `goal` (`goal_id`, `user_id`, `title`, `description`, `due_date`) VALUES
(13, 45, 'get a degree', 'working hard to get the 10th degree', '2025-12-15'),
(14, 45, 'MY First', 'create my first application in my journey', '2025-12-15');

-- --------------------------------------------------------

--
-- Table structure for table `goal_steps`
--

CREATE TABLE `goal_steps` (
  `step_id` int(11) NOT NULL,
  `goal_id` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `completed` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `goal_steps`
--

INSERT INTO `goal_steps` (`step_id`, `goal_id`, `description`, `completed`) VALUES
(29, 13, 'database', 1),
(30, 13, 'database', 1),
(31, 13, 'database', 0),
(32, 13, 'database', 0),
(33, 14, 'CRUD', 1),
(34, 14, 'details', 1),
(35, 14, 'database', 0),
(36, 14, 'search', 0),
(37, 14, 'main pages', 1);

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE `project` (
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `title` varchar(200) NOT NULL,
  `description` text DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`project_id`, `user_id`, `title`, `description`, `start_date`, `end_date`) VALUES
(14, 44, 'Edukid', 'شروع يعمل على بيع الالعاب التعليمية للاطفال ', '2026-01-10', '2026-05-20'),
(15, 45, 'CarGuid', 'a project that helps ....', '2026-02-15', '2026-04-19'),
(16, 45, 'FoodMap', 'app that helps people discover restaurants', '2026-03-25', '2026-08-10');

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE `tasks` (
  `task_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `description` varchar(400) NOT NULL,
  `is_completed` tinyint(1) NOT NULL DEFAULT 0,
  `title` varchar(50) NOT NULL,
  `due_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tasks`
--

INSERT INTO `tasks` (`task_id`, `project_id`, `description`, `is_completed`, `title`, `due_date`) VALUES
(25, 14, 'design the database', 0, 'database', NULL),
(26, 16, 'design the main pages', 0, 'main pages', NULL),
(27, 16, 'create a database', 0, 'database', NULL),
(28, 16, 'implement a search feature', 0, 'search', NULL),
(29, 16, 'display restaurant details', 0, 'details', NULL),
(30, 16, 'implement basic crud', 0, 'CRUD', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL DEFAULT 'name',
  `email` varchar(60) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `email`, `password`) VALUES
(42, 'Saja', 'Sajabenmussa@email.com', 'benmussa28'),
(43, 'Saja', 'sajabenmussa@email.com', '12345678'),
(44, 'Saja', 'Sajabenmussa@email.com', '1234'),
(45, 'Ali', 'Ali@email.com', '56789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `goal`
--
ALTER TABLE `goal`
  ADD PRIMARY KEY (`goal_id`);

--
-- Indexes for table `goal_steps`
--
ALTER TABLE `goal_steps`
  ADD PRIMARY KEY (`step_id`),
  ADD KEY `goal_steps_ibfk_1` (`goal_id`);

--
-- Indexes for table `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`task_id`),
  ADD KEY `project_id` (`project_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `goal`
--
ALTER TABLE `goal`
  MODIFY `goal_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `goal_steps`
--
ALTER TABLE `goal_steps`
  MODIFY `step_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `project`
--
ALTER TABLE `project`
  MODIFY `project_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `task_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `goal_steps`
--
ALTER TABLE `goal_steps`
  ADD CONSTRAINT `goal_steps_ibfk_1` FOREIGN KEY (`goal_id`) REFERENCES `goal` (`goal_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `project_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tasks`
--
ALTER TABLE `tasks`
  ADD CONSTRAINT `tasks_ibfk_1` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
