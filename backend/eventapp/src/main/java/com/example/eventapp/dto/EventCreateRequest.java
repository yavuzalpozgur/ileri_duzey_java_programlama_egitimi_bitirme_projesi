package com.example.eventapp.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class EventCreateRequest {

    @NotBlank(message = "Başlık boş olamaz")
    private String title;

    @FutureOrPresent(
            message = "Geçmiş tarih seçilemez"
    )
    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime time;

    @NotBlank(message = "Yer boş olamaz")
    private String location;

    @NotBlank(message = "Açıklama boş olamaz")
    private String description;

    @NotBlank(message = "Kategori boş olamaz")
    private String category;
}

