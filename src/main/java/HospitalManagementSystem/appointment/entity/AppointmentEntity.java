package HospitalManagementSystem.appointment.entity;



public class AppointmentEntity {

    private Long appointmentId;
    private String patientName;
    private String doctorName;
    private String appointmentDate;
    private String timeSlot;
    private String status;

    public AppointmentEntity() {
    }

    public AppointmentEntity(Long appointmentId, String patientName,
                             String doctorName,
                             String appointmentDate,
                             String timeSlot,
                             String status) {

        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.status = status;

    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
