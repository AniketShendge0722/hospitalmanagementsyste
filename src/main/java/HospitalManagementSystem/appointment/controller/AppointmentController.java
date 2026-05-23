package HospitalManagementSystem.appointment.controller;

import HospitalManagementSystem.appointment.entity.AppointmentEntity;
import HospitalManagementSystem.appointment.service.AppointmentService;
import HospitalManagementSystem.Patient.Pservice.PatientService;
import HospitalManagementSystem.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/appointments")
    public String getAllAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointments";
    }

    @GetMapping("/appointments/create")
    public String createAppointmentForm(Model model) {
        AppointmentEntity appointment = new AppointmentEntity();
        model.addAttribute("appointment", appointment);
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "create_appointment";
    }

    @PostMapping("/appointments")
    public String saveAppointment(@ModelAttribute("appointment") AppointmentEntity appointment) {
        appointmentService.saveAppointment(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/edit/{id}")
    public String editAppointmentForm(@PathVariable Long id, Model model) {
        model.addAttribute("appointment", appointmentService.getAppointmentById(id));
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "edit_appointment";
    }

    @PostMapping("/appointments/{id}")
    public String updateAppointment(@PathVariable Long id, @ModelAttribute("appointment") AppointmentEntity appointment) {
        appointmentService.updateAppointment(id, appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/delete/{id}")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "redirect:/appointments";
    }

}
