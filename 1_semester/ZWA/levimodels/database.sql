-- phpMyAdmin SQL Dump
-- Date of creation 02.01.2023
-- version of the server: adminer 4.7.6
-- version PHP: 7.2.0
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";
--
-- База данных: `test`
--
-- --------------------------------------------------------
--
-- structure of table `users`
--
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(100) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `date` varchar(10) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table data dump `users`
--

INSERT INTO `users` (`id`, `login`, `email`, `date`, `password`) VALUES
(2, 'admin', 'test@local.ru', '22-01-2022', '05a671c66aefea124cc08b76ea6d30bb');
--
-- spreadsheet indexes `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);
--
-- AUTO_INCREMENT sor saving spreadsheet
--
--
-- AUTO_INCREMENT for spreadsheet `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;