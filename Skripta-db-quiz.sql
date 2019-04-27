-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema quiz_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema quiz_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `quiz_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `quiz_db` ;

-- -----------------------------------------------------
-- Table `quiz_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz_db`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  `password` VARCHAR(250) CHARACTER SET 'utf8' NOT NULL,
  `role` ENUM('Student', 'Teacher', 'Admin') NULL DEFAULT NULL,
  `email` VARCHAR(100) CHARACTER SET 'utf8' NOT NULL,
  `br_index` VARCHAR(9) CHARACTER SET 'utf8' NULL,
  `ime_prezime` VARCHAR(50) CHARACTER SET 'utf8' NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz_db`.`test`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz_db`.`test` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz_db`.`testing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz_db`.`testing` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `studentId` INT(11) NOT NULL,
  `testId` INT(11) NOT NULL,
  `score` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_testing_user1_idx` (`studentId` ASC) VISIBLE,
  INDEX `fk_testing_test1_idx` (`testId` ASC) VISIBLE,
  CONSTRAINT `fk_testing_user1`
    FOREIGN KEY (`studentId`)
    REFERENCES `quiz_db`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_testing_test1`
    FOREIGN KEY (`testId`)
    REFERENCES `quiz_db`.`test` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz_db`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz_db`.`subject` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) CHARACTER SET 'utf8' NOT NULL,
  `teacher_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_subject_user_idx` (`teacher_id` ASC) VISIBLE,
  CONSTRAINT `fk_subject_user`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `quiz_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz_db`.`topic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz_db`.`topic` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) CHARACTER SET 'utf8' NOT NULL,
  `subject_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_topic_subject1_idx` (`subject_id` ASC) VISIBLE,
  CONSTRAINT `fk_topic_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `quiz_db`.`subject` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz_db`.`question`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz_db`.`question` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(500) CHARACTER SET 'utf8' NOT NULL,
  `level` ENUM('1', '2', '3', '4', '5') NULL DEFAULT NULL,
  `topic_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_question_topic1_idx` (`topic_id` ASC) VISIBLE,
  CONSTRAINT `fk_question_topic1`
    FOREIGN KEY (`topic_id`)
    REFERENCES `quiz_db`.`topic` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz_db`.`options`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz_db`.`options` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(200) CHARACTER SET 'utf8' NOT NULL,
  `is_true` TINYINT(1) NOT NULL,
  `question_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_options_question1_idx` (`question_id` ASC) VISIBLE,
  CONSTRAINT `fk_options_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `quiz_db`.`question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz_db`.`answer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz_db`.`answer` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `testingId` INT(11) NOT NULL,
  `optionId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_answer_testing1_idx` (`testingId` ASC) VISIBLE,
  INDEX `fk_answer_options1_idx` (`optionId` ASC) VISIBLE,
  CONSTRAINT `fk_answer_testing1`
    FOREIGN KEY (`testingId`)
    REFERENCES `quiz_db`.`testing` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_answer_options1`
    FOREIGN KEY (`optionId`)
    REFERENCES `quiz_db`.`options` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `quiz_db`.`combination`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `quiz_db`.`combination` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `question_id` INT(11) NOT NULL,
  `testId` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_combination_question1_idx` (`question_id` ASC) VISIBLE,
  INDEX `fk_combination_test1_idx` (`testId` ASC) VISIBLE,
  CONSTRAINT `fk_combination_question1`
    FOREIGN KEY (`question_id`)
    REFERENCES `quiz_db`.`question` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_combination_test1`
    FOREIGN KEY (`testId`)
    REFERENCES `quiz_db`.`test` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
