package com.rftg.toad.repository;

import com.rftg.toad.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("SELECT i FROM Inventory i WHERE i.filmId = :filmId AND i.inventoryId NOT IN " +
            "(SELECT r.inventoryId FROM Rental r WHERE r.returnDate IS NULL)")
    List<Inventory> findAvailableByFilmId(@Param("filmId") Integer filmId);
}
