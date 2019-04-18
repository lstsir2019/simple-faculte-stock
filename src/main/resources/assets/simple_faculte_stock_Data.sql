-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 19, 2019 at 01:16 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `simple_faculte_stock`
--
CREATE DATABASE IF NOT EXISTS `simple_faculte_stock` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `simple_faculte_stock`;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1),
(44),
(44);

--
-- Dumping data for table `magasin`
--

INSERT INTO `magasin` (`id`, `address`, `description`, `libelle`, `reference`) VALUES
(1, NULL, 'magasin Central', 'magasin', 'mag-3'),
(2, 'Adresse No 10', 'p', 'magasin fst', 'mag-1'),
(3, NULL, 'S', 'magasin central', 'mag-2'),
(19, 'Magasin 04', 'grand', 'Magasin ', 'mag-4'),
(43, NULL, 'Magasin', 'Magasin', '');

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`id`, `date_reception`, `qte`, `qte_deffectueuse`, `reference_commande`, `reference_produit`, `reference_reception`, `seuil_alert`, `magasin`) VALUES
(20, '2019-08-20', 10, 5, 'com-02', 'pr-4', 'rec-2019-1', 5, 2),
(21, '2019-08-20', 1, 0, 'com-02', 'pr-1', 'rec-2019-1', 0, 1),
(22, '2019-08-20', 1, 0, 'com-02', 'pr-4', 'rec-2019-1', 0, 1),
(23, '2019-08-20', 3, 0, 'com-01', 'pr-5', 'rec-2019-2', 0, 2),
(24, '2019-08-20', 14, 0, 'com-01', 'pr-6', 'rec-2019-2', 0, 1),
(25, '2019-08-20', 2, 0, 'com-01', 'pr-4', 'rec-2019-2', 0, 19),
(26, '2019-01-29', 12, 0, 'com-02', 'pr-2', 'rec-2019-3', 0, 2),
(27, '2019-01-29', 0, 0, 'com-02', 'pr-1', 'rec-2019-3', 0, 3),
(28, '2019-01-29', 6, 0, 'com-02', 'pr-4', 'rec-2019-3', 0, 19),
(29, '2019-01-29', 12, 0, 'com-02', 'pr-2', 'rec-2019-4', 0, 2),
(30, '2019-01-29', 0, 0, 'com-02', 'pr-1', 'rec-2019-4', 0, 3),
(31, '2019-01-29', 6, 0, 'com-02', 'pr-4', 'rec-2019-4', 0, 19),
(32, '2019-02-23', 9, 0, 'com-03', 'pr-1', 'rec-2019-5', 0, 3),
(33, '2019-02-23', 14, 0, 'com-03', 'pr-3', 'rec-2019-5', 0, 2),
(34, '2019-02-23', 26, 0, 'com-03', 'pr-5', 'rec-2019-5', 0, 19);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
