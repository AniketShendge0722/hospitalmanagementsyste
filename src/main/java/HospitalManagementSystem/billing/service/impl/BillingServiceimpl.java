package HospitalManagementSystem.billing.service.impl;

import HospitalManagementSystem.billing.entity.BillingEntity;
import HospitalManagementSystem.billing.repository.BillingRepository;
import HospitalManagementSystem.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillingServiceimpl implements BillingService
{
    @Autowired
    private BillingRepository billingRepository;

    @Override
    public BillingEntity saveBill(BillingEntity billing) {

        double total = billing.getDoctorFees() + billing.getMedicineCharges() + billing.getRoomCharges();
        billing.setTotalAmount(total);
        return billingRepository.saveBill(billing);
    }

    @Override
    public List<BillingEntity> getAllBills() {
        return billingRepository.getAllBills();
    }

    @Override
    public BillingEntity getBillById(Long id) {
        return billingRepository.getBillById(id);
    }

    @Override
    public BillingEntity updateBill(Long id, BillingEntity billing) {
        double total = billing.getDoctorFees() + billing.getMedicineCharges() + billing.getRoomCharges();
        billing.setTotalAmount(total);
        return billingRepository.updateBill(id, billing);
    }

    @Override
    public void deleteBill(Long id) {
        billingRepository.deleteBill(id);
    }
}
