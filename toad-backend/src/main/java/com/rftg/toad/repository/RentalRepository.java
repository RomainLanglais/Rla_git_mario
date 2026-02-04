package com.rftg.toad.repository;

import com.rftg.toad.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> findByCustomerId(Integer customerId);

    List<Rental> findByCustomerIdAndReturnDateIsNull(Integer customerId);
}
