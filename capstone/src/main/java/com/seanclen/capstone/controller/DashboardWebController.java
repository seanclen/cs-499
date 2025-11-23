package com.seanclen.capstone.controller;

import com.seanclen.capstone.service.AppointmentService;
import com.seanclen.capstone.service.ContactService;
import com.seanclen.capstone.service.TaskService;
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
    private final TaskService taskService;

    public DashboardWebController(AppointmentService appointmentService, ContactService contactService, TaskService taskService) {
        this.appointmentService = appointmentService;
        this.contactService = contactService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String getDashboard(Model model) {
        int appointmentCount = appointmentService.getAllAppointments().size();
        model.addAttribute("appointmentCount", appointmentCount);
        
        int contactCount = contactService.getAllContacts().size();
        model.addAttribute("contactCount", contactCount);

        int taskCount = taskService.getAllTasks().size();
        model.addAttribute("taskCount", taskCount);
        
        return "dashboard";
    }
}