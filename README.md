# EventApp 

EventApp, Angular 17 ve Spring Boot kullanılarak geliştirilmiş modern bir etkinlik yönetim sistemidir.

Kullanıcılar:
- Kayıt olabilir
- Giriş yapabilir
- JWT ile doğrulanabilir
- Etkinlik oluşturabilir
- Etkinlik yayınlayabilir
- Etkinliklere katılabilir
- Kendi etkinliklerini yönetebilir

---

# Kullanılan Teknolojiler

## Frontend
- Angular 17
- TypeScript
- Bootstrap 5
- Standalone Component Architecture

## Backend
- Spring Boot
- Spring Security
- JWT Authentication
- BCrypt Password Hashing
- JPA / Hibernate

## Veritabanı
- PostgreSQL

## Araçlar
- Swagger
- Docker
- Postman
- Maven

---

# Proje Özellikleri

## Kimlik Doğrulama
- Kullanıcı kayıt sistemi
- JWT tabanlı giriş sistemi
- Route Guard koruması
- Token bazlı yetkilendirme

## Etkinlik Yönetimi
- Etkinlik oluşturma
- Etkinlik listeleme
- Etkinlik detay görüntüleme
- Etkinlik yayınlama
- Etkinliğe katılım sistemi

## UI Özellikleri
- Responsive tasarım
- Bootstrap 5 arayüzü
- Modern Angular kontrol akışları (@if, @for)
- Login/Register geçiş sistemi

---

# Proje Mimarisi

```text
Angular Frontend --> Spring Boot REST API --> PostgreSQL Database
