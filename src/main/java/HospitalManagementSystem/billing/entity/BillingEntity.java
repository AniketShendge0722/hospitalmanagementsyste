package HospitalManagementSystem.billing.entity;

public class BillingEntity {

    private Long billId;

    private String patientName;

    private String doctorName;

    private double doctorFees;

    private double medicineCharges;

    private double roomCharges;

    private double totalAmount;

    private String paymentStatus;

    private String billingDate;

    public BillingEntity() {
    }

    public BillingEntity(Long billId,
                         String patientName,
                         String doctorName,
                         double doctorFees,
                         double medicineCharges,
                         double roomCharges,
                         double totalAmount,
                         String paymentStatus,
                         String billingDate) {

        this.billId = billId;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.doctorFees = doctorFees;
        this.medicineCharges = medicineCharges;
        this.roomCharges = roomCharges;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.billingDate = billingDate;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
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

    public double getDoctorFees() {
        return doctorFees;
    }

    public void setDoctorFees(double doctorFees) {
        this.doctorFees = doctorFees;
    }

    public double getMedicineCharges() {
        return medicineCharges;
    }

    public void setMedicineCharges(double medicineCharges) {
        this.medicineCharges = medicineCharges;
    }

    public double getRoomCharges() {
        return roomCharges;
    }

    public void setRoomCharges(double roomCharges) {
        this.roomCharges = roomCharges;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }
}
