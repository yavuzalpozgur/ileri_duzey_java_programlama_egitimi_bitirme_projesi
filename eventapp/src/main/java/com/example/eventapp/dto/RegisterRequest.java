package com.example.eventapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    // Ekstra Sıkı Validation
    @NotBlank(message = "Ad soyad boş olamaz")
    private String fullName;

    @Email(message = "Geçerli email giriniz")
    @NotBlank(message = "Email boş olamaz")
    private String email;

    @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır")
    private String password;
}



