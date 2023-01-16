-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 16-01-2023 a las 10:17:57
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
  `fecha` datetime DEFAULT NULL,
  `pokemon1` smallint NOT NULL,
  `pokemon2` smallint DEFAULT NULL,
  `pokemon3` smallint DEFAULT NULL,
  `pokemon4` smallint DEFAULT NULL,
  `pokemon5` smallint DEFAULT NULL,
  `pokemon6` smallint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Equipo`
--

INSERT INTO `Equipo` (`id`, `nombre`, `usernameAutor`, `fecha`, `pokemon1`, `pokemon2`, `pokemon3`, `pokemon4`, `pokemon5`, `pokemon6`) VALUES
('00000000', 'Prueba', NULL, '2022-12-07 14:43:53', 661, 61, 836, 566, 897, 305),
('00000001', 'Prueba', NULL, '2022-12-07 14:43:59', 769, 1, 190, 603, 112, 158),
('00000002', 'Prueba', NULL, '2022-12-07 14:44:04', 751, 904, 670, 516, 219, 524),
('00000003', 'Prueba', NULL, '2022-12-07 14:44:09', 675, 277, 799, 738, 298, 583),
('00000004', 'Prueba', NULL, '2022-12-07 14:44:14', 6, 509, 753, 551, 225, 839),
('00000005', 'Prueba', NULL, '2022-12-07 14:44:19', 216, 84, 650, 709, 743, 254),
('00000006', 'Prueba', NULL, '2022-12-07 14:44:24', 50, 662, 600, 665, 707, 807),
('00000007', 'Prueba', NULL, '2022-12-07 14:44:29', 36, 490, 719, 100, 177, 172),
('00000008', 'Prueba', NULL, '2022-12-07 14:44:34', 607, 580, 41, 383, 720, 58),
('00000009', 'Prueba', NULL, '2022-12-07 14:44:39', 630, 239, 351, 155, 825, 132),
('0000000A', 'Prueba', NULL, '2022-12-07 14:44:44', 93, 873, 498, 611, 86, 395),
('0000000B', 'Prueba', NULL, '2022-12-07 14:44:49', 873, 544, 211, 158, 795, 430),
('0000000C', 'Prueba', NULL, '2022-12-07 14:44:54', 648, 773, 616, 861, 344, 456),
('0000000D', 'Prueba', NULL, '2022-12-07 14:44:59', 89, 663, 348, 724, 808, 151),
('0000000E', 'Prueba', NULL, '2022-12-07 14:45:04', 346, 383, 192, 782, 170, 310),
('0000000F', 'Prueba', NULL, '2022-12-07 14:45:09', 562, 859, 204, 837, 873, 463),
('0000000G', 'Prueba', NULL, '2022-12-07 14:45:14', 429, 122, 14, 298, 889, 198),
('0000000H', 'Prueba', NULL, '2022-12-07 14:45:19', 861, 701, 506, 737, 168, 293),
('0000000I', 'Prueba', NULL, '2022-12-07 14:45:24', 110, 385, 378, 192, 287, 294),
('0000000J', 'Prueba', NULL, '2022-12-07 14:45:29', 859, 624, 572, 846, 474, 249),
('0000000K', 'Prueba', NULL, '2022-12-07 14:45:34', 892, 848, 274, 465, 706, 871),
('0000000L', 'Prueba', NULL, '2022-12-07 14:45:39', 163, 587, 445, 189, 881, 799),
('0000000M', 'Prueba', NULL, '2022-12-07 14:45:44', 423, 137, 714, 226, 109, 398),
('0000000N', 'Prueba', NULL, '2022-12-07 14:45:49', 144, 837, 613, 458, 294, 644),
('0000000O', 'Prueba', NULL, '2022-12-07 14:45:54', 507, 152, 656, 158, 423, 770),
('0000000P', 'Prueba', NULL, '2022-12-07 14:45:59', 427, 708, 480, 206, 115, 416),
('0000000Q', 'Prueba', NULL, '2022-12-07 14:46:04', 516, 267, 403, 84, 455, 745),
('0000000R', 'Prueba', NULL, '2022-12-07 14:46:09', 231, 371, 144, 627, 398, 536),
('0000000S', 'Prueba', NULL, '2022-12-07 14:46:14', 852, 322, 395, 745, 626, 103),
('0000000T', 'Prueba', NULL, '2022-12-07 14:46:19', 515, 407, 261, 596, 601, 677),
('0000000U', 'Prueba', NULL, '2022-12-07 14:46:24', 112, 447, 100, 715, 803, 148),
('0000000V', 'Prueba', NULL, '2022-12-07 14:46:29', 834, 54, 28, 327, 126, 613),
('0000000W', 'Prueba', NULL, '2022-12-07 14:46:34', 497, 83, 604, 2, 746, 329),
('0000000X', 'Prueba', NULL, '2022-12-07 14:46:39', 198, 316, 835, 368, 751, 829),
('0000000Y', 'Prueba', NULL, '2022-12-07 14:46:44', 767, 669, 36, 893, 656, 327),
('0000000Z', 'Prueba', NULL, '2022-12-07 14:46:49', 850, 410, 459, 410, 675, 97),
('00000010', 'Prueba', NULL, '2022-12-07 14:46:54', 433, 70, 523, 46, 223, 636),
('00000011', 'Prueba', NULL, '2022-12-07 14:46:59', 286, 27, 247, 101, 820, 593),
('00000012', 'Prueba', NULL, '2022-12-07 14:47:04', 191, 830, 572, 49, 680, 283),
('00000013', 'Prueba', NULL, '2022-12-07 14:47:09', 46, 877, 569, 783, 197, 201),
('00000014', 'Prueba', NULL, '2022-12-07 14:47:14', 721, 605, 449, 415, 156, 671),
('00000015', 'Prueba', NULL, '2022-12-07 14:47:19', 643, 315, 629, 544, 473, 315),
('00000016', 'Prueba', NULL, '2022-12-07 14:47:24', 241, 208, 697, 890, 148, 380),
('00000017', 'Prueba', NULL, '2022-12-07 14:47:30', 875, 235, 109, 463, 759, 228),
('00000018', 'Prueba', NULL, '2022-12-07 14:47:35', 460, 129, 557, 190, 196, 761),
('00000019', 'Prueba', NULL, '2022-12-07 14:47:40', 715, 543, 828, 530, 673, 274),
('0000001A', 'Prueba', NULL, '2022-12-07 14:47:45', 303, 232, 272, 836, 435, 86),
('0000001B', 'Prueba', NULL, '2022-12-07 14:47:50', 713, 812, 455, 134, 195, 830),
('0000001C', 'Prueba', NULL, '2022-12-07 14:47:55', 621, 460, 28, 371, 255, 855),
('0000001D', 'Prueba', NULL, '2022-12-07 14:48:00', 824, 769, 62, 76, 31, 867),
('0000001E', 'Prueba', NULL, '2022-12-07 14:48:05', 117, 280, 121, 13, 276, 170),
('0000001F', 'Prueba', NULL, '2022-12-07 14:48:10', 453, 502, 392, 196, 271, 153),
('0000001G', 'Prueba', NULL, '2022-12-07 14:48:15', 360, 140, 183, 378, 411, 155),
('0000001H', 'Prueba', NULL, '2022-12-07 14:48:20', 774, 586, 701, 577, 332, 215),
('0000001I', 'Prueba', NULL, '2022-12-07 14:48:25', 424, 498, 477, 323, 687, 57),
('0000001J', 'Prueba', NULL, '2022-12-07 14:48:30', 714, 767, 870, 536, 891, 21),
('0000001K', 'Prueba', NULL, '2022-12-07 14:48:35', 461, 273, 626, 295, 732, 788),
('0000001L', 'Prueba', NULL, '2022-12-07 14:48:40', 651, 894, 632, 606, 151, 410),
('0000001M', 'Prueba', NULL, '2022-12-07 14:48:45', 362, 450, 128, 243, 201, 638),
('0000001N', 'Prueba', NULL, '2022-12-07 14:48:50', 591, 42, 470, 213, 553, 384),
('0000001O', 'Prueba', NULL, '2022-12-07 14:48:55', 678, 612, 688, 888, 820, 579),
('0000001P', 'Prueba', NULL, '2022-12-07 14:49:00', 21, 619, 500, 811, 180, 233),
('0000001Q', 'Prueba', NULL, '2022-12-07 14:49:05', 733, 198, 120, 678, 830, 39),
('0000001R', 'Prueba', NULL, '2022-12-07 14:49:10', 413, 113, 489, 79, 804, 444),
('0000001S', 'Prueba', NULL, '2022-12-07 14:49:15', 340, 481, 225, 24, 548, 115),
('0000001T', 'Prueba', NULL, '2022-12-07 14:49:20', 272, 281, 564, 841, 342, 690),
('0000001U', 'Prueba', NULL, '2022-12-07 14:49:25', 348, 848, 500, 692, 117, 563),
('0000001V', 'Prueba', NULL, '2022-12-07 14:49:30', 382, 352, 120, 30, 210, 583),
('0000001W', 'Prueba', NULL, '2022-12-07 14:49:35', 374, 765, 636, 233, 50, 567),
('0000001X', 'Prueba', NULL, '2022-12-07 14:49:40', 518, 818, 731, 101, 853, 574),
('0000001Y', 'Prueba', NULL, '2022-12-07 14:49:45', 286, 361, 799, 573, 760, 332),
('0000001Z', 'Prueba', NULL, '2022-12-07 14:49:50', 200, 583, 344, 888, 131, 468),
('00000020', 'Prueba', NULL, '2022-12-07 14:49:55', 427, 111, 125, 448, 505, 702),
('00000021', 'Prueba', NULL, '2022-12-07 14:50:00', 870, 218, 373, 634, 801, 256),
('00000022', 'Prueba', NULL, '2022-12-07 14:50:05', 523, 741, 27, 737, 225, 245),
('00000023', 'Prueba', NULL, '2022-12-07 14:50:10', 680, 198, 64, 642, 57, 30),
('00000024', 'Prueba', NULL, '2022-12-07 14:50:15', 252, 841, 329, 484, 14, 657),
('00000025', 'Prueba', NULL, '2022-12-07 14:50:20', 351, 735, 307, 441, 894, 246),
('00000026', 'Prueba', NULL, '2022-12-07 14:50:25', 797, 885, 799, 526, 127, 555),
('00000027', 'Prueba', NULL, '2022-12-07 14:50:30', 448, 369, 598, 358, 270, 869),
('00000028', 'Prueba', NULL, '2022-12-07 14:50:36', 419, 278, 902, 206, 270, 698),
('00000029', 'Prueba', NULL, '2022-12-07 14:50:41', 210, 145, 596, 196, 119, 166),
('0000002A', 'Prueba', NULL, '2022-12-07 14:50:46', 608, 503, 498, 848, 584, 383),
('0000002B', 'Prueba', NULL, '2022-12-07 14:50:51', 178, 5, 246, 348, 97, 429),
('0000002C', 'Prueba', NULL, '2022-12-07 14:50:56', 255, 413, 637, 544, 561, 389),
('0000002D', 'Prueba', NULL, '2022-12-07 14:51:01', 779, 721, 429, 85, 515, 577),
('0000002E', 'Prueba', NULL, '2022-12-07 14:51:06', 3, 510, 22, 449, 125, 480),
('0000002F', 'Prueba', NULL, '2022-12-07 14:51:11', 572, 64, 428, 236, 426, 612),
('0000002G', 'Prueba', NULL, '2022-12-07 14:51:16', 111, 571, 48, 105, 738, 529),
('0000002H', 'Prueba', NULL, '2022-12-07 14:51:21', 393, 259, 546, 866, 179, 786),
('0000002I', 'Prueba', NULL, '2022-12-07 14:51:26', 278, 311, 47, 649, 594, 84),
('0000002J', 'Prueba', NULL, '2022-12-07 14:51:31', 419, 35, 45, 625, 786, 571),
('0000002K', 'Prueba', NULL, '2022-12-07 14:51:36', 586, 235, 637, 406, 252, 495),
('0000002L', 'Prueba', NULL, '2022-12-07 14:51:41', 162, 39, 643, 794, 507, 126),
('0000002M', 'Prueba', NULL, '2022-12-07 14:51:46', 467, 432, 146, 814, 867, 791),
('0000002N', 'Prueba', NULL, '2022-12-07 14:51:51', 16, 265, 347, 255, 773, 659),
('0000002O', 'Prueba', NULL, '2022-12-07 14:51:56', 190, 768, 353, 768, 735, 328),
('0000002P', 'Prueba', NULL, '2022-12-07 14:52:01', 817, 235, 504, 71, 573, 748),
('0000002Q', 'Prueba', NULL, '2022-12-07 14:52:06', 14, 473, 783, 292, 9, 224),
('0000002R', 'Prueba', NULL, '2022-12-07 14:52:11', 726, 536, 399, 148, 880, 248),
('0000002S', 'Hola', NULL, '2022-12-09 11:59:43', 1, 2, 10, 20, 50, 70),
('0000002T', 'Probando subida', 'test', '2022-12-09 12:38:46', 1, 0, 0, 0, 0, 0),
('0000002U', 'Dame fav', 'SFKao', '2022-12-09 16:51:47', 672, 627, 853, 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Favoritos`
--

