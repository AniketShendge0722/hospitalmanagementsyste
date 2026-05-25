package HospitalManagementSystem.dashboard;

import HospitalManagementSystem.Patient.Pservice.PatientService;
import HospitalManagementSystem.appointment.service.AppointmentService;
import HospitalManagementSystem.billing.entity.BillingEntity;
import HospitalManagementSystem.billing.service.BillingService;
import HospitalManagementSystem.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

        @Autowired
        private PatientService patientService;
        @Autowired
        private DoctorService doctorService;
        @Autowired
        private AppointmentService appointmentService;
        @Autowired
        private BillingService billingService;

        @GetMapping("/")
        public String home(Model model) {

            int totalPatients = patientService.getAllPatients().size();
            int totalDoctors = doctorService.getAllDoctors().size();
            int totalAppointments = appointmentService.getAllAppointments().size();
            int totalBills = billingService.getAllBills().size();
            double totalRevenue = billingService.getAllBills().stream().mapToDouble(BillingEntity::getTotalAmount).sum();
            model.addAttribute("totalPatients", totalPatients);
            model.addAttribute("totalDoctors", totalDoctors);
            model.addAttribute("totalAppointments", totalAppointments);
            model.addAttribute("totalBills", totalBills);
            model.addAttribute("totalRevenue", totalRevenue);
            return "home";
        }

}
