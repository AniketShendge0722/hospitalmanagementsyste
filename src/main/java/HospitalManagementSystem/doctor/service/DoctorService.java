package HospitalManagementSystem.doctor.service;

import HospitalManagementSystem.doctor.enitiy.DoctorEntity;

import java.util.List;

public interface DoctorService {

    List<DoctorEntity> getAllDoctors();

    DoctorEntity saveDoctor(DoctorEntity doctor);

    DoctorEntity getDoctorById(Long id);

    DoctorEntity updateDoctor(DoctorEntity doctor);

    void deleteDoctorById(Long id);
}
