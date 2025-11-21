package com.seanclen.capstone.controller;

import com.seanclen.capstone.model.Appointment;
import com.seanclen.capstone.form.AppointmentForm;
import com.seanclen.capstone.service.AppointmentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/appointments")
public class AppointmentWebController {

    private final AppointmentService appointmentService;

    public AppointmentWebController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointments"; 
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("appointment", new AppointmentForm());
        return "appointments_form";
    }

    @GetMapping("/{id}") // Maps to GET /appointments/{id} for editing
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        if (appointment == null) {
            redirectAttributes.addFlashAttribute("error", "Appointment not found.");
            return "redirect:/appointments";
        }
        // Map the model Appointment to AppointmentForm
        AppointmentForm appointmentForm = new AppointmentForm();
        appointmentForm.setId(appointment.getId());
        appointmentForm.setDate(appointment.getDate().toString());
        appointmentForm.setDescription(appointment.getDescription());

        // Pass the existing appointment object to the form for pre-filling
        model.addAttribute("appointment", appointmentForm);
        return "appointments_form";
    }

    @PostMapping 
    public String createAppointment(@ModelAttribute AppointmentForm form, // Use @ModelAttribute to bind form fields
                                RedirectAttributes redirectAttributes) {
        // Convert the date string to LocalDateTime if necessary
        LocalDateTime date = LocalDateTime.parse(form.getDate());

        try {
            // Call the service with the data from the form object
            appointmentService.createAppointment(date, form.getDescription());
            redirectAttributes.addFlashAttribute("success", "Appointment created successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Error creating appointment: " + e.getMessage());
        }
        return "redirect:/appointments";
    }

    @PutMapping("/{id}") // Maps to POST /appointments/{id} for updating
    public String updateAppointment(@PathVariable String id,
                                    @ModelAttribute AppointmentForm form,
                                    RedirectAttributes redirectAttributes) {
        try {
            // Convert the date string to LocalDateTime if necessary
            LocalDateTime date = LocalDateTime.parse(form.getDate());

            appointmentService.updateAppointment(id, date, form.getDescription());
            redirectAttributes.addFlashAttribute("success", "Appointment updated successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Error updating appointment: " + e.getMessage());
        }
        return "redirect:/appointments";
    }

    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            appointmentService.deleteAppointment(id);
            redirectAttributes.addFlashAttribute("success", "Appointment deleted successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting appointment: " + e.getMessage());
        }
        return "redirect:/appointments";
    }
}