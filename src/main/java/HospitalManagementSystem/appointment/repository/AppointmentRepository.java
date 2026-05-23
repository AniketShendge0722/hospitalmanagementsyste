package HospitalManagementSystem.appointment.repository;

import HospitalManagementSystem.appointment.entity.AppointmentEntity;

import java.util.List;

public interface AppointmentRepository {
    AppointmentEntity saveAppointment(AppointmentEntity appointment);
    List<AppointmentEntity> getAllAppointments();
    AppointmentEntity getAppointmentById(Long id);
    AppointmentEntity updateAppointment(Long id,AppointmentEntity appointment);
    void deleteAppointment(Long id);
}
