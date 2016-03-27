-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema boostapp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema boostapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `boostapp` DEFAULT CHARACTER SET latin1 ;
USE `boostapp` ;

-- -----------------------------------------------------
-- Table `boostapp`.`companies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boostapp`.`companies` (
  `id` INT(11) NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  `createdOn` DATE NOT NULL,
  `slogan` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `boostapp`.`projects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boostapp`.`projects` (
  `id` INT(11) NOT NULL,
  `createdOn` DATE NOT NULL,
  `endOn` DATE NOT NULL,
  `durationInMins` INT(11) NOT NULL,
  `companyId` INT(11) NOT NULL,
  `fullAmountInCents` BIGINT(20) NOT NULL,
  `depositedInCents` BIGINT(20) NOT NULL,
  `isActive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `projects_fk0` (`companyId` ASC),
  CONSTRAINT `projects_fk0`
    FOREIGN KEY (`companyId`)
    REFERENCES `boostapp`.`companies` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `boostapp`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boostapp`.`users` (
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `fullName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `boostapp`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boostapp`.`comments` (
  `id` INT(11) NOT NULL,
  `projectId` INT(11) NOT NULL,
  `userId` VARCHAR(50) NOT NULL,
  `text` TEXT NOT NULL,
  `createdOn` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `comments_fk0` (`projectId` ASC),
  INDEX `comments_fk1` (`userId` ASC),
  CONSTRAINT `comments_fk0`
    FOREIGN KEY (`projectId`)
    REFERENCES `boostapp`.`projects` (`id`),
  CONSTRAINT `comments_fk1`
    FOREIGN KEY (`userId`)
    REFERENCES `boostapp`.`users` (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `boostapp`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boostapp`.`roles` (
  `id` INT(11) NOT NULL,
  `vaue` TINYTEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `boostapp`.`company_members`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boostapp`.`company_members` (
  `userId` VARCHAR(50) NOT NULL,
  `companyId` INT(11) NOT NULL,
  `roleId` INT(11) NOT NULL,
  INDEX `company_members_fk0` (`userId` ASC),
  INDEX `company_members_fk1` (`companyId` ASC),
  INDEX `company_members_fk2` (`roleId` ASC),
  CONSTRAINT `company_members_fk0`
    FOREIGN KEY (`userId`)
    REFERENCES `boostapp`.`users` (`username`),
  CONSTRAINT `company_members_fk1`
    FOREIGN KEY (`companyId`)
    REFERENCES `boostapp`.`companies` (`id`),
  CONSTRAINT `company_members_fk2`
    FOREIGN KEY (`roleId`)
    REFERENCES `boostapp`.`roles` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `boostapp`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boostapp`.`hibernate_sequence` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `boostapp`.`statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boostapp`.`statuses` (
  `id` INT(11) NOT NULL,
  `value` TINYTEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `boostapp`.`invites`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boostapp`.`invites` (
  `id` INT(11) NOT NULL,
  `userId` VARCHAR(50) NOT NULL,
  `companyId` INT(11) NOT NULL,
  `statusId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `invites_fk0` (`userId` ASC),
  INDEX `invites_fk1` (`companyId` ASC),
  INDEX `invites_fk2` (`statusId` ASC),
  CONSTRAINT `invites_fk0`
    FOREIGN KEY (`userId`)
    REFERENCES `boostapp`.`users` (`username`),
  CONSTRAINT `invites_fk1`
    FOREIGN KEY (`companyId`)
    REFERENCES `boostapp`.`companies` (`id`),
  CONSTRAINT `invites_fk2`
    FOREIGN KEY (`statusId`)
    REFERENCES `boostapp`.`statuses` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `boostapp`.`payment_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `boostapp`.`payment_history` (
  `id` INT(11) NOT NULL,
  `projectId` INT(11) NOT NULL,
  `companyId` INT(11) NOT NULL,
  `amountInCents` BIGINT(20) NOT NULL,
  `createdOn` DATE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `payment_history_fk0` (`projectId` ASC),
  INDEX `payment_history_fk1` (`companyId` ASC),
  CONSTRAINT `payment_history_fk0`
    FOREIGN KEY (`projectId`)
    REFERENCES `boostapp`.`projects` (`id`),
  CONSTRAINT `payment_history_fk1`
    FOREIGN KEY (`companyId`)
    REFERENCES `boostapp`.`companies` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
