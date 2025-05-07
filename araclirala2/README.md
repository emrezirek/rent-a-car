# 🚗 Araç Kiralama Uygulaması

Bu proje, Spring Boot ile geliştirilmiş kapsamlı ve kullanıcı dostu bir araç kiralama web uygulamasıdır. Modern bir arayüz ve güçlü bir backend ile müşterilere eksiksiz bir kiralama deneyimi sunar.

## 💻 Uygulama Özellikleri

- **Kullanıcı Yönetimi**: Kayıt, giriş ve oturum yönetimi
- **Araç Katalogu**: Marka, model, yıl, renk ve fiyat bilgileriyle detaylı araç listesi
- **Araç Arama**: Çeşitli kriterlere göre filtreleme ve arama
- **Kiralama Sistemi**: Tarih seçimi, müsaitlik kontrolü ve fiyat hesaplama
- **Kiralama Yönetimi**: Aktif kiralama görüntüleme, iptal etme ve geçmiş kiralama kayıtları
- **Duyarlı Tasarım**: Mobil cihazlarda da sorunsuz çalışan responsive arayüz
- **Güvenli Veri Yönetimi**: Veritabanı entegrasyonu ve veri doğrulama

## 💻 Teknolojiler ve Kütüphaneler

### Backend Teknolojileri

- **Java 17**: 
  - Güçlü nesne yönelimli programlama dili
  - Lambda ifadeleri, Stream API ve modüler sistem gibi modern özellikler
  - Yüksek performans ve güvenlik

- **Spring Boot 3.2.2**: 
  - Hızlı ve kolay Spring tabanlı uygulama geliştirme
  - Otomatik konfigürasyon ve gömülü sunucu desteği
  - Mikroservis mimarisine uygun yapı

- **Spring Web**: 
  - RESTful API geliştirme için kapsamlı çerçeve
  - HTTP istek/yanıt yönetimi
  - Yol (path) değişkenleri ve sorgu parametreleri desteği

- **Spring Data JPA**: 
  - Veritabanı işlemleri için güçlü ORM desteği
  - Repository deseni ile kolay veritabanı erişimi
  - JPQL ve native SQL sorgu desteği

- **Spring Validation**: 
  - Form ve veri doğrulama için kapsamlı araçlar
  - Bean Validation (JSR 380) entegrasyonu
  - Özelleştirilebilir doğrulama mesajları

- **Spring DevTools**: 
  - Otomatik yeniden yükleme (hot reload) özelliği
  - Geliştirme sürecini hızlandıran araçlar
  - Performans optimizasyonları

### Ön Yüz Teknolojileri

- **Thymeleaf**: 
  - Sunucu taraflı Java template motoru
  - HTML5 uyumlu şablonlar
  - Spring Boot ile tam entegrasyon
  - Fragment ve layout desteği ile yeniden kullanılabilir bileşenler

- **Bootstrap**: 
  - Duyarlı (responsive) tasarım için CSS çerçevesi
  - Hazır UI bileşenleri ve stil
  - Mobil öncelikli yaklaşım

- **JavaScript**: 
  - Dinamik kullanıcı arayüzü için istemci taraflı programlama
  - Form doğrulama ve AJAX istekleri
  - Tarih seçici ve dinamik içerik yükleme

### Veritabanı Teknolojileri

- **PostgreSQL**: 
  - Güçlü, açık kaynaklı ilişkisel veritabanı
  - JSON veri tipi ve gelişmiş sorgulama özellikleri
  - Docker ortamında konteynerize edilmiş

- **H2 Database**: 
  - Hafif, gömülü, bellek içi veritabanı
  - Geliştirme ve test ortamı için ideal
  - Web konsol arayüzü ile kolay yönetim

### DevOps ve Araçlar

- **Docker & Docker Compose**: 
  - Uygulama konteynerleştirme
  - Çoklu servis yönetimi ve orkestrasyon
  - Ortam bağımsız çalışma

- **Maven**: 
  - Java projeleri için bağımlılık yönetimi
  - Derleme, test ve paketleme otomasyonu
  - POM (Project Object Model) tabanlı proje yapılandırması

- **Lombok**: 
  - Tekrarlayan Java kodlarını azaltan kütüphane
  - Getter, setter, constructor gibi standart metodları otomatik oluşturma
  - Daha temiz ve okunabilir kod

## 📍 Kurulum Seçenekleri

### 1. Docker ile Kurulum (Önerilen Yöntem)

#### Gereksinimler:
- Docker Engine (20.10.0 veya üzeri)
- Docker Compose (2.0.0 veya üzeri)

