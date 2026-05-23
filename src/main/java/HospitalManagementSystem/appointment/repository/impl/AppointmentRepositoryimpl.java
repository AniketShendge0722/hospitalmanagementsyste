package HospitalManagementSystem.appointment.repository.impl;

import HospitalManagementSystem.appointment.entity.AppointmentEntity;
import HospitalManagementSystem.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentRepositoryimpl implements AppointmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public AppointmentEntity saveAppointment(AppointmentEntity appointment) {

        String sql = "INSERT INTO appointments " + "(patient_name, doctor_name, appointment_date, time_slot, status) " + "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                appointment.getPatientName(),
                appointment.getDoctorName(),
                appointment.getAppointmentDate(),
                appointment.getTimeSlot(),
                appointment.getStatus());

        return appointment;
    }

    @Override
    public List<AppointmentEntity> getAllAppointments() {

        String sql = "SELECT * FROM appointments";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new AppointmentEntity(
                        rs.getLong("appointment_id"),
                        rs.getString("patient_name"),
                        rs.getString("doctor_name"),
                        rs.getString("appointment_date"),
                        rs.getString("time_slot"),
                        rs.getString("status")
                ));
    }

    @Override
    public AppointmentEntity getAppointmentById(Long id) {

        String sql = "SELECT * FROM appointments WHERE appointment_id=?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                        new AppointmentEntity(
                                rs.getLong("appointment_id"),
                                rs.getString("patient_name"),
                                rs.getString("doctor_name"),
                                rs.getString("appointment_date"),
                                rs.getString("time_slot"),
                                rs.getString("status")
                        ));
    }

    @Override
    public AppointmentEntity updateAppointment(Long id, AppointmentEntity appointment) {

        String sql = "UPDATE appointments SET " + "patient_name=?, " + "doctor_name=?, " + "appointment_date=?, " + "time_slot=?, " + "status=? " + "WHERE appointment_id=?";

        jdbcTemplate.update(sql,
                appointment.getPatientName(),
                appointment.getDoctorName(),
                appointment.getAppointmentDate(),
                appointment.getTimeSlot(),
                appointment.getStatus(),
                id);

        return appointment;
    }

    @Override
    public void deleteAppointment(Long id) {

        String sql = "DELETE FROM appointments WHERE appointment_id=?";

        jdbcTemplate.update(sql, id);
    }
}
