package by.alex.spring.SecurityApp.service;


import by.alex.spring.SecurityApp.model.Patient;
import by.alex.spring.SecurityApp.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatientService {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void createPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public Patient getPatient(int id) {
        return patientRepository.findById(id).orElseThrow();
    }

    public Patient updatePatient(Patient updatePatient, int id) {
        updatePatient.setId(id);
        return patientRepository.save(updatePatient);
    }

    public void delete(int id) {
        patientRepository.deleteById(id);
    }
}
