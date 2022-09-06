-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema medcenter_database
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema medcenter_database
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `medcenter_database` DEFAULT CHARACTER SET utf8mb3 ;
USE `medcenter_database` ;

-- -----------------------------------------------------
-- Table `medcenter_database`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medcenter_database`.`addresses` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `house_number` INT NOT NULL,
  PRIMARY KEY (`address_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `medcenter_database`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medcenter_database`.`clients` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(35) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(70) NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`client_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_clients_addresses1_idx` (`address_id` ASC) VISIBLE,
  CONSTRAINT `fk_clients_addresses1`
    FOREIGN KEY (`address_id`)
    REFERENCES `medcenter_database`.`addresses` (`address_id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `medcenter_database`.`internal_logins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medcenter_database`.`internal_logins` (
  `internal_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`internal_id`),
  UNIQUE INDEX `username_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 185
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `medcenter_database`.`doctors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medcenter_database`.`doctors` (
  `doctor_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`doctor_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_doctors_addresses1_idx` (`address_id` ASC) VISIBLE,
  CONSTRAINT `fk_doctors_addresses1`
    FOREIGN KEY (`address_id`)
    REFERENCES `medcenter_database`.`addresses` (`address_id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_doctors_internal_logins1`
    FOREIGN KEY (`email`)
    REFERENCES `medcenter_database`.`internal_logins` (`email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 148
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `medcenter_database`.`treatments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medcenter_database`.`treatments` (
  `treatment_id` INT NOT NULL AUTO_INCREMENT,
  `treatment_name` VARCHAR(45) NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`treatment_id`),
  UNIQUE INDEX `service_name_UNIQUE` (`treatment_name` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `medcenter_database`.`visits`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medcenter_database`.`visits` (
  `visit_id` INT NOT NULL AUTO_INCREMENT,
  `treatment_id` INT NOT NULL,
  `client_id` INT NOT NULL,
  `doctor_id` INT NOT NULL,
  `datetime` DATETIME NOT NULL,
  PRIMARY KEY (`visit_id`),
  INDEX `fk_visits_services1_idx` (`treatment_id` ASC) VISIBLE,
  INDEX `fk_visits_doctors1_idx` (`doctor_id` ASC) VISIBLE,
  INDEX `fk_visits_clients1_idx` (`client_id` ASC) VISIBLE,
  CONSTRAINT `fk_visits_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `medcenter_database`.`clients` (`client_id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_visits_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `medcenter_database`.`doctors` (`doctor_id`)
    ON UPDATE CASCADE,
  CONSTRAINT `fk_visits_services1`
    FOREIGN KEY (`treatment_id`)
    REFERENCES `medcenter_database`.`treatments` (`treatment_id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `medcenter_database`.`treatments_has_doctors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `medcenter_database`.`treatments_has_doctors` (
  `treatment_id` INT NOT NULL,
  `doctor_id` INT NOT NULL,
  PRIMARY KEY (`treatment_id`, `doctor_id`),
  INDEX `fk_treatments_has_doctors_doctors1_idx` (`doctor_id` ASC) VISIBLE,
  INDEX `fk_treatments_has_doctors_treatments1_idx` (`treatment_id` ASC) VISIBLE,
  CONSTRAINT `fk_treatments_has_doctors_treatments1`
    FOREIGN KEY (`treatment_id`)
    REFERENCES `medcenter_database`.`treatments` (`treatment_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_treatments_has_doctors_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `medcenter_database`.`doctors` (`doctor_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
