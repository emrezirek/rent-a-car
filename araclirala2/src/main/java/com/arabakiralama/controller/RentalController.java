package com.arabakiralama.controller;

import com.arabakiralama.model.Rental;
import com.arabakiralama.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    
    private final RentalService rentalService;
    
    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }
    
    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Rental>> getRentalsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(rentalService.getRentalsByCustomer(customerId));
    }
    
    @GetMapping("/car/{carId}")
    public ResponseEntity<List<Rental>> getRentalsByCar(@PathVariable Long carId) {
        return ResponseEntity.ok(rentalService.getRentalsByCar(carId));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Rental>> getRentalsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(rentalService.getRentalsByStatus(status));
    }
    
    @PostMapping
    public ResponseEntity<Rental> createRental(
            @Valid @RequestBody Rental rental,
            @RequestParam Long carId,
            @RequestParam Long customerId) {
        return new ResponseEntity<>(rentalService.createRental(rental, carId, customerId), HttpStatus.CREATED);
    }
    
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Rental> completeRental(
            @PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate actualReturnDate) {
        return ResponseEntity.ok(rentalService.completeRental(id, actualReturnDate));
    }
    
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<Rental> cancelRental(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.cancelRental(id));
    }
    
    @GetMapping("/check-availability")
    public ResponseEntity<Map<String, Boolean>> checkCarAvailability(
            @RequestParam Long carId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        boolean isAvailable = rentalService.isCarAvailable(carId, startDate, endDate);
        Map<String, Boolean> response = Map.of("available", isAvailable);
        return ResponseEntity.ok(response);
    }
}
