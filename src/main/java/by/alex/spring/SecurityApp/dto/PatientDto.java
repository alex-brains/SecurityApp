package by.alex.spring.SecurityApp.dto;

import java.time.LocalDateTime;

public record PatientDto(
        String name,
        String gender,
        String birthDate
) {
}
