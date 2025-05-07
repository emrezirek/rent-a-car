package com.arabakiralama.controller;

import com.arabakiralama.model.Car;
import com.arabakiralama.model.Customer;
import com.arabakiralama.model.Rental;
import com.arabakiralama.repository.CarRepository;
import com.arabakiralama.repository.CustomerRepository;
import com.arabakiralama.repository.RentalRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RentalRepository rentalRepository;


    @GetMapping("/")
    public String index(HttpSession session, Model model) {

        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            session.removeAttribute("customerId");
            return "redirect:/login";
        }

        Customer customer = optionalCustomer.get();

        List<Car> allCars = carRepository.findAll();


        for (Car car : allCars) {
            List<Rental> activeRentals = rentalRepository.findByCarIdAndStatusNot(car.getId(), "Cancelled");
            car.setActiveRentals(activeRentals);
        }

        model.addAttribute("customer", customer);
        model.addAttribute("cars", allCars);
        model.addAttribute("today", LocalDate.now());
        return "home";
    }


    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }


    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute Customer customer, 
                                  @RequestParam String confirmPassword,
                                  RedirectAttributes redirectAttributes) {
        

        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Bu e-posta adresi zaten kullanılıyor.");
            return "redirect:/register";
        }
        

        if (!customer.getPassword().equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Şifreler eşleşmiyor.");
            return "redirect:/register";
        }
        

        customer.setPhoneNumber(customer.getPhone()); // Phone değerini phoneNumber'a ata (form alanı phone olarak gelecek)
        customerRepository.save(customer);
        redirectAttributes.addFlashAttribute("success", "Kayıt başarıyla tamamlandı. Şimdi giriş yapabilirsiniz.");
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String email, 
                       @RequestParam String password,
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        
        if (optionalCustomer.isEmpty() || !optionalCustomer.get().getPassword().equals(password)) {
            redirectAttributes.addFlashAttribute("error", "E-posta veya şifre hatalı.");
            return "redirect:/login";
        }
        
        Customer customer = optionalCustomer.get();
        session.setAttribute("customerId", customer.getId());
        
        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("customerId");
        return "redirect:/login";
    }

    // Kiralama sayfası
    @GetMapping("/rent")
    public String showRentForm(@RequestParam(required = false) Long carId,
                              HttpSession session,
                              Model model) {
        
        // Giriş yapılmış mı kontrol et
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            session.removeAttribute("customerId");
            return "redirect:/login";
        }
        
        Customer customer = optionalCustomer.get();
        model.addAttribute("customer", customer);
        
        // Tüm araçları göster
        List<Car> allCars = carRepository.findAll();
        
        // Her araç için aktif kiralamaları kontrol et
        for (Car car : allCars) {
            List<Rental> activeRentals = rentalRepository.findByCarIdAndStatusNot(car.getId(), "Cancelled");
            car.setActiveRentals(activeRentals);
        }
        
        model.addAttribute("cars", allCars);
        model.addAttribute("today", LocalDate.now());
        
        // Eğer carId parametresi varsa, o aracı seçili olarak göster
        if (carId != null) {
            Optional<Car> optionalCar = carRepository.findById(carId);
            if (optionalCar.isPresent()) {
                model.addAttribute("selectedCar", optionalCar.get());
            }
        }
        
        return "rent";
    }

    // Kiralama işlemini gerçekleştir
    @PostMapping("/rent")
    public String rentCar(@RequestParam Long customerId,
                         @RequestParam Long carId,
                         @RequestParam String startDate,
                         @RequestParam String endDate,
                         HttpSession session,
                         RedirectAttributes redirectAttributes) {
        
        // Giriş yapılmış mı kontrol et
        Long sessionCustomerId = (Long) session.getAttribute("customerId");
        if (sessionCustomerId == null || !sessionCustomerId.equals(customerId)) {
            return "redirect:/login";
        }
        
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Optional<Car> optionalCar = carRepository.findById(carId);
        
        if (optionalCustomer.isEmpty() || optionalCar.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Müşteri veya araç bulunamadı.");
            return "redirect:/rent";
        }
        
        Customer customer = optionalCustomer.get();
        Car car = optionalCar.get();
        
        // Tarih kontrolü
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        
        if (start.isAfter(end)) {
            redirectAttributes.addFlashAttribute("error", "Başlangıç tarihi bitiş tarihinden sonra olamaz.");
            return "redirect:/rent?carId=" + carId;
        }
        
        // Araç seçilen tarih aralığında müsait mi kontrol et
        List<Rental> activeRentals = rentalRepository.findByCarIdAndStatusNot(car.getId(), "Cancelled");
        car.setActiveRentals(activeRentals);
        
        if (!car.isAvailableBetween(start, end)) {
            redirectAttributes.addFlashAttribute("error", "Seçtiğiniz araç bu tarih aralığında başkası tarafından kiralanmış.");
            return "redirect:/rent?carId=" + carId;
        }
        
        // Kiralama oluştur
        Rental rental = new Rental();
        rental.setCar(car);
        rental.setCustomer(customer);
        rental.setStartDate(start);
        rental.setEndDate(end);
        rental.setStatus("Active");
        
        // Toplam fiyatı hesapla
        long days = java.time.temporal.ChronoUnit.DAYS.between(start, end);
        if (days < 1) days = 1; // En az 1 gün
        double totalPrice = days * car.getDailyPrice();
        rental.setTotalPrice(totalPrice);
        
        // Aracın durumunu değiştirmiyoruz, sadece kiralama kaydı oluşturuyoruz
        rentalRepository.save(rental);
        
        redirectAttributes.addFlashAttribute("success", "Araç kiralama işlemi başarıyla tamamlandı.");
        return "redirect:/";
    }

    @GetMapping("/rent-history")
    public String rentHistory(HttpSession session, Model model) {
        // Giriş yapılmış mı kontrol et
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            session.removeAttribute("customerId");
            return "redirect:/login";
        }
        
        Customer customer = optionalCustomer.get();
        List<Rental> rentals = rentalRepository.findByCustomerId(customerId);
        
        model.addAttribute("customer", customer);
        model.addAttribute("rentals", rentals);
        
        return "rent-history";
    }
    
    @PostMapping("/cancel")
    public String cancel(HttpSession session, @RequestParam Long rentalId, RedirectAttributes redirectAttributes) {
        // Giriş yapılmış mı kontrol et
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        
        try {
            Optional<Rental> optionalRental = rentalRepository.findById(rentalId);
            if (optionalRental.isEmpty()) {
                redirectAttributes.addFlashAttribute("error", "Kiralama bulunamadı.");
                return "redirect:/";
            }
            
            Rental rental = optionalRental.get();
            if (rental.getCustomer().getId().equals(customerId)) {
                rental.setStatus("Cancelled");
                rentalRepository.save(rental);
                
                // Artık aracın durumunu değiştirmiyoruz, sadece kiralama durumunu güncelliyoruz
                
                redirectAttributes.addFlashAttribute("success", "Kiralama başarıyla iptal edildi.");
            } else {
                redirectAttributes.addFlashAttribute("error", "Bu kiralama işlemi sizin hesabınıza ait değil.");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Kiralama iptal edilirken bir hata oluştu: " + e.getMessage());
        }
        
        return "redirect:/";
    }
}
