package com.rftg.toad.service;

import com.rftg.toad.model.Store;
import com.rftg.toad.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Integer id) {
        return storeRepository.findById(id).orElse(null);
    }

    public Store saveStore(Store store) {
        return storeRepository.save(store);
    }

    public void deleteStore(Integer id) {
        storeRepository.deleteById(id);
    }
}
