-- migration to peach from Sakila Sample Database Schema
-- 2025/09/12 - V0.1 : students WORK
-- 2025/09/23 - V0.2 : teacher fixes.

SET NAMES utf8mb4;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE peach;

-- MCH : instruction inutile et mal positionnée du reste (avant de créer la table...) : commentée
-- ALTER TABLE film_language ADD language_id INTEGER;
ALTER TABLE film DROP FOREIGN KEY fk_film_language;
ALTER TABLE film DROP COLUMN language_id;

DROP TABLE IF EXISTS payment;

-- MCH : Transformation des majuscules en minuscules
DROP TABLE IF EXISTS status;
CREATE TABLE status (
  status_id    INT NOT NULL AUTO_INCREMENT,
  status_label VARCHAR(100),
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (status_id)
) ENGINE=InnoDB;

-- MCH : retrait des ` inutiles et ne respectant le modèle du script existant
-- MCH : Transformation des majuscules en minuscules
ALTER TABLE rental
  ADD COLUMN status_id INT NULL;

-- MCH : correction de la syntaxe (MODIFY status_id INT NULL non valide)
ALTER TABLE rental
  ADD CONSTRAINT fk_rental_status
    FOREIGN KEY (status_id) REFERENCES status(status_id) ON DELETE RESTRICT;

DROP TABLE IF EXISTS director;
CREATE TABLE director (
  director_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (director_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- MCH : Ajout film_director_id pour respecter l'existant + Ajout contrainte UNIQUE sur (film_id, director_id)
DROP TABLE IF EXISTS film_director;
CREATE TABLE film_director(
    film_director_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    film_id SMALLINT UNSIGNED NOT NULL,
    director_id SMALLINT UNSIGNED NOT NULL,
    last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY(film_director_id), 
    CONSTRAINT fk_film_director_film_id 
	     FOREIGN KEY (film_id) 
		 REFERENCES film (film_id) 
		 ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_film_director_director_id 
	     FOREIGN KEY (director_id) 
	     REFERENCES director (director_id) 
		 ON DELETE RESTRICT ON UPDATE CASCADE,
	UNIQUE KEY uk_film_director (film_id, director_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- INDEX sur les FOREIGN KEY retirés car inutiles
DROP TABLE IF EXISTS film_language;
CREATE TABLE film_language (
    film_language_id SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
    film_id SMALLINT UNSIGNED NOT NULL,
    language_id TINYINT UNSIGNED NOT NULL,
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (film_language_id),
    CONSTRAINT fk_film_language_film 
        FOREIGN KEY (film_id) REFERENCES film (film_id) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_film_language_language 
        FOREIGN KEY (language_id) REFERENCES language (language_id) 
        ON DELETE RESTRICT ON UPDATE CASCADE,
    UNIQUE KEY uk_film_language (film_id, language_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;