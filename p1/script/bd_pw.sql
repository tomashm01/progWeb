 Servidor: oraclepr.uco.es -  Base de datos: i02pinma
-- phpMyAdmin SQL Dump
-- version 2.7.0-pl2
-- http://www.phpmyadmin.net
-- 
-- Servidor: oraclepr.uco.es
-- Tiempo de generaci�n: 03-11-2022 a las 23:44:43
-- Versi�n del servidor: 5.1.73
-- Versi�n de PHP: 5.3.3
-- 
-- Base de datos: `i02pinma`
-- 

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `Bono`
-- 

DROP TABLE IF EXISTS `Bono`;
CREATE TABLE `Bono` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaExpiracion` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `Bono`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `BonoReserva`
-- 

DROP TABLE IF EXISTS `BonoReserva`;
CREATE TABLE `BonoReserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idBono` int(11) NOT NULL,
  `idReserva` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idBono` (`idBono`,`idReserva`),
  KEY `fk_bono_reserva` (`idReserva`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `BonoReserva`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `Kart`
-- 

DROP TABLE IF EXISTS `Kart`;
CREATE TABLE `Kart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `esAdulto` tinyint(1) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `idPista` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idPista` (`idPista`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- 
-- Volcar la base de datos para la tabla `Kart`
-- 

INSERT INTO `Kart` VALUES (1, 1, 'MANTENIMIENTO', 1);
INSERT INTO `Kart` VALUES (2, 2, 'DISPONIBLE', 1);

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `KartReserva`
-- 

DROP TABLE IF EXISTS `KartReserva`;
CREATE TABLE `KartReserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idKart` int(11) NOT NULL,
  `idReserva` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idKart` (`idKart`,`idReserva`),
  KEY `fk_kart_reserva` (`idReserva`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- 
-- Volcar la base de datos para la tabla `KartReserva`
-- 


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `Pista`
-- 

DROP TABLE IF EXISTS `Pista`;
CREATE TABLE `Pista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(64) NOT NULL,
  `dificultad` varchar(16) NOT NULL,
  `maxKarts` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- 
-- Volcar la base de datos para la tabla `Pista`
-- 

INSERT INTO `Pista` VALUES (1, 'Mario Kart', 'FAMILIAR', 20);
INSERT INTO `Pista` VALUES (2, 'CordobaCity', 'ADULTOS', 50);

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `Reserva`
-- 

DROP TABLE IF EXISTS `Reserva`;
CREATE TABLE `Reserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idPista` int(11) NOT NULL,
  `precio` float NOT NULL,
  `descuento` float NOT NULL,
  `fecha` date NOT NULL,
  `duracion` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `numAdultos` int(11) DEFAULT NULL,
  `numMenores` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reserva_user` (`idUser`),
  KEY `fk_reserva_pista` (`idPista`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- 
-- Volcar la base de datos para la tabla `Reserva`
-- 

INSERT INTO `Reserva` VALUES (1, 1, 1, 200, 10, '2022-10-30', 100, 'FAMILIAR', 0, 0);
INSERT INTO `Reserva` VALUES (3, 5, 1, 200, 10, '2022-10-30', 100, 'FAMILIAR', 0, 0);

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `User`
-- 

DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(32) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `nombreCompleto` varchar(64) NOT NULL,
  `fechaInscripcion` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

-- 
-- Volcar la base de datos para la tabla `User`
-- 

INSERT INTO `User` VALUES (1, 'tomas@gmail.com', '2001-09-25', 'Tom�s Hidalgo Mart�n', '2022-10-11');
INSERT INTO `User` VALUES (5, 'i02pinma@uco.es', '2002-12-31', 'Alvaro Pino', '2022-10-30');

-- 
-- Filtros para las tablas descargadas (dump)
-- 

-- 
-- Filtros para la tabla `BonoReserva`
-- 
ALTER TABLE `BonoReserva`
  ADD CONSTRAINT `fk_bono_pista` FOREIGN KEY (`idBono`) REFERENCES `Bono` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_bono_reserva` FOREIGN KEY (`idReserva`) REFERENCES `Reserva` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- 
-- Filtros para la tabla `Kart`
-- 
ALTER TABLE `Kart`
  ADD CONSTRAINT `fk_kart_pista` FOREIGN KEY (`idPista`) REFERENCES `Pista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- 
-- Filtros para la tabla `KartReserva`
-- 
ALTER TABLE `KartReserva`
  ADD CONSTRAINT `fk_kart_kart` FOREIGN KEY (`idKart`) REFERENCES `Kart` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_kart_reserva` FOREIGN KEY (`idReserva`) REFERENCES `Reserva` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

-- 
-- Filtros para la tabla `Reserva`
-- 
ALTER TABLE `Reserva`
  ADD CONSTRAINT `fk_reserva_pista` FOREIGN KEY (`idPista`) REFERENCES `Pista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_reserva_user` FOREIGN KEY (`idUser`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

