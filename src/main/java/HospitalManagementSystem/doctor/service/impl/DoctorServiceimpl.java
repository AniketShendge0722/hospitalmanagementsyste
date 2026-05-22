package HospitalManagementSystem.doctor.service.impl;

import HospitalManagementSystem.doctor.enitiy.DoctorEntity;
import HospitalManagementSystem.doctor.repository.DoctorRepository;
import HospitalManagementSystem.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceimpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<DoctorEntity> getAllDoctors() {

        return doctorRepository.findAll();
    }

    @Override
    public DoctorEntity saveDoctor(DoctorEntity doctor) {

        return doctorRepository.save(doctor);
    }

    @Override
    public DoctorEntity getDoctorById(Long id) {

        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public DoctorEntity updateDoctor(DoctorEntity doctor) {

        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctorById(Long id) {

        doctorRepository.deleteById(id);
    }
}
