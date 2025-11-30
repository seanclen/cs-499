package com.seanclen.capstone.controller;

import com.seanclen.capstone.model.Contact;
import com.seanclen.capstone.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestParam String firstName,
                                                 @RequestParam String lastName,
                                                 @RequestParam String phone,
                                                 @RequestParam String address) {
        Contact contact = contactService.createContact(firstName, lastName, phone, address);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id) {
        try {
            Contact contact = contactService.getContactById(id);
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id,
                                                 @RequestParam String firstName,
                                                 @RequestParam String lastName,
                                                 @RequestParam String phone,
                                                 @RequestParam String address) {
        try {
            Contact updatedContact = contactService.updateContact(id, firstName, lastName, phone, address);
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        try {
            contactService.deleteContact(id); 
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }
}