#### Adımlar:

1. Projeyi bilgisayarınıza indirin veya klonlayın:
   ```bash
   git clone https://github.com/kullanici/arabakiralama.git
   cd arabakiralama
   ```

2. Docker Compose ile uygulamayı başlatın:
   ```bash
   docker-compose up -d
   ```
   > `-d` parametresi uygulamayı arka planda çalıştırır.

3. Uygulamanın durumunu kontrol edin:
   ```bash
   docker-compose ps
   ```

4. Uygulama loglarını görüntüleyin:
   ```bash
   docker-compose logs -f
   ```

5. Uygulamayı durdurmak için:
   ```bash
   docker-compose down
   ```

#### Avantajlar:
- Tek komutla tüm ortamı (uygulama ve veritabanı) kurabilirsiniz
- Ortam bağımsız çalışma garantisi
- PostgreSQL veritabanı otomatik olarak yapılandırılır
- Veritabanı verileri kalıcı olarak Docker volume'da saklanır

### 2. Local Maven ile Kurulum

#### Gereksinimler:
- Java 17 JDK
- Maven 3.8.0 veya üzeri
- (Opsiyonel) PostgreSQL veritabanı

#### Adımlar:

1. Projeyi bilgisayarınıza indirin veya klonlayın:
   ```bash
   git clone https://github.com/kullanici/arabakiralama.git
   cd arabakiralama
   ```

2. Maven ile bağımlılıkları yükleyin ve uygulamayı derleyin:
   ```bash
   mvn clean install
   ```

3. Uygulamayı çalıştırın:
   ```bash
   mvn spring-boot:run
   ```
   
   Alternatif olarak, Windows işletim sisteminde `run.bat` dosyasını kullanabilirsiniz:
   ```bash
   run.bat
   ```

> **Not**: Local ortamda çalıştırırken, varsayılan olarak H2 in-memory veritabanı kullanılacaktır. PostgreSQL kullanmak isterseniz, `application.properties` dosyasındaki ilgili ayarları düzenleyin.

#### Avantajlar:
- Daha hızlı geliştirme döngüsü
- Spring DevTools ile otomatik yeniden yükleme (hot reload)
- Daha az sistem kaynağı kullanımı

### 3. IDE ile Kurulum ve Geliştirme

#### Gereksinimler:
- Java 17 JDK
- IntelliJ IDEA, Eclipse veya VS Code
- Maven eklentisi

#### IntelliJ IDEA ile Adımlar:

1. IntelliJ IDEA'yı açın
2. `File > Open` menüsünden proje klasörünü seçin
3. Maven projesini otomatik olarak tanıyacaktır
4. Maven Tool Window'dan bağımlılıkları yükleyin
5. `src/main/java/com/arabakiralama/ArabaKiralamaApplication.java` dosyasını bulun
6. Sağ tıklayıp "Run ArabaKiralamaApplication" seçeneğini tıklayın

#### Eclipse ile Adımlar:

1. Eclipse'i açın
2. `File > Import > Maven > Existing Maven Projects` yolunu izleyin
3. Proje klasörünü seçin ve `pom.xml` dosyasını içe aktarın
4. Proje üzerinde sağ tıklayıp `Run As > Spring Boot App` seçeneğini tıklayın

#### VS Code ile Adımlar:

1. VS Code'u açın
2. Java ve Spring Boot eklentilerini yükleyin
3. Proje klasörünü açın
4. `JAVA PROJECTS` panelinden projeyi görüntüleyin
5. `ArabaKiralamaApplication.java` dosyasını açın ve "Run" düğmesine tıklayın

#### Avantajlar:
- Kod tamamlama, hata ayıklama ve refaktöring gibi IDE özellikleri
- Entegre hata ayıklama (debugging) imkanı
- Proje yapısını daha kolay görüntüleme ve yönetme

## 💾 Veritabanı Ayarları ve Yönetimi

### PostgreSQL (Docker Ortamı)

Docker Compose ile çalıştırıldığında, uygulama PostgreSQL veritabanını kullanır. Veritabanı ayarları `docker-compose.yml` dosyasında tanımlanmıştır:

```yaml
postgres:
  image: postgres:14-alpine
  ports:
    - "5450:5432"
  environment:
    - POSTGRES_USER=postgres
    - POSTGRES_PASSWORD=postgres
    - POSTGRES_DB=arabakiralama
  volumes:
    - postgres-data:/var/lib/postgresql/data
```

