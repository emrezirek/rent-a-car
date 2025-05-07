package com.arabakiralama.model;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDate;

@Entity
@Table(name = "cars")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String brand;
    
    @Column(nullable = false)
    private String model;
    
    @Column(nullable = false)
    private String color;
    
    @Column(nullable = false)
    private String fuelType;
    
    @Column(nullable = false)
    private String transmissionType;
    
    @Column(nullable = false)
    private Integer year;
    
    @Column(nullable = false)
    private Double dailyPrice;
    
    @Column(nullable = false)
    private String status = "Available";
    
    @Column(nullable = false)
    private String licensePlate;
    
    private String description;
    
    private String imageUrl; // Resim URL'si için yeni alan
    
    @Transient // Veritabanına kaydedilmeyecek geçici alan
    private List<Rental> activeRentals;

    public Car() {
    }

    public Car(Long id, String brand, String model, String color, String fuelType, String transmissionType,
               Integer year, Double dailyPrice, String status, String licensePlate, String description, String imageUrl) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.year = year;
        this.dailyPrice = dailyPrice;
        this.status = status;
        this.licensePlate = licensePlate;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    public List<Rental> getActiveRentals() {
        return activeRentals;
    }

    public void setActiveRentals(List<Rental> activeRentals) {
        this.activeRentals = activeRentals;
    }
    
    // Belirli bir tarih aralığında araç müsait mi kontrol et
    public boolean isAvailableBetween(LocalDate startDate, LocalDate endDate) {
        if (activeRentals == null || activeRentals.isEmpty()) {
            return true; // Aktif kiralama yoksa müsait
        }
        
        for (Rental rental : activeRentals) {
            // Sadece aktif ve tamamlanmış kiralamaları kontrol et (iptal edilenleri değil)
            if ("Cancelled".equals(rental.getStatus())) {
                continue;
            }
            
            // Tarih çakışması kontrolü
            // Yeni kiralama, mevcut bir kiralamanın tarih aralığıyla çakışıyorsa müsait değil
            if (!(endDate.isBefore(rental.getStartDate()) || startDate.isAfter(rental.getEndDate()))) {
                return false; // Tarih çakışması var
            }
        }
        
        return true; // Hiçbir çakışma yoksa müsait
    }
}
