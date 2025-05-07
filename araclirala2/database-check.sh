#!/bin/bash

# Veritabanı şemalarını listele
echo "Veritabanı tabloları:"
docker-compose exec postgres psql -U postgres -d arabakiralama -c "\dt"

# Customer tablosunun yapısını göster
echo "Customer tablosu yapısı:"
docker-compose exec postgres psql -U postgres -d arabakiralama -c "\d customers"

# Car tablosunun yapısını göster
echo "Car tablosu yapısı:"
docker-compose exec postgres psql -U postgres -d arabakiralama -c "\d cars"

# Rental tablosunun yapısını göster
echo "Rental tablosu yapısı:"
docker-compose exec postgres psql -U postgres -d arabakiralama -c "\d rentals"
