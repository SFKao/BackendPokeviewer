-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 29-11-2022 a las 11:26:43
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Pokeviewer`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Equipo`
--

CREATE TABLE `Equipo` (
  `id` char(8) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `usernameAutor` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `pokemon1` smallint NOT NULL,
  `pokemon2` smallint DEFAULT NULL,
  `pokemon3` smallint DEFAULT NULL,
  `pokemon4` smallint DEFAULT NULL,
  `pokemon5` smallint DEFAULT NULL,
  `pokemon6` smallint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Favoritos`
--

CREATE TABLE `Favoritos` (
  `username` varchar(25) NOT NULL,
  `id` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Likes`
--

CREATE TABLE `Likes` (
  `username` varchar(25) NOT NULL,
  `id` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ListaAmigos`
--

CREATE TABLE `ListaAmigos` (
  `username1` varchar(25) NOT NULL,
  `username2` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE `Usuario` (
  `username` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `saltedHash` char(77) NOT NULL,
  `salt` char(16) NOT NULL,
  `apikey` char(35) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Equipo`
--
ALTER TABLE `Equipo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_username` (`usernameAutor`);

--
-- Indices de la tabla `Favoritos`
--
ALTER TABLE `Favoritos`
  ADD PRIMARY KEY (`username`,`id`);

--
-- Indices de la tabla `Likes`
--
ALTER TABLE `Likes`
  ADD PRIMARY KEY (`username`,`id`),
  ADD KEY `fk_likes_equipo` (`id`);

--
-- Indices de la tabla `ListaAmigos`
--
ALTER TABLE `ListaAmigos`
  ADD PRIMARY KEY (`username1`,`username2`),
  ADD KEY `fk_amistad_2` (`username2`);

--
-- Indices de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Equipo`
--
ALTER TABLE `Equipo`
  ADD CONSTRAINT `fk_username` FOREIGN KEY (`usernameAutor`) REFERENCES `Usuario` (`username`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Filtros para la tabla `Likes`
--
ALTER TABLE `Likes`
  ADD CONSTRAINT `fk_likes_equipo` FOREIGN KEY (`id`) REFERENCES `Equipo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_likes_username` FOREIGN KEY (`username`) REFERENCES `Usuario` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ListaAmigos`
--
ALTER TABLE `ListaAmigos`
  ADD CONSTRAINT `fk_amistad_1` FOREIGN KEY (`username1`) REFERENCES `Usuario` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_amistad_2` FOREIGN KEY (`username2`) REFERENCES `Usuario` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
