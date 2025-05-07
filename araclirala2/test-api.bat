@echo off
echo Araba Kiralama API Test Komutlari

echo.
echo 1. Musterileri Listele
curl -X GET http://localhost:8090/api/customers

echo.
echo 2. Yeni Musteri Ekle
curl -X POST http://localhost:8090/api/customers -H "Content-Type: application/json" -d "{\"firstName\":\"Ali\",\"lastName\":\"Yilmaz\",\"email\":\"ali.yilmaz@example.com\",\"phoneNumber\":\"5551234567\",\"address\":\"Istanbul, Kadikoy\",\"drivingLicenseNumber\":\"B123456789\"}"

echo.
echo 3. Musterileri Tekrar Listele
curl -X GET http://localhost:8090/api/customers

echo.
echo 4. Araclari Listele
curl -X GET http://localhost:8090/api/cars

echo.
echo 5. Arac Kirala (1 numarali araci, 1 numarali musteriye)
curl -X POST "http://localhost:8090/api/rentals?carId=1&customerId=1" -H "Content-Type: application/json" -d "{\"startDate\":\"2025-05-10\",\"endDate\":\"2025-05-15\"}"

echo.
echo 6. Kiralamalari Listele
curl -X GET http://localhost:8090/api/rentals

pause
