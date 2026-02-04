package com.rftg.toad.controller;

import com.rftg.toad.model.Store;
import com.rftg.toad.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable Integer id) {
        return storeService.getStoreById(id);
    }

    @PostMapping
    public Store createStore(@RequestBody Store store) {
        return storeService.saveStore(store);
    }

    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Integer id, @RequestBody Store store) {
        store.setStoreId(id);
        return storeService.saveStore(store);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id);
    }
}
