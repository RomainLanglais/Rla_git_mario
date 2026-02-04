package com.rftg.toad.service;

import com.rftg.toad.exception.ResourceNotFoundException;
import com.rftg.toad.model.Inventory;
import com.rftg.toad.model.Rental;
import com.rftg.toad.repository.InventoryRepository;
import com.rftg.toad.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;

    public RentalService(RentalRepository rentalRepository, InventoryRepository inventoryRepository) {
        this.rentalRepository = rentalRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Integer id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental);
    }

    public void deleteRental(Integer id) {
        rentalRepository.deleteById(id);
    }

    public List<Rental> getRentalsByCustomerId(Integer customerId) {
        return rentalRepository.findByCustomerId(customerId);
    }

    public List<Rental> getActiveRentalsByCustomerId(Integer customerId) {
        return rentalRepository.findByCustomerIdAndReturnDateIsNull(customerId);
    }

    public Rental rentFilm(Integer filmId, Integer customerId, Integer staffId) {
        List<Inventory> available = inventoryRepository.findAvailableByFilmId(filmId);
        if (available.isEmpty()) {
            throw new ResourceNotFoundException("Aucune copie disponible pour le film ID: " + filmId);
        }
        Inventory inventory = available.get(0);

        Rental rental = new Rental();
        rental.setRentalDate(LocalDateTime.now());
        rental.setInventoryId(inventory.getInventoryId());
        rental.setCustomerId(customerId);
        rental.setStaffId(staffId);
        rental.setStatusId(3); // en cours
        rental.setLastUpdate(LocalDateTime.now());

        return rentalRepository.save(rental);
    }

    public Rental returnFilm(Integer rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Location non trouvée, ID: " + rentalId));

        if (rental.getReturnDate() != null) {
            throw new IllegalStateException("Cette location a déjà été retournée");
        }

        rental.setReturnDate(LocalDateTime.now());
        rental.setStatusId(1); // terminé
        rental.setLastUpdate(LocalDateTime.now());

        return rentalRepository.save(rental);
    }
}
