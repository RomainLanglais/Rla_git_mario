-- peach Sample Database Data

SET NAMES utf8mb4;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';
SET @old_autocommit=@@autocommit;

USE peach;

-- MCH: Supression des `` inutiles et passage en minuscule du nom de la table
INSERT INTO status (status_id, status_label) VALUES
  (1, 'terminé'),
  (2, 'dans le panier'),
  (3, 'en cours')
ON DUPLICATE KEY UPDATE status_label = VALUES(status_label);

-- MCH: Ajout clause WHERE
UPDATE rental
SET status_id = 1
WHERE status_id IS NULL;
-- MCH : correction syntaxe (ajout structure de la table dans INSERT + null et CURRENT_TIMESTAMP() dans SELECT)
INSERT INTO film_language (film_language_id, film_id, language_id, last_update) 
SELECT null, film_id, 1, CURRENT_TIMESTAMP() FROM film;
INSERT INTO film_language (film_language_id, film_id, language_id, last_update) 
SELECT null, film_id, 2, CURRENT_TIMESTAMP() FROM film;
INSERT INTO film_language (film_language_id, film_id, language_id, last_update) 
SELECT null, film_id, 3, CURRENT_TIMESTAMP() FROM film;
INSERT INTO film_language (film_language_id, film_id, language_id, last_update) 
SELECT null, film_id, 4, CURRENT_TIMESTAMP() FROM film;
INSERT INTO film_language (film_language_id, film_id, language_id, last_update) 
SELECT null, film_id, 5, CURRENT_TIMESTAMP() FROM film;
INSERT INTO film_language (film_language_id, film_id, language_id, last_update) 
SELECT null, film_id, 6, CURRENT_TIMESTAMP() FROM film;

INSERT INTO director (director_id, last_name,first_name , last_update) VALUES
(1, 'Spielberg', 'Steven', CURRENT_TIMESTAMP()),
(2, 'Nolan','Christopher' , CURRENT_TIMESTAMP()),
(3, 'Scorsese','Martin' , CURRENT_TIMESTAMP()),
(4, 'Tarantino', 'Quentin', CURRENT_TIMESTAMP()),
(5, 'Cameron', 'James', CURRENT_TIMESTAMP()),
(6, 'Scott','Ridley' , CURRENT_TIMESTAMP()),
(7, 'Jackson', 'Peter', CURRENT_TIMESTAMP()),
(8, 'Hitchcock', 'Alfred', CURRENT_TIMESTAMP()),
(9, 'Coppola', 'Sofia', CURRENT_TIMESTAMP()),
(10, 'Del Toro', 'Guillermo', CURRENT_TIMESTAMP());

-- MCH - Ajout film_director_id last_update pour respecter la structure de la table
INSERT INTO film_director (film_director_id, film_id, director_id, last_update)
SELECT null,film_id, 1, CURRENT_TIMESTAMP() FROM film
WHERE film_id between 1 and 100;
INSERT INTO film_director (film_director_id, film_id, director_id, last_update)
SELECT null,film_id, 2, CURRENT_TIMESTAMP() FROM film
WHERE film_id between 101 and 200; 
INSERT INTO film_director (film_director_id, film_id, director_id, last_update)
SELECT null,film_id, 3, CURRENT_TIMESTAMP() FROM film
WHERE film_id between 201 and 300; 
INSERT INTO film_director (film_director_id, film_id, director_id, last_update)
SELECT null,film_id, 4, CURRENT_TIMESTAMP() FROM film
WHERE film_id between 301 and 400; 
INSERT INTO film_director (film_director_id, film_id, director_id, last_update)
SELECT null,film_id, 5, CURRENT_TIMESTAMP() FROM film
WHERE film_id between 401 and 500; 
INSERT INTO film_director (film_director_id, film_id, director_id, last_update)
SELECT null,film_id, 6, CURRENT_TIMESTAMP() FROM film
WHERE film_id between 501 and 600; 
INSERT INTO film_director (film_director_id, film_id, director_id, last_update)
SELECT null,film_id, 7, CURRENT_TIMESTAMP() FROM film
WHERE film_id between 601 and 700; 
INSERT INTO film_director (film_director_id, film_id, director_id, last_update)
SELECT null,film_id, 8, CURRENT_TIMESTAMP() FROM film
WHERE film_id between 701 and 800; 
INSERT INTO film_director (film_director_id, film_id, director_id, last_update)
SELECT null,film_id, 9, CURRENT_TIMESTAMP() FROM film
WHERE film_id between 801 and 900; 
INSERT INTO film_director (film_director_id, film_id, director_id, last_update)
SELECT null,film_id, 10, CURRENT_TIMESTAMP() FROM film
WHERE film_id between 901 and 1000;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET autocommit=@old_autocommit;
