package com.rftg.toad.controller;

import com.rftg.toad.model.Inventory;
import com.rftg.toad.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Integer id) {
        return inventoryService.getInventoryById(id);
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Integer id, @RequestBody Inventory inventory) {
        inventory.setInventoryId(id);
        return inventoryService.saveInventory(inventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Integer id) {
        inventoryService.deleteInventory(id);
    }

    @GetMapping("/available/film/{filmId}")
    public List<Inventory> getAvailableInventoryByFilm(@PathVariable Integer filmId) {
        return inventoryService.findAvailableByFilmId(filmId);
    }
}
