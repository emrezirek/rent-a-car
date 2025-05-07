package com.arabakiralama.service;

import com.arabakiralama.model.Car;
import com.arabakiralama.model.Customer;
import com.arabakiralama.model.Rental;
import com.arabakiralama.repository.RentalRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalService {
    
    private final RentalRepository rentalRepository;
    private final CarService carService;
    private final CustomerService customerService;
    
    @Autowired
    public RentalService(RentalRepository rentalRepository, CarService carService, CustomerService customerService) {
        this.rentalRepository = rentalRepository;
        this.carService = carService;
        this.customerService = customerService;
    }
    
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
    
    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Kiralama bulunamadı: " + id));
    }
    
    public List<Rental> getRentalsByCustomer(Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return rentalRepository.findByCustomer(customer);
    }
    
    public List<Rental> getRentalsByCar(Long carId) {
        Car car = carService.getCarById(carId);
        return rentalRepository.findByCar(car);
    }
    
    public List<Rental> getRentalsByStatus(String status) {
        return rentalRepository.findByStatus(status);
    }
    
    @Transactional
    public Rental createRental(Rental rental, Long carId, Long customerId) {
        Car car = carService.getCarById(carId);
        Customer customer = customerService.getCustomerById(customerId);
        
        // Araç müsait mi kontrol et
        if (!"Available".equals(car.getStatus())) {
            throw new IllegalStateException("Araç şu anda kiralamaya müsait değil: " + car.getId());
        }
        
        // Geçerli tarihler mi kontrol et
        if (rental.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Başlangıç tarihi bugünden önce olamaz");
        }
        
        if (rental.getEndDate().isBefore(rental.getStartDate())) {
            throw new IllegalStateException("Bitiş tarihi başlangıç tarihinden önce olamaz");
        }
        
        // Çakışan kiralama var mı kontrol et
        List<Rental> overlappingRentals = rentalRepository.findOverlappingRentals(
                carId, rental.getStartDate(), rental.getEndDate());
        
        if (!overlappingRentals.isEmpty()) {
            throw new IllegalStateException("Seçilen tarih aralığında araç zaten kiralanmış");
        }
        
        // Toplam fiyatı hesapla
        long days = ChronoUnit.DAYS.between(rental.getStartDate(), rental.getEndDate()) + 1;
        double totalPrice = days * car.getDailyPrice();
        
        // Rental nesnesini ayarla
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setTotalPrice(totalPrice);
        rental.setStatus("Active");
        
        // Araç durumunu güncelle
        car.setStatus("Rented");
        carService.saveCar(car);
        
        return rentalRepository.save(rental);
    }
    
    @Transactional
    public Rental completeRental(Long id, LocalDate actualReturnDate) {
        Rental rental = getRentalById(id);
        
        if (!"Active".equals(rental.getStatus())) {
            throw new IllegalStateException("Sadece aktif kiralamalar tamamlanabilir");
        }
        
        // Gerçek dönüş tarihini ayarla
        rental.setActualReturnDate(actualReturnDate);
        
        // Ek ücret hesapla
        if (actualReturnDate.isAfter(rental.getEndDate())) {
            long extraDays = ChronoUnit.DAYS.between(rental.getEndDate(), actualReturnDate);
            double extraCharge = extraDays * rental.getCar().getDailyPrice() * 1.2; // %20 ceza
            rental.setExtraCharge(extraCharge);
        } else {
            rental.setExtraCharge(0.0);
        }
        
        // Durumu güncelle
        rental.setStatus("Completed");
        
        // Arabayı müsait yap
        Car car = rental.getCar();
        car.setStatus("Available");
        carService.saveCar(car);
        
        return rentalRepository.save(rental);
    }
    
    @Transactional
    public Rental cancelRental(Long id) {
        Rental rental = getRentalById(id);
        
        if (!"Active".equals(rental.getStatus())) {
            throw new IllegalStateException("Sadece aktif kiralamalar iptal edilebilir");
        }
        
        // Durumları güncelle
        rental.setStatus("Cancelled");
        
        Car car = rental.getCar();
        car.setStatus("Available");
        carService.saveCar(car);
        
        return rentalRepository.save(rental);
    }
    
    public boolean isCarAvailable(Long carId, LocalDate startDate, LocalDate endDate) {
        Car car = carService.getCarById(carId);
        
        // Araç müsait mi kontrol et
        if (!"Available".equals(car.getStatus())) {
            return false;
        }
        
        // Çakışan kiralama var mı kontrol et
        List<Rental> overlappingRentals = rentalRepository.findOverlappingRentals(
                carId, startDate, endDate);
        
        return overlappingRentals.isEmpty();
    }
}
