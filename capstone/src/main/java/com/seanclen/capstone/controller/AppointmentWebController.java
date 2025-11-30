package com.seanclen.capstone.controller;

import com.seanclen.capstone.model.Appointment;
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
        model.addAttribute("appointment", new Appointment());
        return "appointments_form";
    }

    @GetMapping("/{id}") // Maps to GET /appointments/{id} for editing
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            
            // Pass the Appointment entity directly to the model
            model.addAttribute("appointment", appointment); 
            return "appointments_form";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Appointment not found: " + e.getMessage());
            return "redirect:/appointments";
        }
    }

    @PostMapping 
    public String createAppointment(@ModelAttribute Appointment appointment,
                                RedirectAttributes redirectAttributes) {
        try {
            // Call the service with the data from the appointment object
            appointmentService.createAppointment(appointment.getDate(), appointment.getDescription());
            redirectAttributes.addFlashAttribute("success", "Appointment created successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Error creating appointment: " + e.getMessage());
        }
        return "redirect:/appointments";
    }

    @PutMapping("/{id}") // Maps to POST /appointments/{id} for updating
    public String updateAppointment(@PathVariable String id,
                                    @ModelAttribute Appointment appointment,
                                    RedirectAttributes redirectAttributes) {
        try {
            appointmentService.updateAppointment(id, appointment.getDate(), appointment.getDescription());
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