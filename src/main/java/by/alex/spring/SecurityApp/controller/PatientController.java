package by.alex.spring.SecurityApp.controller;

import by.alex.spring.SecurityApp.dto.PatientDto;
import by.alex.spring.SecurityApp.model.Patient;
import by.alex.spring.SecurityApp.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @Operation(
            summary = "Создание пациента",
            description = "Позволяет создать пациента"
    )
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> createPatient(@RequestBody PatientDto dto) {
        patientService.createPatient(convertToPatient(dto));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(
            summary = "Получение пациента",
            description = "Позволяет получить пациента по id"
    )
    @GetMapping("/get/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(patientService.getPatient(id));
    }

    @Operation(
            summary = "Обновление пациента",
            description = "Обновляет пациента по id и переданным данным"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(
            @RequestBody PatientDto dto,
            @PathVariable(name = "id") int id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(patientService.updatePatient(convertToPatient(dto), id));
    }

    @Operation(
            summary = "Удаление пациента",
            description = "Удаляет пациента по id"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable(name = "id") int id) {
        patientService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Patient convertToPatient(PatientDto dto) {
        Patient patient = new Patient();
        patient.setName(dto.name());
        patient.setGender(dto.gender());
        patient.setBirthDate(LocalDateTime.parse(dto.birthDate()));
        return patient;
    }
}
