package com.arabakiralama.repository;

import com.arabakiralama.model.Car;
import com.arabakiralama.model.Customer;
import com.arabakiralama.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByCar(Car car);
    List<Rental> findByCustomer(Customer customer);
    List<Rental> findByStatus(String status);
    List<Rental> findByCustomerId(Long customerId);
    
    // Belirli bir araca ait ve belirli bir durumda olmayan kiralama kayıtlarını bul
    List<Rental> findByCarIdAndStatusNot(Long carId, String status);
    
    @Query("SELECT r FROM Rental r WHERE r.status = 'Active' AND r.car.id = :carId AND " +
           "((r.startDate BETWEEN :startDate AND :endDate) OR " +
           "(r.endDate BETWEEN :startDate AND :endDate) OR " +
           "(:startDate BETWEEN r.startDate AND r.endDate) OR " +
           "(:endDate BETWEEN r.startDate AND r.endDate))")
    List<Rental> findOverlappingRentals(Long carId, LocalDate startDate, LocalDate endDate);
}
