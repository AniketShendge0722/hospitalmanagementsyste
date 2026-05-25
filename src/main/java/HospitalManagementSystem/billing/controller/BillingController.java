package HospitalManagementSystem.billing.controller;

import HospitalManagementSystem.Patient.Pservice.PatientService;
import HospitalManagementSystem.billing.entity.BillingEntity;
import HospitalManagementSystem.billing.service.BillingService;
import HospitalManagementSystem.doctor.service.DoctorService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
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
    @GetMapping("/billing/pdf/{id}")
    public void generatePdf(@PathVariable Long id, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=bill_" + id + ".pdf");
        BillingEntity bill = billingService.getBillById(id);
        Document document = new Document(PageSize.A4, 40, 40, 50, 50);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        // ================= COLORS =================
        BaseColor primaryBlue = new BaseColor(25, 118, 210);
        BaseColor darkGray = new BaseColor(52, 58, 64);
        BaseColor lightGray = new BaseColor(245, 245, 245);
        BaseColor green = new BaseColor(40, 167, 69);
        // ================= FONTS =================
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.WHITE);
        Font subTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.WHITE);
        Font labelFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.DARK_GRAY);
        Font valueFont = FontFactory.getFont(FontFactory.HELVETICA, 11, BaseColor.BLACK);
        Font totalFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.WHITE);
        Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12, BaseColor.GRAY);
        // ================= HEADER =================
        PdfPTable headerTable = new PdfPTable(1);
        headerTable.setWidthPercentage(100);
        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(primaryBlue);
        headerCell.setPadding(20);
        headerCell.setBorder(Rectangle.NO_BORDER);
        Paragraph hospitalTitle = new Paragraph("🏥 Hospital Management System", titleFont);
        Paragraph invoiceTitle = new Paragraph("Patient Billing Invoice", subTitleFont);
        hospitalTitle.setAlignment(Element.ALIGN_CENTER);
        invoiceTitle.setAlignment(Element.ALIGN_CENTER);
        headerCell.addElement(hospitalTitle);
        headerCell.addElement(invoiceTitle);
        headerTable.addCell(headerCell);
        document.add(headerTable);
        document.add(new Paragraph(" "));
        // ================= BILL INFO =================
        PdfPTable billInfoTable = new PdfPTable(3);
        billInfoTable.setWidthPercentage(100);
        billInfoTable.setSpacingAfter(20);
        PdfPCell cell1 = new PdfPCell();
        cell1.setBorder(Rectangle.NO_BORDER);
        cell1.addElement(new Paragraph("Bill ID", labelFont));
        cell1.addElement(new Paragraph("#000" + bill.getBillId(), valueFont));
        PdfPCell cell2 = new PdfPCell();
        cell2.setBorder(Rectangle.NO_BORDER);
        cell2.addElement(new Paragraph("Billing Date", labelFont));
        cell2.addElement(new Paragraph(bill.getBillingDate(), valueFont));
        PdfPCell cell3 = new PdfPCell();
        cell3.setBorder(Rectangle.NO_BORDER);
        Paragraph status = new Paragraph(bill.getPaymentStatus(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, BaseColor.WHITE));
        PdfPCell statusBadge = new PdfPCell(status);
        statusBadge.setHorizontalAlignment(Element.ALIGN_CENTER);
        statusBadge.setVerticalAlignment(Element.ALIGN_MIDDLE);
        statusBadge.setPadding(8);
        statusBadge.setBackgroundColor(green);
        statusBadge.setBorder(Rectangle.NO_BORDER);
        PdfPTable badgeTable = new PdfPTable(1);
        badgeTable.addCell(statusBadge);
        cell3.addElement(new Paragraph("Payment Status", labelFont));
        cell3.addElement(badgeTable);
        billInfoTable.addCell(cell1);
        billInfoTable.addCell(cell2);
        billInfoTable.addCell(cell3);
        document.add(billInfoTable);
        // ================= PATIENT INFO =================
        PdfPTable patientTable = new PdfPTable(1);
        patientTable.setWidthPercentage(100);
        PdfPCell patientCell = new PdfPCell();
        patientCell.setPadding(15);
        patientCell.setBackgroundColor(lightGray);
        patientCell.setBorderColor(BaseColor.LIGHT_GRAY);
        String initials = "";
        if (bill.getPatientName() != null && !bill.getPatientName().isEmpty())
        {
            String[] names = bill.getPatientName().split(" ");

            for (String n : names)
            {
                initials += n.substring(0, 1).toUpperCase();
            }
        }
        Paragraph patientHeading = new Paragraph("👤 Patient Information", labelFont);
        Paragraph patientName = new Paragraph("Patient Name: " + bill.getPatientName(), valueFont);
        Paragraph doctorName = new Paragraph("Attending Doctor: " + bill.getDoctorName(), valueFont);
        Paragraph avatar = new Paragraph("Patient Initials: " + initials, valueFont);
        patientCell.addElement(patientHeading);
        patientCell.addElement(new Paragraph(" "));
        patientCell.addElement(avatar);
        patientCell.addElement(patientName);
        patientCell.addElement(doctorName);
        patientTable.addCell(patientCell);
        document.add(patientTable);
        document.add(new Paragraph(" "));
        // ================= CHARGES TABLE =================
        PdfPTable chargesTable = new PdfPTable(2);
        chargesTable.setWidthPercentage(100);
        chargesTable.setWidths(new int[]{4, 2});
        PdfPCell itemHeader = new PdfPCell(new Phrase("Charge Type", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
        itemHeader.setBackgroundColor(darkGray);
        itemHeader.setPadding(10);
        PdfPCell amountHeader = new PdfPCell(new Phrase("Amount", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
        amountHeader.setBackgroundColor(darkGray);
        amountHeader.setPadding(10);
        chargesTable.addCell(itemHeader);
        chargesTable.addCell(amountHeader);
        chargesTable.addCell("🩺 Doctor Fees");
        chargesTable.addCell("₹ " + bill.getDoctorFees());
        chargesTable.addCell("💊 Medicine Charges");
        chargesTable.addCell("₹ " + bill.getMedicineCharges());
        chargesTable.addCell("🛏 Room Charges");
        chargesTable.addCell("₹ " + bill.getRoomCharges());
        document.add(chargesTable);
        document.add(new Paragraph(" "));
        // ================= TOTAL SECTION =================
        PdfPTable totalTable = new PdfPTable(1);
        totalTable.setWidthPercentage(100);
        PdfPCell totalCell = new PdfPCell();
        totalCell.setBackgroundColor(primaryBlue);
        totalCell.setPadding(15);
        totalCell.setBorder(Rectangle.NO_BORDER);
        Paragraph totalText = new Paragraph("Total Amount: ₹ " + bill.getTotalAmount(), totalFont);
        totalText.setAlignment(Element.ALIGN_CENTER);
        totalCell.addElement(totalText);
        totalTable.addCell(totalCell);
        document.add(totalTable);
        document.add(new Paragraph(" "));
        // ================= FOOTER =================
        Paragraph footer = new Paragraph("Thank you! Get well soon. ❤️", footerFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
        document.close();
    }

}
