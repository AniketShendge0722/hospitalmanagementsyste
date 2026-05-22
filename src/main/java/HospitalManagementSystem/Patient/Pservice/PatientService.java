package HospitalManagementSystem.Patient.Pservice;

import HospitalManagementSystem.Patient.pentity.Patient;

import java.util.List;

public interface PatientService {
    Patient savePatient(Patient patient);
    List<Patient> getAllPatients();
    Patient getPatientById(Long id);
    Patient updatePatient(Patient patient);
    void deletePatientById(Long id);

}
