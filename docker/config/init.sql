-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema deploy
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema deploy
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `deploy` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `deploy` ;

-- -----------------------------------------------------
-- Table `deploy`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `deploy`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `login_id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(512) NOT NULL,
  `introduce` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_id_UNIQUE` (`login_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deploy`.`store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `deploy`.`store` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `created_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `deploy`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `deploy`.`post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `store_id` INT NOT NULL,
  `content` VARCHAR(255) NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_post_store1_idx` (`store_id` ASC) VISIBLE,
  CONSTRAINT `fk_post_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `deploy`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `deploy`.`store` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
