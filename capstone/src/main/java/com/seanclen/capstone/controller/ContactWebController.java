package com.seanclen.capstone.controller;

import com.seanclen.capstone.model.Contact;
import com.seanclen.capstone.form.ContactForm;
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
        model.addAttribute("contact", new ContactForm());
        return "contacts_form";
    }

    @GetMapping("/{id}") // Maps to GET /contacts/{id} for editing
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes redirectAttributes) {
        Contact contact = contactService.getContactById(id);
        if (contact == null) {
            redirectAttributes.addFlashAttribute("error", "Contact not found.");
            return "redirect:/contacts";
        }

        // Map the model Contact to ContactForm
        ContactForm contactForm = new ContactForm();
        contactForm.setFirstName(contact.getFirstName());
        contactForm.setLastName(contact.getLastName());
        contactForm.setPhone(contact.getPhone());
        contactForm.setAddress(contact.getAddress());

        // Pass the existing contact object to the form for pre-filling
        model.addAttribute("contact", contact);
        return "contacts_form";
    }
    
    @PostMapping 
    public String createContact(@ModelAttribute ContactForm form, // Use @ModelAttribute to bind form fields
                                RedirectAttributes redirectAttributes) {
        
        try {
            // Call the service with the data from the form object
            contactService.createContact(form.getFirstName(), form.getLastName(), form.getPhone(), form.getAddress());
            redirectAttributes.addFlashAttribute("message", "Contact created successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", "Error creating contact: " + e.getMessage());
        }
        return "redirect:/contacts"; 
    }
    
    // --- U (Handle Update PUT) - UPDATED BINDING ---
    @PutMapping("/{id}")
    public String updateContact(@PathVariable String id,
                                @ModelAttribute ContactForm form, // Use @ModelAttribute
                                RedirectAttributes redirectAttributes) {
        
        try {
            // Call the service with the data from the form object
            contactService.updateContact(id, form.getFirstName(), form.getLastName(), form.getPhone(), form.getAddress());
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