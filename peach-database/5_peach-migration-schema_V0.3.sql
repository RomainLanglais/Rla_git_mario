-- migration to peach
-- 2026/02/03 - V0.3 : support logique de location

SET NAMES utf8mb4;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE peach;

-- Index pour accélérer les requêtes de disponibilité (return_date IS NULL)
CREATE INDEX idx_rental_return_date ON rental (return_date);

-- Vue des films ayant au moins une copie disponible à la location
CREATE OR REPLACE VIEW available_films AS
SELECT DISTINCT f.film_id, f.title, f.description, f.release_year,
       f.rental_duration, f.rental_rate, f.length,
       f.replacement_cost, f.rating, f.special_features
FROM film f
INNER JOIN inventory i ON f.film_id = i.film_id
WHERE i.inventory_id NOT IN (
    SELECT r.inventory_id FROM rental r WHERE r.return_date IS NULL
);

-- NOTE : Les mots de passe dans la table staff doivent être hashés en BCrypt
-- pour que l'authentification JWT fonctionne.
-- Exemple pour mettre à jour avec un hash BCrypt généré en Java :
--   UPDATE staff SET password = '<hash_bcrypt>' WHERE username = '<username>';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
