-- MySQL Script generated by MySQL Workbench
-- 11/03/15 16:18:56
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema ServicioIng2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ServicioIng2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ServicioIng2` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ServicioIng2` ;

-- -----------------------------------------------------
-- Table `ServicioIng2`.`Profesor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicioIng2`.`Profesor` (
  `IdProfesor` INT NOT NULL AUTO_INCREMENT,
  `HuellaProfesor` LONGBLOB NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`IdProfesor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServicioIng2`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicioIng2`.`Usuario` (
  `IdPrestador` INT NOT NULL AUTO_INCREMENT,
  `HuellaUsuario` LONGBLOB NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  `Apellidos` VARCHAR(45) NOT NULL,
  `TipoUsuario` TINYINT(1) NOT NULL DEFAULT 0,
  `Usuario` VARCHAR(45) NULL,
  `Password` VARCHAR(45) NULL,
  PRIMARY KEY (`IdPrestador`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServicioIng2`.`Material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicioIng2`.`Material` (
  `IdMaterial` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(45) NULL,
  `Band` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`IdMaterial`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServicioIng2`.`Prestamo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicioIng2`.`Prestamo` (
  `IdPrestamo` INT NOT NULL AUTO_INCREMENT,
  `Profesor_IdProfesor` INT NOT NULL,
  `Material_IdMaterial` INT NOT NULL,
  `Usuario_IdPrestador` INT NOT NULL,
  `Fecha_Entrega` VARCHAR(10) NOT NULL,
  `Hora_Entrega` VARCHAR(10) NOT NULL,
  `Colegio` VARCHAR(45) NOT NULL,
  INDEX `IdMaterial_idx` (`Material_IdMaterial` ASC),
  INDEX `IdPrestador_idx` (`Usuario_IdPrestador` ASC),
  PRIMARY KEY (`IdPrestamo`),
  CONSTRAINT `IdProfesor`
    FOREIGN KEY (`Profesor_IdProfesor`)
    REFERENCES `ServicioIng2`.`Profesor` (`IdProfesor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `IdMaterial`
    FOREIGN KEY (`Material_IdMaterial`)
    REFERENCES `ServicioIng2`.`Material` (`IdMaterial`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `IdPrestador`
    FOREIGN KEY (`Usuario_IdPrestador`)
    REFERENCES `ServicioIng2`.`Usuario` (`IdPrestador`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServicioIng2`.`Recibo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicioIng2`.`Recibo` (
  `IdRecibo` INT NOT NULL AUTO_INCREMENT,
  `Profesor_IdProfesor` INT NOT NULL,
  `Material_IdMaterial` INT NOT NULL,
  `Usuario_IdUsuario` INT NOT NULL,
  `Fecha_Recibo` VARCHAR(45) NOT NULL,
  `Hora_Recibo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`IdRecibo`),
  INDEX `fk_Recibo_Usuario1_idx` (`Usuario_IdUsuario` ASC),
  INDEX `fk_Recibo_Material1_idx` (`Material_IdMaterial` ASC),
  INDEX `fk_Recibo_Profesor1_idx` (`Profesor_IdProfesor` ASC),
  CONSTRAINT `fk_Recibo_Usuario1`
    FOREIGN KEY (`Usuario_IdUsuario`)
    REFERENCES `ServicioIng2`.`Usuario` (`IdPrestador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recibo_Material1`
    FOREIGN KEY (`Material_IdMaterial`)
    REFERENCES `ServicioIng2`.`Material` (`IdMaterial`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Recibo_Profesor1`
    FOREIGN KEY (`Profesor_IdProfesor`)
    REFERENCES `ServicioIng2`.`Profesor` (`IdProfesor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ServicioIng2`.`PrestamoActual`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ServicioIng2`.`PrestamoActual` (
  `idPrestamoActual` INT NOT NULL AUTO_INCREMENT,
  `Profesor_IdProfesor` INT NOT NULL,
  `Material_IdMaterial` INT NOT NULL,
  `Usuario_IdPrestador` INT NOT NULL,
  `Fecha_Entrega` VARCHAR(45) NOT NULL,
  `Hora_Entrega` VARCHAR(45) NOT NULL,
  `Colegio` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPrestamoActual`),
  INDEX `fk_PrestamoActual_Profesor1_idx` (`Profesor_IdProfesor` ASC),
  INDEX `fk_PrestamoActual_Material1_idx` (`Material_IdMaterial` ASC),
  INDEX `fk_PrestamoActual_Usuario1_idx` (`Usuario_IdPrestador` ASC),
  CONSTRAINT `fk_PrestamoActual_Profesor1`
    FOREIGN KEY (`Profesor_IdProfesor`)
    REFERENCES `ServicioIng2`.`Profesor` (`IdProfesor`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_PrestamoActual_Material1`
    FOREIGN KEY (`Material_IdMaterial`)
    REFERENCES `ServicioIng2`.`Material` (`IdMaterial`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_PrestamoActual_Usuario1`
    FOREIGN KEY (`Usuario_IdPrestador`)
    REFERENCES `ServicioIng2`.`Usuario` (`IdPrestador`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
