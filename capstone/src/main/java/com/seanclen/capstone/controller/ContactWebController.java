package com.seanclen.capstone.controller;

import com.seanclen.capstone.model.Contact;
import com.seanclen.capstone.service.ContactService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contacts")
public class ContactWebController {

    private final ContactService contactService;

    public ContactWebController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts());
        return "contacts"; 
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contacts_form";
    }

    @GetMapping("/{id}") // Maps to GET /contacts/{id} for editing
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Contact contact = contactService.getContactById(id);
            
            // Pass the Contact entity directly to the model
            model.addAttribute("contact", contact); 
            return "contacts_form";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Contact not found: " + e.getMessage());
            return "redirect:/contacts";
        }
    }
    
    @PostMapping 
    public String createContact(@ModelAttribute Contact contact,
                                RedirectAttributes redirectAttributes) {
        
        try {
            // Call the service with the data from the form object
            contactService.createContact(contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getAddress());
            redirectAttributes.addFlashAttribute("message", "Contact created successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Error creating contact: " + e.getMessage());
        }
        return "redirect:/contacts"; 
    }
    
    @PutMapping("/{id}")
    public String updateContact(@PathVariable String id,
                                @ModelAttribute Contact contact,
                                RedirectAttributes redirectAttributes) {
        
        try {
            contactService.updateContact(id, contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getAddress());
            redirectAttributes.addFlashAttribute("message", "Contact updated successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Error updating contact: " + e.getMessage());
        }
        return "redirect:/contacts";
    }

    @DeleteMapping("/{id}")
    public String deleteContact(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            contactService.deleteContact(id);
            redirectAttributes.addFlashAttribute("message", "Contact deleted successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Contact not found.");
        }
        return "redirect:/contacts";
    }
}