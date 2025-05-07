# IntelliJ IDEA ile Araç Kiralama Uygulamasını Çalıştırma

Bu dökümanda IntelliJ IDEA IDE'si kullanarak Araç Kiralama Spring Boot uygulamasını sorunsuz bir şekilde çalıştırmak için adımlar anlatılmaktadır.

## 1. Proje Açma

1. IntelliJ IDEA'yı açın
2. "File -> Open" menüsünü kullanarak `c:\Users\EMRE\Desktop\araclirala2` dizinini seçin
3. "Open as Project" seçeneğini seçin
4. Maven projesinin yüklenmesini bekleyin (sağ altta Maven indeks ayarlanır)

## 2. JDK Ayarı

1. "File -> Project Structure" menüsünü açın
2. "Project" sekmesinde "Project SDK" ayarının Java 17 veya üzeri olduğundan emin olun
3. "Project language level" ayarını "17 - Sealed types, always-strict floating-point semantics" olarak ayarlayın
4. Apply'a tıklayın

## 3. Maven Yapılandırma Kontrolü

1. Sağ taraftaki "Maven" sekmesine tıklayın
2. "Reload All Maven Projects" düğmesine tıklayarak Maven'i yeniden yükleyin

## 4. Run Configuration Oluşturma

1. Menüden "Run -> Edit Configurations" seçin
2. "+" düğmesine tıklayın ve "Spring Boot" seçin
3. Name: "ArabaKiralamaApplication" olarak ayarlayın
4. Main class: "com.arabakiralama.ArabaKiralamaApplication"'ı seçin
5. JRE: Proje JDK'sını kullanın (Java 17 veya üzeri)
6. "Before launch" bölümünde "Build" görevinin olduğundan emin olun
7. "Apply" ve "OK" düğmelerine tıklayın

## 5. Uygulama Ayarlarını Kontrol Etme

1. `src/main/resources/application.properties` dosyasının doğru şekilde yapılandırıldığından emin olun
2. Şu anda H2 veritabanı yapılandırması aktif olmalıdır

## 6. Uygulamayı Çalıştırma

1. Üst menüden "Run -> Run 'ArabaKiralamaApplication'" seçin
2. Konsol penceresinde başlatma işlemini izleyin
3. Başarılı başlatmanın sonunda aşağıdaki URL'leri göreceksiniz:

- Ana Sayfa: http://localhost:8080
- H2 Konsolu: http://localhost:8080/h2-console
- API Testi: http://localhost:8080/test
- Araç Listesi: http://localhost:8080/api/cars

## 7. Başlatma Sorunları İçin Kontrol Listesi

Eğer başlatma sırasında hata alırsanız şunları kontrol edin:

1. **Port Çakışması**: 8080 portu başka bir uygulama tarafından kullanılıyor olabilir. `application.properties` dosyasından `server.port=8081` olarak değiştirin.

2. **Database Hataları**: H2 konsolu açılmıyorsa `application.properties` içinde H2 ayarlarını kontrol edin:
   ```
   spring.datasource.url=jdbc:h2:mem:arabakiralamadb
   spring.datasource.username=sa
   spring.datasource.password=
   spring.h2.console.enabled=true
   ```

3. **Lombok Sorunları**: Lombok sorunları için IntelliJ IDEA'da "Enable annotation processing" seçeneğini aktifleştirin:
   - "Settings -> Build, Execution, Deployment -> Compiler -> Annotation Processors"
   - "Enable annotation processing" seçeneğini işaretleyin

4. **JPA Entity Taraması**: Entity sınıfları bulunamıyorsa, `@EntityScan` ve `@EnableJpaRepositories` annotasyonlarının doğru şekilde yapılandırıldığından emin olun.

## 8. Veritabanı Konsolu (H2)

H2 veritabanı konsoluna erişmek için:
1. http://localhost:8080/h2-console adresine gidin
2. JDBC URL: `jdbc:h2:mem:arabakiralamadb`
3. Kullanıcı Adı: sa
4. Şifre: (boş bırakın)
5. "Connect" düğmesine tıklayın

Tüm bu adımları takip ettikten sonra uygulama sorunsuz çalışmalıdır. Hala sorun yaşıyorsanız, IntelliJ IDEA konsolundaki hata mesajlarını kontrol edin ve gerekirse Maven bağımlılıklarını temizleyip yeniden yükleyin (Clean & Install).