#### PostgreSQL Bağlantı Bilgileri:

- **JDBC URL**: `jdbc:postgresql://postgres:5432/arabakiralama`
- **Kullanıcı Adı**: `postgres`
- **Şifre**: `postgres`
- **Port**: `5450` (host makine üzerinde)
- **Veritabanı Adı**: `arabakiralama`

#### PostgreSQL'e Dışarıdan Erişim:

PostgreSQL veritabanına pgAdmin veya başka bir veritabanı yönetim aracı ile erişmek için:

- **Host**: `localhost`
- **Port**: `5450`
- **Kullanıcı Adı**: `postgres`
- **Şifre**: `postgres`
- **Veritabanı Adı**: `arabakiralama`

### H2 Database (Local Geliştirme Ortamı)

Local ortamda Maven veya IDE ile çalıştırıldığında, uygulama varsayılan olarak H2 in-memory veritabanını kullanır. Bu, herhangi bir harici veritabanı kurulumu gerektirmeden hızlı geliştirme yapmanıza olanak tanır.

#### H2 Konsol Erişimi:

Uygulama çalışırken, H2 web konsoluna şu URL üzerinden erişebilirsiniz:

- **URL**: `http://localhost:8090/h2-console`

#### H2 Bağlantı Bilgileri:

- **JDBC URL**: `jdbc:h2:mem:arabakiralamadb`
- **Kullanıcı Adı**: `sa`
- **Şifre**: (boş bırakın)
- **Driver Class**: `org.h2.Driver`

### Veritabanı Şeması ve Tablolar

Uygulama aşağıdaki ana tabloları içerir:

1. **cars**: Araç bilgilerini saklar
   - `id`: Birincil anahtar
   - `brand`: Marka
   - `model`: Model
   - `color`: Renk
   - `fuel_type`: Yakıt tipi
   - `transmission_type`: Vites tipi
   - `year`: Üretim yılı
   - `daily_price`: Günlük kiralama ücreti
   - `status`: Araç durumu (Available, Rented, Maintenance)
   - `license_plate`: Plaka
   - `description`: Açıklama
   - `image_url`: Araç görsel URL'si

2. **customers**: Müşteri bilgilerini saklar
   - `id`: Birincil anahtar
   - `first_name`: Ad
   - `last_name`: Soyad
   - `email`: E-posta (benzersiz)
   - `password`: Şifre
   - `phone_number`: Telefon numarası
   - `address`: Adres
   - `driving_license_number`: Ehliyet numarası

3. **rentals**: Kiralama işlemlerini saklar
   - `id`: Birincil anahtar
   - `car_id`: Kiralanan araç (yabancı anahtar)
   - `customer_id`: Kiralayan müşteri (yabancı anahtar)
   - `start_date`: Kiralama başlangıç tarihi
   - `end_date`: Kiralama bitiş tarihi
   - `total_price`: Toplam kiralama ücreti
   - `status`: Kiralama durumu (Active, Completed, Cancelled)
   - `created_at`: Kayıt oluşturma tarihi
   - `updated_at`: Son güncelleme tarihi

### Veritabanı Migrasyon ve Başlangıç Verileri

Uygulama ilk çalıştırıldığında, Hibernate `ddl-auto=update` ayarı sayesinde gerekli tabloları otomatik olarak oluşturur. Ayrıca, `DataInitializer` sınıfı ile örnek veriler (araçlar, müşteriler) otomatik olarak yüklenir.

## 💯 Uygulama Özellikleri ve İşlevler

### Kullanıcı Yönetimi

- **Kayıt Sistemi**: Kullanıcılar ad, soyad, e-posta, şifre, telefon numarası, adres ve ehliyet numarası bilgileriyle sisteme kayıt olabilirler.
- **Giriş Sistemi**: Kayıtlı kullanıcılar e-posta ve şifreleriyle sisteme giriş yapabilirler.
- **Oturum Yönetimi**: Kullanıcı oturumları HTTP Session ile yönetilir, güvenli erişim sağlanır.
- **Profil Yönetimi**: Kullanıcılar kişisel bilgilerini görüntüleyebilir ve güncelleyebilirler.

### Araç Katalogu ve Yönetimi

- **Araç Listeleme**: Tüm araçlar marka, model, yıl, renk, yakıt tipi, vites tipi, günlük fiyat ve durum bilgileriyle listelenir.
- **Araç Detay Görüntüleme**: Her aracın detaylı bilgileri ve görselleri görüntülenebilir.
- **Araç Arama ve Filtreleme**: Marka, model, yıl ve maksimum fiyata göre araç arama imkanı.
- **Araç Durumu Takibi**: Araçların müsaitlik durumu (Available, Rented, Maintenance) takip edilebilir.
- **Araç Ekleme/Düzenleme/Silme**: Yetkili kullanıcılar için araç yönetim fonksiyonları (API üzerinden).