CREATE TABLE `Favoritos` (
  `username` varchar(25) NOT NULL,
  `id` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Favoritos`
--

INSERT INTO `Favoritos` (`username`, `id`) VALUES
('SFKao', '0000001O'),
('SFKao', '0000002A'),
('SFKao', '0000002G'),
('SFKao', '0000002J'),
('SFKao', '0000002L'),
('SFKao', '0000002O'),
('SFKao', '0000002R'),
('SFKao', '0000002S'),
('SFKao', '0000002U'),
('test', '0000002T');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Likes`
--

CREATE TABLE `Likes` (
  `username` varchar(25) NOT NULL,
  `id` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Likes`
--

INSERT INTO `Likes` (`username`, `id`) VALUES
('SFKao', '0000000E'),
('SFKao', '00000015'),
('test', '0000001A'),
('SFKao', '0000001L'),
('SFKao', '0000001O'),
('SFKao', '0000001Z'),
('SFKao', '0000002A'),
('SFKao', '0000002D'),
('SFKao', '0000002F'),
('SFKao', '0000002G'),
('SFKao', '0000002J'),
('SFKao', '0000002L'),
('SFKao', '0000002M'),
('SFKao', '0000002O'),
('test', '0000002P'),
('SFKao', '0000002R'),
('test', '0000002R'),
('SFKao', '0000002S'),
('test', '0000002S'),
('SFKao', '0000002T'),
('test', '0000002T'),
('SFKao', '0000002U');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ListaAmigos`
--

CREATE TABLE `ListaAmigos` (
  `username1` varchar(25) NOT NULL,
  `username2` varchar(25) NOT NULL,
  `estado` enum('pendiente','aceptada','rechazada') NOT NULL DEFAULT 'pendiente'
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
  `apikey` char(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pk1` smallint DEFAULT NULL,
  `pk2` smallint DEFAULT NULL,
  `pk3` smallint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Usuario`
--

INSERT INTO `Usuario` (`username`, `email`, `saltedHash`, `salt`, `apikey`, `pk1`, `pk2`, `pk3`) VALUES
('SFKao', 'SFKao', '$100801$/i6wm/ixlRm8PQ2ywMacLA==$H+LFUtnpuWoa0LLouTup5QG4muKI7dRR7aO7o5boLXI=', 'Xkâ‡H¹”ªÝ:!`ˆ²', '1F65F3A6FD397469677EEDE5475F04B3377B8E12893243FE447F9A0E311AC70D', NULL, NULL, NULL),
('test', 'test', '$100801$4MfvIdmSYPZpUCOzmGeIDA==$VfXrOLanPKQGK/stQ6/jxwFwUD2c81qNlT0hmiHZMNU=', 'þšŒ:d£(¬àÙèÆŸŒ', 'E4D8B3065FBF61C1697E7DC3650B3FF3B06391778308BDE0570B5218EFCAAADB', NULL, NULL, NULL);

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
  ADD KEY `fk_amistad_2` (`username2`),
  ADD KEY `username1` (`username1`);

--
-- Indices de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `apikey` (`apikey`) USING BTREE;

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
