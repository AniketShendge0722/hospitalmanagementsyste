package HospitalManagementSystem.doctor.repository.impl;

import HospitalManagementSystem.doctor.enitiy.DoctorEntity;
import HospitalManagementSystem.doctor.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepositoryimpl implements DoctorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper
    private final RowMapper<DoctorEntity>  doctorRowMapper = new RowMapper<DoctorEntity>()
    {

        @Override
        public DoctorEntity mapRow(ResultSet rs, int rowNum) throws SQLException
        {

            DoctorEntity doctor = new DoctorEntity();

            doctor.setId(rs.getLong("id"));
            doctor.setDoctorName(rs.getString("doctor_name"));
            doctor.setSpecialization(rs.getString("specialization"));
            doctor.setEmail(rs.getString("email"));
            doctor.setPhone(rs.getString("phone"));

            return doctor;
        }
    };

    // Save Doctor
    @Override
    public DoctorEntity save(DoctorEntity doctor)
    {

        if(doctor.getId() == null){

            String sql = "INSERT INTO doctors " + "(doctor_name, specialization, email, phone) " + "VALUES (?, ?, ?, ?)";

            jdbcTemplate.update(
                    sql,
                    doctor.getDoctorName(),
                    doctor.getSpecialization(),
                    doctor.getEmail(),
                    doctor.getPhone()
            );

        } else {

            String sql = "UPDATE doctors SET " + "doctor_name=?, " + "specialization=?, " + "email=?, " + "phone=? " + "WHERE id=?";

            jdbcTemplate.update(
                    sql,
                    doctor.getDoctorName(),
                    doctor.getSpecialization(),
                    doctor.getEmail(),
                    doctor.getPhone(),
                    doctor.getId()
            );
        }

        return doctor;
    }

    // Find By Id
    @Override
    public Optional<DoctorEntity>
    findById(Long id)
    {
        String sql = "SELECT * FROM doctors WHERE id=?";
        List<DoctorEntity> doctors = jdbcTemplate.query(sql, doctorRowMapper, id);
        return doctors.stream().findFirst();
    }

    // Find All
    @Override
    public List<DoctorEntity> findAll()
    {
        String sql = "SELECT * FROM doctors";
        return jdbcTemplate.query(sql, doctorRowMapper);
    }

    // Delete
    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM doctors WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

}