### Kiralama Sistemi

- **Tarih Seçimi**: Kullanıcılar kiralama başlangıç ve bitiş tarihlerini seçebilirler.
- **Müsaitlik Kontrolü**: Seçilen tarih aralığında aracın müsait olup olmadığı otomatik kontrol edilir.
- **Fiyat Hesaplama**: Kiralama süresi ve aracın günlük fiyatına göre toplam fiyat otomatik hesaplanır.
- **Kiralama Onayı**: Kullanıcılar kiralama bilgilerini görüntüleyip onaylayabilirler.
- **Kiralama İptali**: Aktif kiralamalar kullanıcılar tarafından iptal edilebilir.

### Kiralama Yönetimi

- **Aktif Kiralama Görüntüleme**: Kullanıcılar aktif kiralamalarını görüntüleyebilirler.
- **Kiralama Geçmişi**: Kullanıcılar geçmiş kiralama kayıtlarını görüntüleyebilirler.
- **Kiralama Detayları**: Her kiralamanın araç bilgileri, tarihler, fiyat ve durum bilgileri görüntülenebilir.
- **Kiralama Durumu Güncelleme**: Kiralamaların durumu (Active, Completed, Cancelled) güncellenebilir (API üzerinden).

### Güvenlik Özellikleri

- **Oturum Doğrulama**: Tüm işlemler için kullanıcı oturumu doğrulanır.
- **Veri Doğrulama**: Tüm form verileri sunucu tarafında doğrulanır.
- **Hata Yönetimi**: Kullanıcı dostu hata mesajları ve yönlendirmeler.

### Kullanıcı Arayüzü Özellikleri

- **Duyarlı Tasarım**: Mobil cihazlarda da sorunsuz çalışan responsive arayüz.
- **Sezgisel Gezinme**: Kolay kullanılabilir menü ve gezinme yapısı.
- **Bildirimler**: İşlem sonucu bildirimleri (başarılı, hata, uyarı).
- **Dinamik İçerik Yükleme**: AJAX ile sayfa yenileme olmadan içerik güncelleme.

## Proje Yapısı

```
src/main/java/com/arabakiralama/
├── ArabaKiralamaApplication.java  # Ana uygulama sınıfı
├── config/                      # Konfigürasyon sınıfları
│   └── DataInitializer.java     # Başlangıç verileri yükleyen sınıf
├── controller/                 # Web ve API kontrolcüleri
│   ├── CarController.java      # Araç API'si
│   ├── CustomerController.java # Müşteri API'si
│   ├── HomeController.java     # Web sayfaları yönetimi
│   └── RentalController.java   # Kiralama API'si
├── exception/                  # Özel istisna sınıfları
├── model/                      # Veri modelleri
│   ├── Car.java               # Araç modeli
│   ├── CarStatus.java         # Araç durumu enum
│   ├── Customer.java          # Müşteri modeli
│   ├── FuelType.java          # Yakıt tipi enum
│   ├── Rental.java            # Kiralama modeli
│   ├── RentalStatus.java      # Kiralama durumu enum
│   └── TransmissionType.java  # Vites tipi enum
├── repository/                # Veritabanı erişim katmanı
│   ├── CarRepository.java     # Araç veritabanı işlemleri
│   ├── CustomerRepository.java # Müşteri veritabanı işlemleri
│   └── RentalRepository.java   # Kiralama veritabanı işlemleri
└── service/                   # İş mantığı katmanı
    ├── CarService.java        # Araç işlemleri
    ├── CustomerService.java    # Müşteri işlemleri
    └── RentalService.java      # Kiralama işlemleri

src/main/resources/
├── application.properties    # Uygulama ayarları
├── static/                    # Statik dosyalar (CSS, JS, resimler)
│   ├── css/                  # Stil dosyaları
│   ├── images/               # Görsel dosyaları
│   └── images/cars/          # Araç görselleri
└── templates/                # Thymeleaf şablonları
    ├── home.html             # Ana sayfa şablonu
    ├── login.html            # Giriş sayfası şablonu
    ├── register.html         # Kayıt sayfası şablonu
    ├── rent-history.html     # Kiralama geçmişi şablonu
    └── rent.html             # Kiralama sayfası şablonu
```

