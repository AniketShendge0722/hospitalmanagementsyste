package HospitalManagementSystem.billing.repository.impl;

import HospitalManagementSystem.billing.entity.BillingEntity;
import HospitalManagementSystem.billing.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BillingRepositoryimpl implements BillingRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public BillingEntity saveBill(BillingEntity billing) {

        String sql = "INSERT INTO billing " +
                "(patient_name, doctor_name, doctor_fees, medicine_charges, " +
                "room_charges, total_amount, payment_status, billing_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                billing.getPatientName(),
                billing.getDoctorName(),
                billing.getDoctorFees(),
                billing.getMedicineCharges(),
                billing.getRoomCharges(),
                billing.getTotalAmount(),
                billing.getPaymentStatus(),
                billing.getBillingDate());

        return billing;
    }

    @Override
    public List<BillingEntity> getAllBills() {

        String sql = "SELECT * FROM billing";

        return jdbcTemplate.query(sql, (rs, rowNum) -> new BillingEntity(
                        rs.getLong("bill_id"),
                        rs.getString("patient_name"),
                        rs.getString("doctor_name"),
                        rs.getDouble("doctor_fees"),
                        rs.getDouble("medicine_charges"),
                        rs.getDouble("room_charges"),
                        rs.getDouble("total_amount"),
                        rs.getString("payment_status"),
                        rs.getString("billing_date")
                ));
    }

    @Override
    public BillingEntity getBillById(Long id) {

        String sql = "SELECT * FROM billing WHERE bill_id=?";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) ->
                        new BillingEntity(
                                rs.getLong("bill_id"),
                                rs.getString("patient_name"),
                                rs.getString("doctor_name"),
                                rs.getDouble("doctor_fees"),
                                rs.getDouble("medicine_charges"),
                                rs.getDouble("room_charges"),
                                rs.getDouble("total_amount"),
                                rs.getString("payment_status"),
                                rs.getString("billing_date")
                        ));
    }

    @Override
    public BillingEntity updateBill(Long id, BillingEntity billing) {

        String sql = "UPDATE billing SET " + "patient_name=?, " + "doctor_name=?, " + "doctor_fees=?, " + "medicine_charges=?, " + "room_charges=?, " + "total_amount=?, " + "payment_status=?, " + "billing_date=? " + "WHERE bill_id=?";

        jdbcTemplate.update(sql,
                billing.getPatientName(),
                billing.getDoctorName(),
                billing.getDoctorFees(),
                billing.getMedicineCharges(),
                billing.getRoomCharges(),
                billing.getTotalAmount(),
                billing.getPaymentStatus(),
                billing.getBillingDate(),
                id);

        return billing;
    }

    @Override
    public void deleteBill(Long id) {

        String sql = "DELETE FROM billing WHERE bill_id=?";

        jdbcTemplate.update(sql, id);
    }
}
