package HospitalManagementSystem.appointment.service.impl;

import HospitalManagementSystem.appointment.entity.AppointmentEntity;
import HospitalManagementSystem.appointment.repository.AppointmentRepository;
import HospitalManagementSystem.appointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceimpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public AppointmentEntity saveAppointment(AppointmentEntity appointment) {

        return appointmentRepository.saveAppointment(appointment);
    }

    @Override
    public List<AppointmentEntity> getAllAppointments() {

        return appointmentRepository.getAllAppointments();
    }

    @Override
    public AppointmentEntity getAppointmentById(Long id) {

        return appointmentRepository.getAppointmentById(id);
    }

    @Override
    public AppointmentEntity updateAppointment(Long id, AppointmentEntity appointment) {

        return appointmentRepository.updateAppointment(id, appointment);
    }

    @Override
    public void deleteAppointment(Long id) {

        appointmentRepository.deleteAppointment(id);
    }
}