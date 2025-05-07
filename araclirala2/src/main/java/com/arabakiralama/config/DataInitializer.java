package com.arabakiralama.config;

import com.arabakiralama.model.Car;
import com.arabakiralama.model.Customer;
import com.arabakiralama.repository.CarRepository;
import com.arabakiralama.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) {
        try {
            if (carRepository.count() == 0) {
                initCars();
                logger.info("Örnek araçlar başarıyla eklendi.");
            } else {
                logger.info("Veritabanında zaten araç kaydı var. Toplam: " + carRepository.count());
            }
            
            // Eğer hiç müşteri yoksa demo müşteri oluştur
            if (customerRepository.count() == 0) {
                initCustomers();
                logger.info("Demo müşteri hesabı oluşturuldu.");
            }
        } catch (Exception e) {
            logger.error("Veri başlatma hatası: " + e.getMessage(), e);
        }
    }

    private void initCars() {
        List<Car> sampleCars = List.of(
            createCar("Toyota", "Corolla", 2022, "Beyaz", "Otomatik", "Benzin", "34ABC123", 750.0, "Available", "Toyota Corolla 2022 model, otomatik vites, benzin", "/images/cars/toyotacorolla.png"),
            createCar("Renault", "Clio", 2021, "Kırmızı", "Manuel", "Dizel", "34DEF456", 500.0, "Available", "Renault Clio 2021 model, manuel vites, dizel", "/images/cars/clio.jpg"),
            createCar("Volkswagen", "Golf", 2023, "Siyah", "Otomatik", "Hibrit", "34GHI789", 900.0, "Available", "Volkswagen Golf 2023 model, otomatik vites, hibrit", "/images/cars/golf.png"),
            createCar("Ford", "Focus", 2020, "Mavi", "Manuel", "Benzin", "34JKL012", 600.0, "Available", "Ford Focus 2020 model, manuel vites, benzin", "/images/cars/ford.jpg"),
            createCar("Honda", "Civic", 2022, "Gri", "Otomatik", "Dizel", "34MNO345", 800.0, "Available", "Honda Civic 2022 model, otomatik vites, dizel", "/images/cars/honda.jpg"),
            createCar("Hyundai", "i20", 2021, "Beyaz", "Manuel", "Benzin", "34PQR678", 450.0, "Available", "Hyundai i20 2021 model, manuel vites, benzin", "/images/cars/i20.jpg"),
            createCar("Fiat", "Egea", 2022, "Gümüş", "Otomatik", "Dizel", "34STU901", 550.0, "Available", "Fiat Egea 2022 model, otomatik vites, dizel", "/images/cars/fiat.png"),
            createCar("Opel", "Astra", 2020, "Siyah", "Manuel", "Benzin", "34VWX234", 600.0, "Available", "Opel Astra 2020 model, manuel vites, benzin", "/images/cars/opel.png"),
            createCar("Mercedes", "A180", 2023, "Lacivert", "Otomatik", "Hibrit", "34YZA567", 1200.0, "Available", "Mercedes A180 2023 model, otomatik vites, hibrit", "/images/cars/mercedes.jpg"),
            createCar("BMW", "118i", 2022, "Beyaz", "Otomatik", "Benzin", "34BCD890", 1100.0, "Available", "BMW 118i 2022 model, otomatik vites, benzin", "/images/cars/bmw.jpg")
        );
        
        carRepository.saveAll(sampleCars);
    }
    
    private void initCustomers() {
        // Demo hesap ekleme
        Customer demoCustomer = new Customer();
        demoCustomer.setFirstName("Demo");
        demoCustomer.setLastName("Kullanıcı");
        demoCustomer.setEmail("demo@example.com");
        demoCustomer.setPhoneNumber("5551112233");
        demoCustomer.setAddress("İstanbul");
        demoCustomer.setPassword("demo123");
        
        customerRepository.save(demoCustomer);
    }

    private Car createCar(String brand, String model, int year, String color, 
                          String transmissionType, String fuelType, 
                          String licensePlate, double dailyPrice, String status, String description, String imageUrl) {
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(year);
        car.setColor(color);
        car.setTransmissionType(transmissionType);
        car.setFuelType(fuelType);
        car.setLicensePlate(licensePlate);
        car.setDailyPrice(dailyPrice);
        car.setStatus(status);
        car.setDescription(description);
        car.setImageUrl(imageUrl);
        return car;
    }
}
