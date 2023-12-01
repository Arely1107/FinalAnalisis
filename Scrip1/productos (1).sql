-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-11-2023 a las 17:27:16
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `productos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `idCategoria` int(11) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `categoria` varchar(50) NOT NULL,
  `subcategoria` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`idCategoria`, `descripcion`, `categoria`, `subcategoria`) VALUES
(2, 'Vitaminas y suplementos', 'Antioxidantes', 'Medicamentos'),
(3, 'Termometro', 'Aparato de medicion', 'Equipo y Botiquin'),
(4, 'Anticaspa', 'Capilar', 'Dermocosmeticos'),
(5, 'Aerosoles', 'Antimicóticos Aerosoles', 'Medicamentos'),
(6, 'Capilares', 'Dermatológicos', 'Medicamentos'),
(7, 'Equipos Respiratorios', 'Equipo y accesorios médicos', 'Equipo y botiquín'),
(8, 'Belleza', 'Vitaminas y suplementos', 'Vitamínicos'),
(9, 'Piel Sensible', 'Facial', 'Dermocosméticos'),
(10, 'Aceites', 'Cuidados del bebé', 'Bebés'),
(11, 'Enjuagues bucales', 'Cuidado bucal', 'Cuidado personal y belleza'),
(12, 'Leches en polvo', 'Leche líquida y en polvo', 'Alimentos y bebidas'),
(13, 'Suplementos', 'Vitaminas y Suplementos', 'San Pablo Natural'),
(14, 'Salud sexual', 'Bienestar sexual', 'Pruebas'),
(15, 'Guantes', 'Esenciales Covid', 'Equipo y botiquín');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `categoria` varchar(255) NOT NULL,
  `idCategoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `nombre`, `precio`, `stock`, `descripcion`, `categoria`, `idCategoria`) VALUES
