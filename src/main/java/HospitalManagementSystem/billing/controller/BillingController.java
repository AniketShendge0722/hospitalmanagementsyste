package HospitalManagementSystem.billing.controller;

import HospitalManagementSystem.Patient.Pservice.PatientService;
import HospitalManagementSystem.billing.entity.BillingEntity;
import HospitalManagementSystem.billing.service.BillingService;
import HospitalManagementSystem.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BillingController {
    @Autowired
    private BillingService billingService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/billing")
    public String getAllBills(Model model) {
        model.addAttribute("bills", billingService.getAllBills());
        return "billing";
    }

    @GetMapping("/addBill")
    public String createBillForm(Model model) {
        BillingEntity billing = new BillingEntity();
        model.addAttribute("billing", billing);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "create_bill";
    }
    @PostMapping("/billing")
    public String saveBill(@ModelAttribute("billing") BillingEntity billing) {
        billingService.saveBill(billing);
        return "redirect:/billing";
    }

    @GetMapping("/billing/edit/{id}")
    public String editBillForm(@PathVariable Long id, Model model) {
        model.addAttribute("billing", billingService.getBillById(id));
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "edit_bill";
    }

    @PostMapping("/billing/{id}")
    public String updateBill(@PathVariable Long id, @ModelAttribute("billing") BillingEntity billing) {
        billingService.updateBill(id, billing);
        return "redirect:/billing";
    }

    @GetMapping("/billing/delete/{id}")
    public String deleteBill(@PathVariable Long id) {
        billingService.deleteBill(id);
        return "redirect:/billing";
    }

}