## Temel Bileşenlerin Açıklamaları

### Model Sınıfları

- **Car**: Araç bilgilerini içeren JPA entity sınıfı. Marka, model, renk, yakıt tipi, vites tipi, yıl, günlük fiyat, durum, plaka, açıklama ve resim URL'si gibi özellikleri içerir. Ayrıca belirli bir tarih aralığında aracın müsait olup olmadığını kontrol eden `isAvailableBetween()` metodu bulunur.

- **Customer**: Müşteri bilgilerini içeren JPA entity sınıfı. Ad, soyad, e-posta, şifre, telefon numarası, adres ve ehliyet numarası gibi özellikleri içerir.

- **Rental**: Kiralama işlemlerini içeren JPA entity sınıfı. Hangi müşterinin hangi aracı kiralandığını, başlangıç ve bitiş tarihlerini, toplam fiyatı ve kiralama durumunu (aktif, tamamlanmış, iptal edilmiş) içerir.

### Repository Sınıfları

- **CarRepository**: Araçlarla ilgili veritabanı işlemlerini gerçekleştiren Spring Data JPA repository'si. Marka, model, yıl ve fiyat gibi kriterlere göre araç arama metodları içerir.

- **CustomerRepository**: Müşterilerle ilgili veritabanı işlemlerini gerçekleştiren repository. E-posta ve ehliyet numarasına göre müşteri bulma metodları içerir.

- **RentalRepository**: Kiralama işlemleriyle ilgili veritabanı işlemlerini gerçekleştiren repository. Müşteri ID'sine, araç ID'sine ve kiralama durumuna göre kiralama kayıtlarını bulma metodları içerir.

### Servis Sınıfları

- **CarService**: Araçlarla ilgili iş mantığını içeren servis sınıfı. Tüm araçları listeleme, ID'ye göre araç getirme, müsait araçları listeleme, araç ekleme, güncelleme, silme ve araç durumunu güncelleme gibi metodlar içerir. Ayrıca çeşitli kriterlere göre araç arama fonksiyonu bulunur.

- **CustomerService**: Müşterilerle ilgili iş mantığını içeren servis sınıfı. Müşteri ekleme, güncelleme, silme ve listeleme işlemlerini gerçekleştirir.

- **RentalService**: Kiralama işlemleriyle ilgili iş mantığını içeren servis sınıfı. Kiralama oluşturma, iptal etme, tamamlama ve listeleme işlemlerini gerçekleştirir. Ayrıca belirli bir tarih aralığında aracın müsait olup olmadığını kontrol eden metodlar içerir.

### Kontrolcü Sınıfları

- **CarController**: Araçlarla ilgili REST API endpoint'lerini içeren kontrolcü sınıfı. Tüm araçları listeleme, ID'ye göre araç getirme, müsait araçları listeleme, araç ekleme, güncelleme, silme ve araç durumunu güncelleme gibi endpoint'leri içerir.

- **CustomerController**: Müşterilerle ilgili REST API endpoint'lerini içeren kontrolcü sınıfı. Müşteri ekleme, güncelleme, silme ve listeleme işlemlerini gerçekleştiren endpoint'leri içerir.

- **RentalController**: Kiralama işlemleriyle ilgili REST API endpoint'lerini içeren kontrolcü sınıfı. Kiralama oluşturma, iptal etme, tamamlama ve listeleme işlemlerini gerçekleştiren endpoint'leri içerir.

- **HomeController**: Web sayfalarını yöneten kontrolcü sınıfı. Ana sayfa, giriş, kayıt, kiralama ve kiralama geçmişi sayfalarını yönlendirir. Ayrıca giriş, kayıt, kiralama ve kiralama iptal işlemlerini gerçekleştirir.

## 🔗 API Endpoints ve Kullanımı

Uygulama, frontend arayüzü yanında kapsamlı bir REST API sunar. Bu API'ler, mobil uygulamalar veya harici sistemlerle entegrasyon için kullanılabilir.

### Araç API'leri

| Metod | Endpoint | Açıklama | Örnek Kullanım |
|-------|----------|------------|---------------|
| GET | `/api/cars` | Tüm araçları listeler | `GET /api/cars` |
| GET | `/api/cars/{id}` | ID'ye göre araç getirir | `GET /api/cars/1` |
| GET | `/api/cars/available` | Müsait araçları listeler | `GET /api/cars/available` |
| POST | `/api/cars` | Yeni araç ekler | `POST /api/cars` + JSON body |
| PUT | `/api/cars/{id}` | Araç bilgilerini günceller | `PUT /api/cars/1` + JSON body |
| DELETE | `/api/cars/{id}` | Araç siler | `DELETE /api/cars/1` |
| PATCH | `/api/cars/{id}/status` | Araç durumunu günceller | `PATCH /api/cars/1/status` + status string |
| GET | `/api/cars/search` | Araçları kriterlere göre arar | `GET /api/cars/search?brand=Toyota&maxPrice=500` |

