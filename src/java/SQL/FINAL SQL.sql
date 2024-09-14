-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-10-2019 a las 18:33:44
-- Versión del servidor: 10.1.38-MariaDB
-- Versión de PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `flowersx`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `idciudad` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `nombreIngles` varchar(45) NOT NULL,
  `Pais` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`idciudad`, `nombre`, `nombreIngles`, `Pais`) VALUES
(3, 'Bogotá D.C.', '', 8),
(4, 'Nueva York', '', 11),
(5, 'Washington D.C.', '', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_pedido`
--

CREATE TABLE `estado_pedido` (
  `idestado_pedido` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `nombre_en` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_usuario`
--

CREATE TABLE `estado_usuario` (
  `idestado_usuario` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `nombre_en` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `estado_usuario`
--

INSERT INTO `estado_usuario` (`idestado_usuario`, `nombre`, `nombre_en`) VALUES
(1, 'Activo', 'Active'),
(2, 'Inactivo', 'Inactive');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `novedad`
--

CREATE TABLE `novedad` (
  `idNovedad` int(5) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha` date NOT NULL,
  `Pedido` int(5) NOT NULL,
  `Usuario_id` int(5) NOT NULL,
  `tipo_novedad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE `pago` (
  `idPago` int(5) NOT NULL,
  `numero` varchar(45) NOT NULL,
  `codigoDeSeguridad` varchar(45) NOT NULL,
  `fechaDeVencimiento` varchar(5) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `Pedido` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pais`
--

CREATE TABLE `pais` (
  `idpais` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `nombre_ingles` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pais`
--

INSERT INTO `pais` (`idpais`, `nombre`, `nombre_ingles`) VALUES
(2, 'Australia', 'Australia'),
(3, 'Austria', 'Austria'),
(4, 'Brasil', 'Brazil'),
(5, 'Canadá', 'Canada'),
(6, 'Catar', 'Qatar'),
(7, 'Chile', 'Chile'),
(8, 'Colombia', 'Colombia'),
(9, 'Emiratos Árabes Unidos', 'United Arab Emirates'),
(10, 'España', 'Spain'),
(11, 'Estados Unidos', 'United States'),
(12, 'Francia', 'France'),
(13, 'Italia', 'Italy'),
(14, 'Mónaco', 'Monaco'),
(15, 'Países Bajos', 'Netherlands'),
(16, 'Portugal', 'Portugal'),
(17, 'Reino Unido', 'United Kingdom'),
(18, 'Rusia', 'Russia'),
(19, 'Sudáfrica', 'South Africa'),
(20, 'Suecia', 'Sweden'),
(21, 'Suiza', 'Switzerland'),
(22, 'Uruguay', 'Uruguay'),
(24, 'Alemania', 'Germany');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `idPedido` int(5) NOT NULL,
  `fechaDeCreacion` date NOT NULL,
  `fechaDeEntrega` date NOT NULL,
  `subTotal` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `Usuario_id` int(5) NOT NULL,
  `estado_pedido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `idpermisos` int(5) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `nombre_en` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `icon` varchar(45) DEFAULT NULL,
  `permiso_padre` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`idpermisos`, `nombre`, `nombre_en`, `url`, `icon`, `permiso_padre`) VALUES
(1, 'Usuarios', 'Users', NULL, NULL, NULL),
(2, 'Solicitudes', 'Requests', NULL, NULL, NULL),
(3, 'Producción', 'Production', NULL, NULL, NULL),
(4, 'Catálogo', 'Catalog', NULL, NULL, NULL),
(5, 'Crear Usuario', 'Create user', 'Admin/crear-usuario.xhtml', 'x', 1),
(6, 'Gestionar Usuarios', 'Manage users', 'Admin/gestionar-usuarios.xhtml', 'x', 1),
(7, 'Crear Solicitud', 'Create request', 'Admin/crear-solicitud.xhtml', 'x', 2),
(8, 'Gestionar Solicitudes', 'Manage requests', 'Admin/gestionar-solicitudes.xhtml', 'x', 2),
(10, 'Crear Novedad', 'Create novelty', 'Admin/crear-novedad.xhtml', 'x', 3),
(11, 'Gestionar Novedades', 'Manage novelties', 'Admin/gestionar-novedades.xhtml', 'x', 3),
(12, 'Registrar producto', 'Add product', 'Admin/crear-catalogo.xhtml', 'x', 4),
(13, 'Gestionar Catálogo', 'Manage catalog', 'Admin/gestionar-catalogo.xhtml', 'x', 4),
(17, 'Ventas', 'Sales', '', '', NULL),
(18, 'Nuevo pedido', 'New sales order', 'Vendedor/crear-pedido-vendedor.xhtml', 'x', 17),
(19, 'Consultar pedidos', 'Search sales orders', 'Vendedor/gestionar-pedido-vendedor.xhtml', 'x', 17),
(20, 'Pagos', 'Payments', NULL, NULL, NULL),
(21, 'Registrar método de pago', 'Add payment method', 'Cliente/crear-pago.xhtml', 'x', 20),
(22, 'Gestionar métodos de pago', 'Manage payment methods', 'Cliente/gestionar-pago.xhtml', 'x', 20),
(23, 'Comprar', 'Buy', NULL, NULL, NULL),
(24, 'Nuevo pedido', 'New order', 'Cliente/nuevo-pedido.xhtml', 'x', 23),
(25, 'Consultar pedidos', 'Search orders', 'Cliente/gestionar-pedido.xhtml', 'x', 23),
(26, 'Consultar catálogo', 'Catalog lookup', 'Cliente/consultar-catalogo.xhtml', 'x', 4),
(27, 'Consultar pedidos', 'Search orders', 'Admin/consultar-pedidos.xhtml', 'x', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(5) NOT NULL,
  `nombreProducto` varchar(45) NOT NULL,
  `nombreIngles` varchar(45) NOT NULL,
  `foto` text NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `tiempoDeCultivo` int(11) NOT NULL,
  `existencias` int(10) NOT NULL,
  `precio` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `nombreProducto`, `nombreIngles`, `foto`, `descripcion`, `tiempoDeCultivo`, `existencias`, `precio`) VALUES
(1, 'Rosas', 'Roses', '/FlowersXProyecto/Archivos/rosas.jpg', 'Varios colores disponibles', 3, 50000, 300),
(2, 'Orquídeas', 'Orchids', '/FlowersXProyecto/Archivos/orchid.jpg', 'Varios colores disponibles', 6, 20000, 200),
(3, 'Girasoles', 'Sunflowers', '/FlowersXProyecto/Archivos/sunflower.jpg', 'Varios colores disponibles', 3, 80000, 150),
(4, 'Tulipanes', 'Tulips', '/FlowersXProyecto/Archivos/tulip.jpg', 'Varios colores disponibles', 4, 0, 250),
(5, 'Azucenas', 'Lilies', '/FlowersXProyecto/Archivos/azucena.jpg', 'Varios colores disponibles.', 4, 500, 300),
(6, 'Violetas', 'Violets', '/FlowersXProyecto/Archivos/violeta.jpg', 'Disponible en un solo color.', 2, 0, 250),
(7, 'Claveles', 'Carnations', '/FlowersXProyecto/Archivos/flor-clavel.jpg', 'Varios colores disponibles', 4, 600, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_has_pedido`
--

CREATE TABLE `producto_has_pedido` (
  `producto_idProducto` int(5) NOT NULL,
  `pedido_idPedido` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idRol` int(5) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idRol`, `nombre`, `name`) VALUES
(1, 'Administrador', 'Administrator'),
(2, 'Vendedor', 'Sales agent'),
(3, 'Cliente', 'Customer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol_has_permisos`
--

CREATE TABLE `rol_has_permisos` (
  `Rol_idRol` int(5) NOT NULL,
  `permisos_idpermisos` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `rol_has_permisos`
--

INSERT INTO `rol_has_permisos` (`Rol_idRol`, `permisos_idpermisos`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 27),
(2, 17),
(2, 18),
(2, 19),
(3, 20),
(3, 21),
(3, 22),
(3, 23),
(3, 24),
(3, 25),
(3, 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE `solicitud` (
  `idSolicitud` int(5) NOT NULL,
  `fecha` date NOT NULL,
  `destinatario` varchar(45) NOT NULL,
  `soporte1` text NOT NULL,
  `soporte2` text,
  `soporte3` text,
  `pedido` int(5) NOT NULL,
  `usuario` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_novedad`
--

CREATE TABLE `tipo_novedad` (
  `idtiponovedad` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `nombre_en` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(5) NOT NULL,
  `nombre_titular` varchar(45) NOT NULL,
  `apellido_titular` varchar(45) NOT NULL,
  `razonSocial` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `Rol` int(5) NOT NULL,
  `pais` int(11) NOT NULL,
  `ciudad` int(11) NOT NULL,
  `estado_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre_titular`, `apellido_titular`, `razonSocial`, `email`, `password`, `Rol`, `pais`, `ciudad`, `estado_usuario`) VALUES
(20, 'Felipe', 'Hernández', 'FlowersX', 'feehernandezba@gmail.com', 'a873de377ffbd389c31e5fc9f5c6dcd8', 1, 8, 3, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`idciudad`),
  ADD KEY `FK_Pais` (`Pais`);

--
-- Indices de la tabla `estado_pedido`
--
ALTER TABLE `estado_pedido`
  ADD PRIMARY KEY (`idestado_pedido`);

--
-- Indices de la tabla `estado_usuario`
--
ALTER TABLE `estado_usuario`
  ADD PRIMARY KEY (`idestado_usuario`);

--
-- Indices de la tabla `novedad`
--
ALTER TABLE `novedad`
  ADD PRIMARY KEY (`idNovedad`),
  ADD KEY `fk_Novedad_Pedido1_idx` (`Pedido`),
  ADD KEY `fk_Novedad_Usuario1_idx` (`Usuario_id`),
  ADD KEY `fk_novedad_tipo_novedad1_idx` (`tipo_novedad`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`idPago`),
  ADD KEY `fk_Pago_Pedido1_idx` (`Pedido`);

--
-- Indices de la tabla `pais`
--
ALTER TABLE `pais`
  ADD PRIMARY KEY (`idpais`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`idPedido`),
  ADD KEY `fk_Pedido_Usuario1_idx` (`Usuario_id`),
  ADD KEY `fk_pedido_estado_pedido1_idx` (`estado_pedido`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`idpermisos`),
  ADD KEY `fk_permiso_padre` (`permiso_padre`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `producto_has_pedido`
--
ALTER TABLE `producto_has_pedido`
  ADD PRIMARY KEY (`producto_idProducto`,`pedido_idPedido`),
  ADD KEY `fk_producto_has_pedido_pedido1_idx` (`pedido_idPedido`),
  ADD KEY `fk_producto_has_pedido_producto1_idx` (`producto_idProducto`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idRol`);

--
-- Indices de la tabla `rol_has_permisos`
--
ALTER TABLE `rol_has_permisos`
  ADD PRIMARY KEY (`Rol_idRol`,`permisos_idpermisos`),
  ADD KEY `fk_Rol_has_permisos_permisos1_idx` (`permisos_idpermisos`),
  ADD KEY `fk_Rol_has_permisos_Rol1_idx` (`Rol_idRol`);

--
-- Indices de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD PRIMARY KEY (`idSolicitud`),
  ADD KEY `fk_solicitud_pedido1_idx` (`pedido`),
  ADD KEY `fk_solicitud_usuario1_idx` (`usuario`);

--
-- Indices de la tabla `tipo_novedad`
--
ALTER TABLE `tipo_novedad`
  ADD PRIMARY KEY (`idtiponovedad`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Usuario_Rol1_idx` (`Rol`),
  ADD KEY `fk_usuario_pais1_idx` (`pais`),
  ADD KEY `fk_usuario_ciudad1_idx` (`ciudad`),
  ADD KEY `fk_usuario_estado_usuario1_idx` (`estado_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `idciudad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `estado_usuario`
--
ALTER TABLE `estado_usuario`
  MODIFY `idestado_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `novedad`
--
ALTER TABLE `novedad`
  MODIFY `idNovedad` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `idPago` int(5) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pais`
--
ALTER TABLE `pais`
  MODIFY `idpais` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `idPedido` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idProducto` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idRol` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `solicitud`
--
ALTER TABLE `solicitud`
  MODIFY `idSolicitud` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tipo_novedad`
--
ALTER TABLE `tipo_novedad`
  MODIFY `idtiponovedad` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD CONSTRAINT `FK_Pais` FOREIGN KEY (`Pais`) REFERENCES `pais` (`idpais`);

--
-- Filtros para la tabla `novedad`
--
ALTER TABLE `novedad`
  ADD CONSTRAINT `fk_Novedad_Pedido1` FOREIGN KEY (`Pedido`) REFERENCES `pedido` (`idPedido`),
  ADD CONSTRAINT `fk_Novedad_Usuario1` FOREIGN KEY (`Usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `fk_novedad_tipo_novedad1` FOREIGN KEY (`tipo_novedad`) REFERENCES `tipo_novedad` (`idtiponovedad`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `fk_Pago_Pedido1` FOREIGN KEY (`Pedido`) REFERENCES `pedido` (`idPedido`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `fk_Pedido_Usuario1` FOREIGN KEY (`Usuario_id`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `fk_pedido_estado_pedido1` FOREIGN KEY (`estado_pedido`) REFERENCES `estado_pedido` (`idestado_pedido`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD CONSTRAINT `fk_permiso_padre` FOREIGN KEY (`permiso_padre`) REFERENCES `permisos` (`idpermisos`);

--
-- Filtros para la tabla `producto_has_pedido`
--
ALTER TABLE `producto_has_pedido`
  ADD CONSTRAINT `fk_producto_has_pedido_pedido1` FOREIGN KEY (`pedido_idPedido`) REFERENCES `pedido` (`idPedido`),
  ADD CONSTRAINT `fk_producto_has_pedido_producto1` FOREIGN KEY (`producto_idProducto`) REFERENCES `producto` (`idProducto`);

--
-- Filtros para la tabla `rol_has_permisos`
--
ALTER TABLE `rol_has_permisos`
  ADD CONSTRAINT `fk_Rol_has_permisos_Rol1` FOREIGN KEY (`Rol_idRol`) REFERENCES `rol` (`idRol`),
  ADD CONSTRAINT `fk_Rol_has_permisos_permisos1` FOREIGN KEY (`permisos_idpermisos`) REFERENCES `permisos` (`idpermisos`);

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `fk_solicitud_pedido1` FOREIGN KEY (`pedido`) REFERENCES `pedido` (`idPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_solicitud_usuario1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_Usuario_Rol1` FOREIGN KEY (`Rol`) REFERENCES `rol` (`idRol`),
  ADD CONSTRAINT `fk_usuario_ciudad1` FOREIGN KEY (`ciudad`) REFERENCES `ciudad` (`idciudad`),
  ADD CONSTRAINT `fk_usuario_estado_usuario1` FOREIGN KEY (`estado_usuario`) REFERENCES `estado_usuario` (`idestado_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuario_pais1` FOREIGN KEY (`pais`) REFERENCES `pais` (`idpais`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