(8, 'Oxal Infantil 2 a 6 años', 167.50, 150, '10 ML Suspensión Frasco Quinfamida 100 MG', 'Parásitos', NULL),
(9, 'Vermox', 117.00, 15, '6 Tableta Caja Mebendazol 100 MG', 'Parásitos', NULL),
(10, 'Cerave Crema hidratante Para Piel Seca A Muy Seca Sin Perfume', 440.00, 16, '454 G Crema Tarro', 'Hidratación Corporal', NULL),
(11, 'Electrolit Fresa kiwi', 22.00, 160, '625 ML Líquido Botella Cloruro de sodio 12 MG', 'Sueros orales', NULL),
(12, 'Redoxon Naranja', 95.00, 75, '10 Tableta Tubo Vitamina c 1 G', 'Prevención De Resfriado', NULL),
(13, 'Tabcin Active Día Alivio para las Molestias de la Gripe y Tos', 59.50, 85, '12 Cápsulas Caja Paracetamol 250 MG', 'Antigripales', NULL),
(14, 'Diapro Predoblado Gel', 83.00, 5, '10 Piezas Bolsa', 'Ropa Interior Desechable', NULL),
(15, 'Colgate Hilo Dental Total Encerado', 63.50, 7, '1 Pieza Caja', 'Hilos Dentales', NULL),
(30, 'Dalux Guantes de Látex Talla Chica', 44.50, 36, '', 'Guantes', 15),
(31, 'Aurax Guantes de Látex de Color Blanco Talla Mediana', 37.00, 45, '10 Piezas Bolsa', 'Guantes', 15),
(32, 'Miyako Guantes De látex medianos', 378.00, 19, '100 Piezas Cajaa', 'Guantes', 15),
(33, 'Johnson\'s Gotas de brillo Spray para peinar', 87.00, 23, '200 ML Líquido Atomizador', 'Aceites', 10),
(34, 'Mennen Aceite para Bebé Baby Magic Hipoalergénico', 82.50, 15, '200 ML Aceite Envase', 'Aceites', 10),
(35, 'Jaloma Aceite para Bebé', 28.50, 32, '120 ML Botella', 'Aceites', 10),
(36, 'Listerine Ultraclean Anti-Sarro Enjuague Bucal', 128.00, 45, '', 'Enjuagues bucales', 11),
(37, 'Bexident Estomatológicos Encías', 488.00, 5, '40 ML Spray Botella', 'Enjuagues bucales', 11),
(38, 'Gum Enjuague bucal Paroex gingivitis', 188.00, 15, '300 ML Botella', 'Enjuagues bucales', 11),
(39, 'Vantal Enjuague bucal', 258.00, 9, '360 ML Solución Botella', 'Enjuagues bucales', 11),
(40, 'Vantal Enjuague bucal', 418.00, 3, '40 ML Solución Atomizador', 'Enjuagues bucales', 11),
(41, 'prueba', 15.00, 4, 'prueba', 'Anticaspa', NULL),
(42, 'prueba2', 25.00, 15, 'dddd', 'Anticaspa', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productosvendidos`
--

CREATE TABLE `productosvendidos` (
  `id_Venta` int(11) NOT NULL,
  `id_Producto` int(11) NOT NULL,
  `fecha_Venta` date DEFAULT NULL,
  `piezas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productosvendidos`
--

INSERT INTO `productosvendidos` (`id_Venta`, `id_Producto`, `fecha_Venta`, `piezas`) VALUES
(1, 15, '2023-11-18', 15),
(2, 14, '2023-11-01', 14),
(3, 34, '2023-11-21', 1),
(4, 34, '2023-11-20', 0),
(5, 34, '2023-11-19', 3),
(6, 34, '2023-11-18', 4),
(7, 34, '2023-11-17', 5),
(8, 34, '2023-11-16', 6),
(9, 34, '2023-11-15', 5),
(10, 34, '2023-11-14', 2),
(11, 34, '2023-11-13', 0),
(12, 34, '2023-11-12', 4),
(13, 34, '2023-11-11', 2),
(14, 34, '2023-11-10', 1),
(15, 34, '2023-11-09', 5),
(16, 34, '2023-11-08', 0),
(17, 34, '2023-11-07', 9),
(18, 34, '2023-11-06', 6),
(19, 34, '2023-11-05', 0),
(20, 34, '2023-11-04', 7),
(21, 34, '2023-11-03', 8),
(22, 34, '2023-11-02', 1),
(23, 34, '2023-11-01', 5),
(24, 34, '2023-10-31', 0),
(25, 34, '2023-10-30', 0),
(26, 34, '2023-10-29', 0),
(27, 34, '2023-10-28', 0),
(28, 34, '2023-10-27', 0),
(29, 34, '2023-10-26', 0),
(30, 34, '2023-10-25', 0),
(31, 34, '2023-10-24', 0),
(32, 34, '2023-10-23', 0),
(33, 34, '2023-10-22', 0),
(34, 34, '2023-10-21', 0),
(35, 34, '2023-10-20', 0),
(36, 34, '2023-10-19', 0),
(37, 34, '2023-10-18', 0),
(38, 34, '2023-10-17', 0),
(39, 34, '2023-10-16', 0),
(40, 34, '2023-10-15', 0),
(41, 34, '2023-10-14', 0),
(42, 34, '2023-10-13', 0),
(43, 34, '2023-10-12', 0),
(44, 11, '2023-10-01', 3),
(45, 11, '2023-10-02', 3),
(46, 11, '2023-10-03', 1),
(47, 11, '2023-10-04', 2),
(48, 11, '2023-10-05', 3),
(49, 11, '2023-10-06', 3),
(50, 11, '2023-10-07', 0),
(51, 11, '2023-10-08', 5),
(52, 11, '2023-10-09', 6),
(53, 11, '2023-10-10', 7),
(54, 11, '2023-10-11', 4),
(55, 11, '2023-10-12', 3),
(56, 11, '2023-10-13', 6),
(57, 11, '2023-10-14', 8),
(58, 11, '2023-10-15', 1),
(59, 11, '2023-10-16', 0),
(60, 11, '2023-10-17', 0),
(61, 11, '2023-10-18', 0),
(62, 11, '2023-10-19', 1),
(63, 11, '2023-10-20', 2),
(64, 11, '2023-10-21', 3),
(65, 11, '2023-10-22', 5),
(66, 11, '2023-10-23', 8),
(67, 11, '2023-10-24', 0),
(68, 11, '2023-10-25', 1),
(69, 11, '2023-10-26', 10),
(70, 11, '2023-10-27', 1),
(71, 11, '2023-10-28', 2),
(72, 11, '2023-10-29', 5),
(73, 11, '2023-10-30', 6),
(74, 11, '2023-10-31', 3),
(75, 11, '2023-11-01', 7),
(76, 11, '2023-11-02', 9),
(77, 11, '2023-11-03', 5),
(78, 11, '2023-11-04', 3),
(79, 11, '2023-11-05', 1),
(80, 11, '2023-11-06', 3),
(81, 11, '2023-11-07', 1),
(82, 11, '2023-11-08', 2),
(83, 11, '2023-11-09', 5),
(84, 11, '2023-11-10', 6),
(85, 11, '2023-11-11', 1),
(86, 11, '2023-11-12', 4),
(87, 11, '2023-11-13', 2),
(88, 11, '2023-11-14', 5),
(89, 11, '2023-11-15', 8),
(90, 11, '2023-11-16', 0),
(91, 11, '2023-11-17', 1),
(92, 11, '2023-11-18', 4),
(93, 11, '2023-11-19', 6),
(94, 11, '2023-11-20', 15),
(95, 11, '2023-11-21', 30),
(96, 11, '2023-11-22', 24),
(97, 11, '2023-11-23', 16),
(98, 11, '2023-11-24', 15),
(99, 11, '2023-11-25', 1),
(100, 11, '2023-11-26', 5),
(101, 11, '2023-11-27', 1),
(102, 11, '2023-11-28', 2),
(103, 11, '2023-11-29', 8),
(104, 11, '2023-11-30', 9),
(105, 8, '2023-10-02', 0),
(106, 8, '2023-10-03', 0),
(107, 8, '2023-10-04', 0),
(108, 8, '2023-10-05', 0),
(109, 8, '2023-10-06', 0),
(110, 8, '2023-10-07', 0),
(111, 8, '2023-10-08', 0),
(112, 8, '2023-10-09', 1),
(113, 8, '2023-10-10', 1),
(114, 8, '2023-10-11', 1),
(115, 8, '2023-10-12', 1),
(116, 8, '2023-10-13', 1),
(117, 8, '2023-10-14', 1),
(118, 8, '2023-10-15', 1),
(119, 8, '2023-10-16', 1),
(120, 8, '2023-10-17', 1),
(121, 8, '2023-10-18', 1),
(122, 8, '2023-10-19', 1),
(123, 8, '2023-10-20', 2),
(124, 8, '2023-10-21', 2),
(125, 8, '2023-10-22', 2),
(126, 8, '2023-10-23', 2),
(127, 8, '2023-10-24', 2),
(128, 8, '2023-10-25', 2),
(129, 8, '2023-10-26', 3),
(130, 8, '2023-10-27', 3),
(131, 8, '2023-10-28', 3),
(132, 8, '2023-10-29', 3),
(133, 8, '2023-10-30', 3),
(134, 8, '2023-10-31', 3),
(135, 8, '2023-11-01', 3),
(136, 8, '2023-11-02', 4),
(137, 8, '2023-11-03', 4),
(138, 8, '2023-11-04', 4),
(139, 8, '2023-11-05', 5),
(140, 8, '2023-11-06', 5),
(141, 8, '2023-11-07', 5),
(142, 8, '2023-11-08', 5),
(143, 8, '2023-11-09', 5),
(144, 8, '2023-11-10', 5),
(145, 8, '2023-11-11', 5),
(146, 8, '2023-11-12', 6),
(147, 8, '2023-11-13', 6),
(148, 8, '2023-11-14', 6),
(149, 8, '2023-11-15', 6),
(150, 8, '2023-11-16', 6),
(151, 8, '2023-11-17', 7),
(152, 8, '2023-11-18', 7),
(153, 8, '2023-11-19', 8),
(154, 8, '2023-11-20', 8),
(155, 8, '2023-11-21', 8),
(156, 8, '2023-11-22', 8),
(157, 8, '2023-11-23', 9),
(158, 8, '2023-11-24', 9),
(159, 8, '2023-11-25', 10),
(160, 8, '2023-11-26', 15),
(161, 8, '2023-11-27', 15),
(162, 8, '2023-11-28', 16),
(163, 8, '2023-11-29', 24),
(164, 8, '2023-11-30', 30),
(165, 31, '2023-11-02', 56);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`idCategoria`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_producto_categoria` (`idCategoria`);

--
-- Indices de la tabla `productosvendidos`
--
ALTER TABLE `productosvendidos`
  ADD PRIMARY KEY (`id_Venta`),
  ADD KEY `FK_productosvendidos_producto` (`id_Producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `idCategoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT de la tabla `productosvendidos`
--
ALTER TABLE `productosvendidos`
  MODIFY `id_Venta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=166;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `fk_producto_categoria` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`idCategoria`);

--
-- Filtros para la tabla `productosvendidos`
--
ALTER TABLE `productosvendidos`
  ADD CONSTRAINT `FK_productosvendidos_producto` FOREIGN KEY (`id_Producto`) REFERENCES `producto` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
