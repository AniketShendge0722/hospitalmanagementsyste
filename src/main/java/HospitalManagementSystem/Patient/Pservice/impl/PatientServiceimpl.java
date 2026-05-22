package HospitalManagementSystem.Patient.Pservice.impl;

import HospitalManagementSystem.Patient.Pservice.PatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceimpl implements PatientService {

    private final HospitalManagementSystem.Patient.Prepository.PatientRepository patientRepository;

    public PatientServiceimpl(HospitalManagementSystem.Patient.Prepository.PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public HospitalManagementSystem.Patient.pentity.Patient savePatient(HospitalManagementSystem.Patient.pentity.Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public java.util.List<HospitalManagementSystem.Patient.pentity.Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public HospitalManagementSystem.Patient.pentity.Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public HospitalManagementSystem.Patient.pentity.Patient updatePatient(HospitalManagementSystem.Patient.pentity.Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }


}
