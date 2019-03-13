-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 13, 2019 at 01:40 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(19),
(19);

-- --------------------------------------------------------

--
-- Table structure for table `magasin`
--

CREATE TABLE `magasin` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `magasin`
--

INSERT INTO `magasin` (`id`, `address`, `description`, `libelle`, `reference`) VALUES
(1, NULL, NULL, 'magasin', 'mag-3'),
(2, NULL, NULL, 'magasin fst', 'mag-1'),
(3, NULL, NULL, 'magasin central', 'mag-2');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL,
  `qte` int(11) NOT NULL,
  `qte_deffectueuse` int(11) NOT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `reference_commande` varchar(255) DEFAULT NULL,
  `reference_produit` varchar(255) DEFAULT NULL,
  `reference_reception` varchar(255) DEFAULT NULL,
  `seuil_alert` int(11) NOT NULL,
  `magasin` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`id`, `qte`, `qte_deffectueuse`, `reference`, `reference_commande`, `reference_produit`, `reference_reception`, `seuil_alert`, `magasin`) VALUES
(7, 20, 0, NULL, 'com-01', 'pr-1', 'rec-2019-1', 0, 2),
(8, 99, 0, NULL, 'com-01', 'pr-3', 'rec-2019-1', 0, 2),
(9, 22, 0, NULL, 'com-01', 'pr-4', 'rec-2019-1', 0, 2),
(10, 15, 0, NULL, 'com-01', 'pr-1', 'rec-2019-2', 0, 3),
(11, 99, 0, NULL, 'com-01', 'pr-3', 'rec-2019-2', 0, 1),
(12, 10, 0, NULL, 'com-01', 'pr-4', 'rec-2019-2', 0, 2),
(13, 15, 0, NULL, 'com-01', 'pr-1', 'rec-2019-3', 0, 2),
(14, 14, 0, NULL, 'com-01', 'pr-3', 'rec-2019-3', 0, 1),
(15, 10, 0, NULL, 'com-01', 'pr-4', 'rec-2019-3', 0, 2),
(16, 1, 0, NULL, 'com-02', 'pr-4', 'rec-2019-5', 0, 2),
(17, 1, 0, NULL, 'com-02', 'pr-1', 'rec-2019-5', 0, 1),
(18, 1, 0, NULL, 'com-02', 'pr-4', 'rec-2019-5', 0, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `magasin`
--
ALTER TABLE `magasin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn1wvgg3tux8yqes0unb34kkt` (`magasin`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
