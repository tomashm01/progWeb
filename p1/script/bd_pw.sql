-- phpMyAdmin SQL Dump
-- version 2.7.0-pl2
-- http://www.phpmyadmin.net
-- 
-- Servidor: oraclepr.uco.es
-- Tiempo de generación: 02-12-2022 a las 17:57:47
-- Versión del servidor: 5.1.73
-- Versión de PHP: 5.3.3
-- 
-- Base de datos: `i02pinma`
-- 

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `Bono`
-- 

DROP TABLE IF EXISTS `Bono`;
CREATE TABLE IF NOT EXISTS `Bono` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fechaExpiracion` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `BonoReserva`
-- 

DROP TABLE IF EXISTS `BonoReserva`;
CREATE TABLE IF NOT EXISTS `BonoReserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idBono` int(11) NOT NULL,
  `idReserva` int(11) NOT NULL,
  `posicion` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idBono` (`idBono`,`idReserva`),
  KEY `fk_bono_reserva` (`idReserva`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `Kart`
-- 

DROP TABLE IF EXISTS `Kart`;
CREATE TABLE IF NOT EXISTS `Kart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `esAdulto` tinyint(1) NOT NULL,
  `estado` varchar(20) NOT NULL,
  `idPista` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idPista` (`idPista`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `KartReserva`
-- 

DROP TABLE IF EXISTS `KartReserva`;
CREATE TABLE IF NOT EXISTS `KartReserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idKart` int(11) NOT NULL,
  `idReserva` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idKart` (`idKart`),
  KEY `fk_kart_reserva` (`idReserva`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;


-- 
-- Estructura de tabla para la tabla `Pista`
-- 

DROP TABLE IF EXISTS `Pista`;
CREATE TABLE IF NOT EXISTS `Pista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(64) NOT NULL,
  `dificultad` varchar(16) NOT NULL,
  `maxKarts` int(11) NOT NULL,
  `disponible` int(8) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `Reserva`
-- 

DROP TABLE IF EXISTS `Reserva`;
CREATE TABLE IF NOT EXISTS `Reserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` varchar(32) NOT NULL,
  `idPista` int(11) NOT NULL,
  `precio` float NOT NULL,
  `descuento` float NOT NULL,
  `fecha` datetime NOT NULL,
  `duracion` int(11) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `numAdultos` int(11) DEFAULT NULL,
  `numMenores` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reserva_user` (`idUser`),
  KEY `fk_reserva_pista` (`idPista`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

-- 
-- Estructura de tabla para la tabla `User`
-- 

DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `email` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `rol` varchar(32) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `nombreCompleto` varchar(64) NOT NULL,
  `fechaInscripcion` date NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


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
  ADD CONSTRAINT `fk_reserva_user` FOREIGN KEY (`idUser`) REFERENCES `User` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;
