-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema alternative-db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema alternative-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `alternative-db` DEFAULT CHARACTER SET utf8 ;
USE `alternative-db` ;

-- -----------------------------------------------------
-- Table `alternative-db`.`addresses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`addresses` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `house_number` INT NOT NULL,
  PRIMARY KEY (`address_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`login_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`login_data` (
  `login_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(72) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`login_id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`contact` (
  `contact_id` INT NOT NULL AUTO_INCREMENT,
  `login_id` INT NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `address_id` INT NULL,
  PRIMARY KEY (`contact_id`),
  INDEX `fk_persons_addresses_idx` (`address_id` ASC) VISIBLE,
  INDEX `fk_contact_login_data1_idx` (`login_id` ASC) VISIBLE,
  UNIQUE INDEX `login_id_UNIQUE` (`login_id` ASC) VISIBLE,
  CONSTRAINT `fk_persons_addresses`
    FOREIGN KEY (`address_id`)
    REFERENCES `alternative-db`.`addresses` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contact_login_data1`
    FOREIGN KEY (`login_id`)
    REFERENCES `alternative-db`.`login_data` (`login_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`specialities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`specialities` (
  `speciality_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(800) NOT NULL,
  PRIMARY KEY (`speciality_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`shift_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`shift_types` (
  `shift_type_id` INT NOT NULL AUTO_INCREMENT,
  `start_time` TIME NOT NULL,
  `end_time` TIME NOT NULL,
  PRIMARY KEY (`shift_type_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`doctors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`doctors` (
  `doctor_id` INT NOT NULL AUTO_INCREMENT,
  `contact_id` INT NOT NULL,
  `description` VARCHAR(800) NOT NULL,
  `speciality_id` INT NOT NULL,
  `qualification_level` INT NOT NULL,
  `shift_type_id` INT NOT NULL,
  PRIMARY KEY (`doctor_id`),
  INDEX `fk_doctors_specialities1_idx` (`speciality_id` ASC) VISIBLE,
  INDEX `fk_doctors_shift_types1_idx` (`shift_type_id` ASC) VISIBLE,
  INDEX `fk_doctors_persons_data1_idx` (`contact_id` ASC) VISIBLE,
  CONSTRAINT `fk_doctors_specialities1`
    FOREIGN KEY (`speciality_id`)
    REFERENCES `alternative-db`.`specialities` (`speciality_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctors_shift_types1`
    FOREIGN KEY (`shift_type_id`)
    REFERENCES `alternative-db`.`shift_types` (`shift_type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctors_persons_data1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `alternative-db`.`contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`treatments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`treatments` (
  `treatment_id` INT NOT NULL AUTO_INCREMENT,
  `speciality_id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `regular_price` INT NOT NULL,
  `required_qualification` INT NOT NULL,
  PRIMARY KEY (`treatment_id`),
  INDEX `fk_treatments_specialities1_idx` (`speciality_id` ASC) VISIBLE,
  CONSTRAINT `fk_treatments_specialities1`
    FOREIGN KEY (`speciality_id`)
    REFERENCES `alternative-db`.`specialities` (`speciality_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`loyalty_levels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`loyalty_levels` (
  `loyalty_level_id` INT NOT NULL AUTO_INCREMENT,
  `level_name` VARCHAR(45) NOT NULL,
  `discount_percentage` INT NOT NULL,
  `level_description` VARCHAR(800) NOT NULL,
  PRIMARY KEY (`loyalty_level_id`),
  UNIQUE INDEX `level_name_UNIQUE` (`level_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`clients` (
  `client_id` INT NOT NULL AUTO_INCREMENT,
  `contact_id` INT NOT NULL,
  `total_spendings` INT NOT NULL,
  `loyalty_level_id` INT NOT NULL,
  PRIMARY KEY (`client_id`),
  INDEX `fk_clients_contact1_idx` (`contact_id` ASC) VISIBLE,
  INDEX `fk_clients_loyalty_levels1_idx` (`loyalty_level_id` ASC) VISIBLE,
  CONSTRAINT `fk_clients_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `alternative-db`.`contact` (`contact_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_clients_loyalty_levels1`
    FOREIGN KEY (`loyalty_level_id`)
    REFERENCES `alternative-db`.`loyalty_levels` (`loyalty_level_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`visits`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`visits` (
  `visit_id` INT NOT NULL AUTO_INCREMENT,
  `client_id` INT NOT NULL,
  `doctor_id` INT NOT NULL,
  `treatment_id` INT NOT NULL,
  `total_price` INT NOT NULL,
  `datetime` DATETIME NOT NULL,
  `illness_description` VARCHAR(1000) NOT NULL,
  PRIMARY KEY (`visit_id`),
  INDEX `fk_visits_clients1_idx` (`client_id` ASC) VISIBLE,
  INDEX `fk_visits_treatments1_idx` (`treatment_id` ASC) VISIBLE,
  INDEX `fk_visits_doctors1_idx` (`doctor_id` ASC) VISIBLE,
  CONSTRAINT `fk_visits_clients1`
    FOREIGN KEY (`client_id`)
    REFERENCES `alternative-db`.`clients` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_visits_treatments1`
    FOREIGN KEY (`treatment_id`)
    REFERENCES `alternative-db`.`treatments` (`treatment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_visits_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `alternative-db`.`doctors` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`vacation_reasons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`vacation_reasons` (
  `reason_id` INT NOT NULL AUTO_INCREMENT,
  `short_name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(800) NOT NULL,
  PRIMARY KEY (`reason_id`),
  UNIQUE INDEX `short_name_UNIQUE` (`short_name` ASC) VISIBLE,
  UNIQUE INDEX `vacation_reasonscol_UNIQUE` (`description` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `alternative-db`.`vacations`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`vacations` (
  `vacation_id` INT NOT NULL AUTO_INCREMENT,
  `doctor_id` INT NOT NULL,
  `reason_id` INT NOT NULL,
  `starting_time` TIMESTAMP NOT NULL,
  `ending_time` TIMESTAMP NOT NULL,
  PRIMARY KEY (`vacation_id`),
  INDEX `fk_vacations_doctors1_idx` (`doctor_id` ASC) VISIBLE,
  INDEX `fk_vacations_vacation_reasons1_idx` (`reason_id` ASC) VISIBLE,
  CONSTRAINT `fk_vacations_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `alternative-db`.`doctors` (`doctor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_vacations_vacation_reasons1`
    FOREIGN KEY (`reason_id`)
    REFERENCES `alternative-db`.`vacation_reasons` (`reason_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `alternative-db` ;

-- -----------------------------------------------------
-- Placeholder table for view `alternative-db`.`doctors_public`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alternative-db`.`doctors_public` (`doctor_id` INT, `first_name` INT, `last_name` INT, `description` INT, `speciality_name` INT, `shift_type_id` INT);

-- -----------------------------------------------------
-- View `alternative-db`.`doctors_public`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `alternative-db`.`doctors_public`;
USE `alternative-db`;
CREATE  OR REPLACE VIEW `doctors_public` AS
SELECT d.doctor_id, c.first_name, c.last_name, d.description, s.name AS speciality_name, sh.shift_type_id as shift_type_id
FROM doctors AS d
JOIN contact AS c ON d.contact_id = c.contact_id
JOIN specialities AS s ON d.speciality_id = s.speciality_id
JOIN shift_types AS sh ON sh.shift_type_id = d.shift_type_id;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
