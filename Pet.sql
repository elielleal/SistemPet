-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Pet
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Pet
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Pet` DEFAULT CHARACTER SET utf8 ;
USE `Pet` ;

-- -----------------------------------------------------
-- Table `Pet`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pet`.`clientes` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `cidade` VARCHAR(45) NOT NULL,
  `agendar_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pet`.`agendar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pet`.`agendar` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `nomeDono` VARCHAR(100) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `horarioAgend` VARCHAR(45) NOT NULL,
  `clientes_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_agendar_clientes1_idx` (`clientes_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_agendar_clientes1`
    FOREIGN KEY (`clientes_codigo`)
    REFERENCES `Pet`.`clientes` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pet`.`fornecedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pet`.`fornecedores` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Pet`.`produtos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Pet`.`produtos` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  `quantidade` INT NOT NULL,
  `preco` DECIMAL(5,2) NULL,
  `fornecedores_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_produtos_fornecedores_idx` (`fornecedores_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_produtos_fornecedores`
    FOREIGN KEY (`fornecedores_codigo`)
    REFERENCES `Pet`.`fornecedores` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
