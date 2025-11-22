package com.seanclen.capstone.controller;

import com.seanclen.capstone.service.AppointmentService;
import com.seanclen.capstone.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Web controller for the dashboard page.
 * Handles requests to the dashboard and populates the model with necessary data.
 * @author Sean Clendening
 */
@Controller
public class DashboardWebController {
    private final AppointmentService appointmentService;
    private final ContactService contactService;

    public DashboardWebController(AppointmentService appointmentService, ContactService contactService) {
        this.appointmentService = appointmentService;
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String getDashboard(Model model) {
        int appointmentCount = appointmentService.getAllAppointments().size();
        model.addAttribute("appointmentCount", appointmentCount);
        
        int contactCount = contactService.getAllContacts().size();
        model.addAttribute("contactCount", contactCount);
        
        return "dashboard";
    }
}