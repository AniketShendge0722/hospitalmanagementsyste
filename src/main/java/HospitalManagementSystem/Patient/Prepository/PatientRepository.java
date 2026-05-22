package HospitalManagementSystem.Patient.Prepository;

import HospitalManagementSystem.Patient.pentity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository {
    Patient save(Patient patient);
    List<Patient> findAll();
    Optional<Patient> findById(Long id);
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();

}
