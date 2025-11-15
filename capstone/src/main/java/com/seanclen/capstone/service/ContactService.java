package com.seanclen.capstone.service;

import com.seanclen.capstone.model.Contact;
import com.seanclen.capstone.repository.InMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing contacts.
 * Provides methods to add, retrieve, update, and delete contacts.
 * @author Sean Clendening
 */
@Service
public class ContactService {
    private final InMemoryRepository<Contact> contactRepository;

    public ContactService(InMemoryRepository<Contact> contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact getContactById(String id) {
        return contactRepository.findById(id);
    }

    public Contact createContact(String firstName, String lastName, String phone, String address) {
        String id = contactRepository.getNextId();
        Contact contact = new Contact(id, firstName, lastName, phone, address);
        return contactRepository.save(contact);
    }

    public Contact updateContact(String id, String firstName, String lastName, String phone, String address) {
        Contact contact = contactRepository.findById(id);
        if (contact == null) {
            throw new IllegalArgumentException("Contact not found");
        }

        contact.setFirstName(firstName);;
        contact.setLastName(lastName);
        contact.setPhone(phone);
        contact.setAddress(address);

        return contact;
    }

    public boolean deleteContact(String id) {
        return contactRepository.deleteById(id);
    }
}