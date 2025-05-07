package com.arabakiralama;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EntityScan("com.arabakiralama.model")
@EnableJpaRepositories("com.arabakiralama.repository")
public class ArabaKiralamaApplication {

    public static void main(String[] args) {
        try {
            System.out.println("Uygulama başlatılıyor...");
            ConfigurableApplicationContext context = SpringApplication.run(ArabaKiralamaApplication.class, args);
            Environment env = context.getEnvironment();
            String port = env.getProperty("server.port", "8090");
            
            System.out.println("Uygulama başlatıldı!");
            System.out.println("Tarayıcınızda http://localhost:" + port + " adresini açabilirsiniz");
            System.out.println("H2 veritabanı konsolu: http://localhost:" + port + "/h2-console");
            System.out.println("API testi: http://localhost:" + port + "/test");
            System.out.println("Araçları listele: http://localhost:" + port + "/api/cars");
        } catch (Exception e) {
            System.err.println("Uygulama başlatılırken hata oluştu:");
            e.printStackTrace();
        }
    }
}