#### Örnek JSON Yapısı (Araç Ekleme/Güncelleme):
```json
{
  "brand": "Toyota",
  "model": "Corolla",
  "color": "Beyaz",
  "fuelType": "Benzin",
  "transmissionType": "Otomatik",
  "year": 2022,
  "dailyPrice": 450.0,
  "status": "Available",
  "licensePlate": "34ABC123",
  "description": "Ekonomik sedan araç",
  "imageUrl": "images/cars/corolla.jpg"
}
```

### Müşteri API'leri

| Metod | Endpoint | Açıklama | Örnek Kullanım |
|-------|----------|------------|---------------|
| GET | `/api/customers` | Tüm müşterileri listeler | `GET /api/customers` |
| GET | `/api/customers/{id}` | ID'ye göre müşteri getirir | `GET /api/customers/1` |
| POST | `/api/customers` | Yeni müşteri ekler | `POST /api/customers` + JSON body |
| PUT | `/api/customers/{id}` | Müşteri bilgilerini günceller | `PUT /api/customers/1` + JSON body |
| DELETE | `/api/customers/{id}` | Müşteri siler | `DELETE /api/customers/1` |
| GET | `/api/customers/email/{email}` | E-posta adresine göre müşteri getirir | `GET /api/customers/email/user@example.com` |
| GET | `/api/customers/license/{number}` | Ehliyet numarasına göre müşteri getirir | `GET /api/customers/license/12345678` |

#### Örnek JSON Yapısı (Müşteri Ekleme/Güncelleme):
```json
{
  "firstName": "Ahmet",
  "lastName": "Yılmaz",
  "email": "ahmet@example.com",
  "password": "guvenli_sifre",
  "phoneNumber": "5551234567",
  "address": "Ankara, Türkiye",
  "drivingLicenseNumber": "12345678"
}
```

### Kiralama API'leri

| Metod | Endpoint | Açıklama | Örnek Kullanım |
|-------|----------|------------|---------------|
| GET | `/api/rentals` | Tüm kiralamaları listeler | `GET /api/rentals` |
| GET | `/api/rentals/{id}` | ID'ye göre kiralama getirir | `GET /api/rentals/1` |
| GET | `/api/rentals/customer/{customerId}` | Müşteriye göre kiralamaları listeler | `GET /api/rentals/customer/1` |
| GET | `/api/rentals/car/{carId}` | Araca göre kiralamaları listeler | `GET /api/rentals/car/1` |
| GET | `/api/rentals/status/{status}` | Duruma göre kiralamaları listeler | `GET /api/rentals/status/Active` |
| POST | `/api/rentals` | Yeni kiralama ekler | `POST /api/rentals` + JSON body |
| PATCH | `/api/rentals/{id}/complete` | Kiralamayı tamamlar | `PATCH /api/rentals/1/complete` |
| PATCH | `/api/rentals/{id}/cancel` | Kiralamayı iptal eder | `PATCH /api/rentals/1/cancel` |
| GET | `/api/rentals/check-availability` | Belirli tarih aralığında araç uygunluğunu kontrol eder | `GET /api/rentals/check-availability?carId=1&startDate=2025-05-10&endDate=2025-05-15` |

#### Örnek JSON Yapısı (Kiralama Ekleme):
```json
{
  "carId": 1,
  "customerId": 1,
  "startDate": "2025-05-10",
  "endDate": "2025-05-15",
  "status": "Active"
}
```

### Web Sayfaları

| Endpoint | Açıklama |
|----------|------------|
| `/` | Ana sayfa - Araç listesi |
| `/login` | Giriş sayfası |
| `/register` | Kayıt sayfası |
| `/rent` | Araç kiralama sayfası |
| `/rent-history` | Kiralama geçmişi sayfası |
| `/logout` | Çıkış işlemi |
| `/test` | API test sayfası |
| `/h2-console` | H2 veritabanı yönetim konsolu |

## 🔧 Yapılan Değişiklikler ve İyileştirmeler

