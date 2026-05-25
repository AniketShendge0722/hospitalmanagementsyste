package HospitalManagementSystem.Patient.pconroller;

import HospitalManagementSystem.Patient.Pservice.PatientService;
import HospitalManagementSystem.Patient.pentity.Patient;
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
public class PatientController {
    @Autowired
    private PatientService patientService;

    // Home Page
//    @GetMapping("/")
//    public String homePage() {
//        return "home";
//    }

    // Get All Patients
    @GetMapping("/patients")
    public String getAllPatients(Model model) {

        model.addAttribute("patients", patientService.getAllPatients());

        return "patients";
    }

    // Open Add Patient Form
    @GetMapping("/addPatient")
    public String createPatientForm(Model model) {

        Patient patient = new Patient();

        model.addAttribute("patient", patient);

        return "create_patient";
    }

    // Save Patient
    @PostMapping("/patients")
    public String savePatient(
            @Valid @ModelAttribute("patient") Patient patient,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "create_patient";
        }

        patientService.savePatient(patient);

        return "redirect:/patients";
    }

    // Open Edit Patient Form
    @GetMapping("/patients/edit/{id}")
    public String editPatientForm(@PathVariable Long id, Model model) {

        Patient patient = patientService.getPatientById(id);

        if (patient != null) {

            model.addAttribute("patient", patient);

            return "edit_patient";
        }

        return "redirect:/patients";
    }

    // Update Patient
    @PostMapping("/patients/{id}")
    public String updatePatient(
            @PathVariable Long id,
            @Valid @ModelAttribute("patient") Patient patient,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            patient.setId(id);

            return "edit_patient";
        }

        patient.setId(id);

        patientService.updatePatient(patient);

        return "redirect:/patients";
    }

    // Delete Patient
    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable Long id) {

        patientService.deletePatientById(id);

        return "redirect:/patients";
    }

}