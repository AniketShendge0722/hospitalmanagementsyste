package HospitalManagementSystem.Patient.Prepository.impl;

import HospitalManagementSystem.Patient.Prepository.PatientRepository;
import HospitalManagementSystem.Patient.pentity.Patient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    private final JdbcTemplate jdbcTemplate;

    public PatientRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Row Mapper
    private final RowMapper<Patient> patientRowMapper = new RowMapper<Patient>() {

        @Override
        public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {

            Patient patient = new Patient();

            patient.setId(rs.getLong("id"));
            patient.setFirstName(rs.getString("first_name"));
            patient.setLastName(rs.getString("last_name"));
            patient.setAge(rs.getInt("age"));
            patient.setGender(rs.getString("gender"));
            patient.setBloodGroup(rs.getString("blood_group"));
            patient.setPhoneNumber(rs.getString("phone_number"));
            patient.setEmail(rs.getString("email"));
            patient.setAddress(rs.getString("address"));
            patient.setDisease(rs.getString("disease"));
            patient.setDoctorName(rs.getString("doctor_name"));
            
            // Use getObject for safe mapping of nullable LocalDate fields
            patient.setAdmissionDate(rs.getObject("admission_date", java.time.LocalDate.class));
            patient.setDischargeDate(rs.getObject("discharge_date", java.time.LocalDate.class));

            return patient;
        }
    };

    // Save Patient
    @Override
    public Patient save(Patient patient) {

        if (patient.getId() == null) {

            // Insert Query
            String sql = """
                    INSERT INTO patients
                    (
                        first_name,
                        last_name,
                        age,
                        gender,
                        blood_group,
                        phone_number,
                        email,
                        address,
                        disease,
                        doctor_name,
                        admission_date,
                        discharge_date
                    )
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;

            jdbcTemplate.update(
                    sql,
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getAge(),
                    patient.getGender(),
                    patient.getBloodGroup(),
                    patient.getPhoneNumber(),
                    patient.getEmail(),
                    patient.getAddress(),
                    patient.getDisease(),
                    patient.getDoctorName(),
                    patient.getAdmissionDate(),
                    patient.getDischargeDate()
            );

        } else {

            // Update Query
            String sql = """
                    UPDATE patients
                    SET  first_name = ?,last_name = ?,age = ?,gender = ?,blood_group = ?,phone_number = ?, email = ?,
                        address = ?,disease = ?,doctor_name = ?,admission_date = ?,discharge_date = ?
                    WHERE id = ? """;

            jdbcTemplate.update(
                    sql,
                    patient.getFirstName(),
                    patient.getLastName(),
                    patient.getAge(),
                    patient.getGender(),
                    patient.getBloodGroup(),
                    patient.getPhoneNumber(),
                    patient.getEmail(),
                    patient.getAddress(),
                    patient.getDisease(),
                    patient.getDoctorName(),
                    patient.getAdmissionDate(),
                    patient.getDischargeDate(),
                    patient.getId()
            );
        }

        return patient;
    }

    // Get All Patients
    @Override
    public List<Patient> findAll() {

        String sql = "SELECT * FROM patients";

        return jdbcTemplate.query(sql, patientRowMapper);
    }

    // Get Patient By Id
    @Override
    public Optional<Patient> findById(Long id) {

        String sql = "SELECT * FROM patients WHERE id = ?";

        List<Patient> patients = jdbcTemplate.query(
                sql,
                patientRowMapper,
                id
        );

        return patients.stream().findFirst();
    }

    // Delete Patient
    @Override
    public void deleteById(Long id) {

        String sql = "DELETE FROM patients WHERE id = ?";

        jdbcTemplate.update(sql, id);
    }

    // Check Patient Exists
    @Override
    public boolean existsById(Long id) {

        String sql = "SELECT COUNT(*) FROM patients WHERE id = ?";

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                id
        );

        return count != null && count > 0;
    }

    // Count Total Patients
    @Override
    public long count() {

        String sql = "SELECT COUNT(*) FROM patients";

        Long count = jdbcTemplate.queryForObject(
                sql,
                Long.class
        );

        return count != null ? count : 0L;
    }
}
