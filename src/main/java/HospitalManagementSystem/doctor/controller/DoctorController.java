package HospitalManagementSystem.doctor.controller;

import HospitalManagementSystem.doctor.enitiy.DoctorEntity;
import HospitalManagementSystem.doctor.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DoctorController {


    @Autowired
    private DoctorService doctorService;

    // Get All Doctors
    @GetMapping("/doctors")
    public String getAllDoctors(Model model){
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctors";
    }

    // Open Add Doctor Form
    @GetMapping("/doctors/new")
    public String createDoctorForm(Model model){
        DoctorEntity doctor = new DoctorEntity();
        model.addAttribute("doctor", doctor);
        return "create_doctor";
    }

    // Save Doctor
    @PostMapping("/doctors")
    public String saveDoctor(@Valid @ModelAttribute("doctor")
            DoctorEntity doctor, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "create_doctor";
        }
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    // Edit Doctor Form
    @GetMapping("/doctors/edit/{id}")
    public String editDoctorForm(@PathVariable Long id, Model model){
        DoctorEntity doctor = doctorService.getDoctorById(id);
        model.addAttribute("doctor", doctor);
        return "edit_doctor";
    }

    // Update Doctor
    @PostMapping("/doctors/{id}")
    public String updateDoctor(@PathVariable Long id, @Valid @ModelAttribute("doctor")
            DoctorEntity doctor, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            doctor.setId(id);
            return "edit_doctor";
        }
        doctor.setId(id);
        doctorService.updateDoctor(doctor);
        return "redirect:/doctors";
    }

    // Delete Doctor
    @GetMapping("/doctors/delete/{id}")
    public String deleteDoctor(@PathVariable Long id){
        doctorService.deleteDoctorById(id);
        return "redirect:/doctors";
    }

}