Uygulama üzerinde yapılan son değişiklikler ve iyileştirmeler aşağıda detaylı olarak açıklanmıştır:

### 1. Port Ayarı Düzenlemesi

**Yapılan Değişiklik**: Uygulama 8090 portunda çalışacak şekilde ayarlandı.

**Değişiklik Yapılan Dosya**: `src/main/resources/application.properties`

**Yapılan Güncelleme**:
```properties
# Server Port
server.port=8090
```

**Nedeni**: Uygulamanın tutarlı bir port üzerinden çalışmasını sağlamak ve port çakışmalarını önlemek için sabit bir port ataması yapıldı. Docker Compose yapılandırması da bu porta göre ayarlandı.

### 2. Java Sürümü Güncellemesi

**Yapılan Değişiklik**: Projenin Java sürümü 24'ten 17'ye düşürüldü.

**Değişiklik Yapılan Dosya**: `pom.xml`

**Yapılan Güncelleme**:
```xml
<properties>
    <java.version>17</java.version>
</properties>
```

**Nedeni**: Java 24 henüz yaygın olarak kullanılmadığından ve çoğu sistemde bulunmadığından, daha yaygın ve kararlı olan Java 17 sürümüne geçildi. Bu, uygulamayı daha fazla ortamda çalıştırabilmeyi sağlar.

### 3. Dinamik Port Gösterimi İyileştirmesi

**Yapılan Değişiklik**: Başlangıç mesajlarındaki URL'lerdeki port numaraları, sabit değerler yerine `application.properties` dosyasından dinamik olarak okunacak şekilde düzenlendi.

**Değişiklik Yapılan Dosya**: `src/main/java/com/arabakiralama/ArabaKiralamaApplication.java`

**Yapılan Güncelleme**:
```java
// Eski kod
System.out.println("Tarayıcınızda http://localhost:8080 adresini açabilirsiniz");

// Yeni kod
ConfigurableApplicationContext context = SpringApplication.run(ArabaKiralamaApplication.class, args);
Environment env = context.getEnvironment();
String port = env.getProperty("server.port", "8090");
System.out.println("Tarayıcınızda http://localhost:" + port + " adresini açabilirsiniz");
```

**Nedeni**: Uygulama çıktısında gösterilen URL'lerdeki port numaralarının, gerçekte kullanılan port ile tutarsız olması kullanıcıların kafasını karıştırabilir. Bu değişiklik, uygulamanın başlangıç mesajlarında gösterdiği URL'lerin her zaman gerçek port numarasını içermesini sağlar.

### 4. README Dosyası Güncellemesi

**Yapılan Değişiklik**: README.md dosyası daha kapsamlı ve detaylı hale getirildi.

**Değişiklik Yapılan Dosya**: `README.md`

**Yapılan Güncelleme**:
- Proje yapısı ve bileşenleri detaylı olarak açıklandı
- API endpoint'leri ve kullanım örnekleri eklendi
- Kurulum ve çalıştırma adımları detaylandırıldı
- Veritabanı yapısı ve tablolar hakkında bilgi eklendi
- Uygulama özellikleri ve işlevleri detaylı olarak açıklandı

**Nedeni**: Projeyi daha anlaşılır ve kullanılabilir hale getirmek, yeni geliştiricilerin projeye hızlıca adapte olmasını sağlamak.

## 🚀 Uygulamanın Çalıştırılması

Uygulama üç farklı yöntemle çalıştırılabilir. Aşağıdaki yöntemlerden size en uygun olanı seçebilirsiniz:

### 💻 1. Windows'ta run.bat ile Çalıştırma (En Kolay Yöntem)

<details>
<summary><b>👉 Adım adım talimatlar için tıklayın</b></summary>

#### Gereksinimler:
- Java 17 JDK kurulu olmalı
- Maven kurulu olmalı (veya proje içindeki Maven Wrapper kullanılabilir)

#### Adımlar:

1. **Komut İstemi ile:**
   ```bash
   # Komut istemini açın (Windows + R tuşlarına basıp "cmd" yazarak)
   # Proje dizinine gidin
   cd c:\Users\EMRE\Desktop\araclirala2
   
   # run.bat dosyasını çalıştırın
   run.bat
   ```

2. **Dosya Gezgini ile:**
   - Windows Dosya Gezgini'ni açın
   - `c:\Users\EMRE\Desktop\araclirala2` klasörüne gidin
   - `run.bat` dosyasına çift tıklayın

