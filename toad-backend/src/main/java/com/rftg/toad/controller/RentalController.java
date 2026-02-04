package com.rftg.toad.controller;

import com.rftg.toad.dto.RentRequest;
import com.rftg.toad.model.Rental;
import com.rftg.toad.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable Integer id) {
        return rentalService.getRentalById(id);
    }

    @PostMapping
    public Rental createRental(@RequestBody Rental rental) {
        return rentalService.saveRental(rental);
    }

    @PutMapping("/{id}")
    public Rental updateRental(@PathVariable Integer id, @RequestBody Rental rental) {
        rental.setRentalId(id);
        return rentalService.saveRental(rental);
    }

    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable Integer id) {
        rentalService.deleteRental(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Rental> getRentalsByCustomer(@PathVariable Integer customerId) {
        return rentalService.getRentalsByCustomerId(customerId);
    }

    @GetMapping("/customer/{customerId}/active")
    public List<Rental> getActiveRentalsByCustomer(@PathVariable Integer customerId) {
        return rentalService.getActiveRentalsByCustomerId(customerId);
    }

    @PostMapping("/rent")
    public ResponseEntity<Rental> rentFilm(@RequestBody RentRequest rentRequest) {
        Rental rental = rentalService.rentFilm(
                rentRequest.getFilmId(),
                rentRequest.getCustomerId(),
                rentRequest.getStaffId()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Rental> returnFilm(@PathVariable Integer id) {
        Rental rental = rentalService.returnFilm(id);
        return ResponseEntity.ok(rental);
    }
}
