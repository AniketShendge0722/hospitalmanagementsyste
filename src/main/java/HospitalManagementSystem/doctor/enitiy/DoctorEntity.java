package HospitalManagementSystem.doctor.enitiy;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "doctors")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_name")
    @NotBlank(message = "Doctor name is required")
    private String doctorName;

    @Column(name = "specialization")
    @NotBlank(message = "Specialization is required")
    private String specialization;

    @Column(name = "email")
    @Email(message = "Enter valid email")
    private String email;

    @Column(name = "phone")
    @NotBlank(message = "Phone number is required")
    private String phone;

    // Default Constructor
    public DoctorEntity() {
    }

    // Parameterized Constructor
    public DoctorEntity(Long id,
                        String doctorName,
                        String specialization,
                        String email,
                        String phone) {

        this.id = id;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}