#### Beklenen Çıktı:
```
Araba Kiralama Uygulamasi Baslatiliyor...
[INFO] Scanning for projects...
[INFO] Building Araba Kiralama 0.0.1-SNAPSHOT
...
Uygulama başlatıldı!
Tarayıcınızda http://localhost:8090 adresini açabilirsiniz
H2 veritabanı konsolu: http://localhost:8090/h2-console
API testi: http://localhost:8090/test
Araçları listele: http://localhost:8090/api/cars
```
</details>

### 📦 2. Docker ile Çalıştırma (Önerilen Yöntem)

<details>
<summary><b>👉 Adım adım talimatlar için tıklayın</b></summary>

#### Gereksinimler:
- Docker Desktop kurulu olmalı
- Docker Compose kurulu olmalı (Docker Desktop ile birlikte gelir)

#### Adımlar:

1. **Docker Compose ile Başlatma:**
   ```bash
   # Komut istemini açın
   # Proje dizinine gidin
   cd c:\Users\EMRE\Desktop\araclirala2
   
   # Docker Compose ile uygulamayı başlatın
   docker-compose up
   ```

2. **Arka Planda Çalıştırma (opsiyonel):**
   ```bash
   # Arka planda çalıştırmak için
   docker-compose up -d
   
   # Logları görmek için
   docker-compose logs -f
   ```

3. **Uygulamayı Durdurma:**
   ```bash
   # Uygulamayı durdurmak için
   docker-compose down
   ```

#### Beklenen Çıktı:
```
[+] Running 3/3
 ✓ Network araclirala2_default       Created
 ✓ Container araclirala2-postgres-1   Started
 ✓ Container araclirala2-app-1        Started
Uygulama başlatıldı!
Tarayıcınızda http://localhost:8090 adresini açabilirsiniz
H2 veritabanı konsolu: http://localhost:8090/h2-console
API testi: http://localhost:8090/test
Araçları listele: http://localhost:8090/api/cars
```
</details>

### 📚 3. IDE ile Çalıştırma (Geliştiriciler İçin)

<details>
<summary><b>👉 Adım adım talimatlar için tıklayın</b></summary>

#### Gereksinimler:
- Java 17 JDK kurulu olmalı
- IntelliJ IDEA, Eclipse veya VS Code gibi bir IDE kurulu olmalı

#### IntelliJ IDEA ile Adımlar:

1. IntelliJ IDEA'yı açın
2. `File > Open` menüsünden proje klasörünü seçin: `c:\Users\EMRE\Desktop\araclirala2`
3. Proje açıldıktan sonra, `src/main/java/com/arabakiralama/ArabaKiralamaApplication.java` dosyasını bulun
4. Dosyanın içinde, main metodunun yanındaki yeşil üçgen ‣ simgesine tıklayın veya sağ tıklayıp "Run 'ArabaKiralamaApplication'" seçeneğini seçin

#### Eclipse ile Adımlar:

1. Eclipse'i açın
2. `File > Import > Maven > Existing Maven Projects` yolunu izleyin
3. Root Directory olarak `c:\Users\EMRE\Desktop\araclirala2` klasörünü seçin
4. Proje açıldıktan sonra, `src/main/java/com/arabakiralama/ArabaKiralamaApplication.java` dosyasını bulun
5. Dosyaya sağ tıklayıp `Run As > Java Application` seçeneğini seçin

#### VS Code ile Adımlar:

1. VS Code'u açın
2. `File > Open Folder` menüsünden `c:\Users\EMRE\Desktop\araclirala2` klasörünü seçin
3. Java ve Spring Boot eklentilerinin yüklü olduğundan emin olun
4. `src/main/java/com/arabakiralama/ArabaKiralamaApplication.java` dosyasını açın
5. Dosyanın üst kısmındaki `Run | Debug` bağlantılarına tıklayın
</details>

### 🌐 Uygulamaya Erişim

Uygulama başarıyla başlatıldıktan sonra, aşağıdaki URL'ler üzerinden erişebilirsiniz:

| Sayfa | URL | Açıklama |
|------|-----|------------|
| 🏠 **Ana Sayfa** | [http://localhost:8090](http://localhost:8090) | Araç listesi ve kiralama işlemleri |
| 💾 **H2 Konsolu** | [http://localhost:8090/h2-console](http://localhost:8090/h2-console) | Veritabanı yönetim arayüzü |
| 💻 **API Testi** | [http://localhost:8090/test](http://localhost:8090/test) | API test sayfası |
| 🚗 **Araç Listesi API** | [http://localhost:8090/api/cars](http://localhost:8090/api/cars) | Tüm araçların JSON listesi |

