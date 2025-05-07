package com.arabakiralama.service;

import com.arabakiralama.model.Car;
import com.arabakiralama.repository.CarRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    
    private final CarRepository carRepository;
    
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    
    public Car getCarById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Araba bulunamadı: " + id));
    }
    
    public List<Car> getAvailableCars() {
        return carRepository.findAll().stream()
                .filter(car -> car.getStatus().equals("Available"))
                .collect(Collectors.toList());
    }
    
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }
    
    public Car updateCar(Long id, Car carDetails) {
        Car car = getCarById(id);
        
        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setColor(carDetails.getColor());
        car.setTransmissionType(carDetails.getTransmissionType());
        car.setFuelType(carDetails.getFuelType());
        car.setLicensePlate(carDetails.getLicensePlate());
        car.setDailyPrice(carDetails.getDailyPrice());
        car.setStatus(carDetails.getStatus());
        car.setDescription(carDetails.getDescription());
        
        return carRepository.save(car);
    }
    
    public void deleteCar(Long id) {
        Car car = getCarById(id);
        carRepository.delete(car);
    }
    
    public Car updateCarStatus(Long id, String status) {
        Car car = getCarById(id);
        car.setStatus(status);
        return carRepository.save(car);
    }
    
    public List<Car> searchCars(String brand, String model, Integer year, Double maxPrice) {
        if (brand != null && !brand.isEmpty()) {
            return carRepository.findByBrandContainingIgnoreCase(brand);
        }
        
        if (model != null && !model.isEmpty()) {
            // Model aramayı manuel olarak yapalım
            return carRepository.findAll().stream()
                    .filter(car -> car.getModel().toLowerCase().contains(model.toLowerCase()))
                    .collect(Collectors.toList());
        }
        
        if (year != null) {
            // Yıl aramayı manuel olarak yapalım
            return carRepository.findAll().stream()
                    .filter(car -> car.getYear() >= year)
                    .collect(Collectors.toList());
        }
        
        if (maxPrice != null) {
            // Fiyat aramayı manuel olarak yapalım
            return carRepository.findAll().stream()
                    .filter(car -> car.getDailyPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }
        
        return getAllCars();
    }
}
