package HospitalManagementSystem.billing.repository;

import HospitalManagementSystem.billing.entity.BillingEntity;

import java.util.List;

public interface BillingRepository {
    BillingEntity saveBill(BillingEntity billing);

    List<BillingEntity> getAllBills();

    BillingEntity getBillById(Long id);

    BillingEntity updateBill(Long id, BillingEntity billing);

    void deleteBill(Long id);
}
