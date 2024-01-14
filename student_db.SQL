-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : sam. 13 jan. 2024 à 18:30
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `student_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `CIN` int(8) NOT NULL,
  `Nom` varchar(25) NOT NULL,
  `Prenom` varchar(25) NOT NULL,
  `Adresse` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `etudiant`
--

INSERT INTO `etudiant` (`CIN`, `Nom`, `Prenom`, `Adresse`) VALUES
('00625094', 'William', 'Jones', 'Argentina'),
('36332805', 'Charlie', 'Thomas', 'Nigeria'),
('93912355', 'Harry', 'Garcia', 'USA'),
('90486409', 'Irene', 'Davis', 'Nigeria'),
('47798586', 'Zoe', 'Williams', 'Russia'),
('78914531', 'Lily', 'Martin', 'Italy'),
('55307937', 'Bob', 'Anderson', 'India'),
('16380330', 'Jack', 'Smith', 'Russia'),
('36486288', 'Bob', 'Williams', 'USA'),
('74093658', 'Sophia', 'Brown', 'Argentina'),
('34758254', 'Bob', 'Brown', 'Japan'),
('72425894', 'Lily', 'Lopez', 'Nigeria'),
('41954785', 'Oscar', 'Jones', 'France'),
('05997922', 'Jack', 'Jackson', 'USA'),
('06044497', 'Grace', 'Thomas', 'Indonesia'),
('54498382', 'Oscar', 'Moore', 'UK'),
('92734749', 'Sophia', 'Jones', 'Italy'),
('87939833', 'Diana', 'Taylor', 'Brazil'),
('69043165', 'Zoe', 'Davis', 'Nigeria'),
('86432549', 'Noah', 'Davis', 'USA')
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
