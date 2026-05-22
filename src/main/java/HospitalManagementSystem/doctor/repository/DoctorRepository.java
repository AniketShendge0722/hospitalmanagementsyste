package HospitalManagementSystem.doctor.repository;

import HospitalManagementSystem.doctor.enitiy.DoctorEntity;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository {

    DoctorEntity save(DoctorEntity doctor);

    Optional<DoctorEntity> findById(Long id);

    List<DoctorEntity> findAll();

    void deleteById(Long id);
}
