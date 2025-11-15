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

    /**
     * Retrieve a contact by its ID.
     * @param id the ID of the contact
     * @return the contact with the given ID, or null if not found
     */
    public Contact getContactById(String id) {
        return contactRepository.findById(id);
    }

    /**
     * Create a new contact with the given attributes.
     * @param firstName the first name of the contact
     * @param lastName the last name of the contact
     * @param phone the phone number of the contact
     * @param address the address of the contact
     * @return the created contact
     * @throws IllegalArgumentException if any of the attributes are invalid
     */
    public Contact createContact(String firstName, String lastName, String phone, String address) {
        String id = contactRepository.getNextId();
        Contact contact = new Contact(id, firstName, lastName, phone, address);
        return contactRepository.save(contact);
    }

    /**
     * Update an existing contact with new attributes.
     * @param id the ID of the contact to update
     * @param firstName the new first name of the contact
     * @param lastName the new last name of the contact
     * @param phone the new phone number of the contact
     * @param address the new address of the contact
     * @return the updated contact
     * @throws IllegalArgumentException if the contact is not found or any of the new attributes are invalid
     */
    public Contact updateContact(String id, String firstName, String lastName, String phone, String address) {
        Contact contact = contactRepository.findById(id);
        if (contact == null) {
            throw new IllegalArgumentException("Contact not found");
        }

        // Validate new attributes before updating. All attributes must be valid.
        if (!Contact.isValidFirstName(firstName)) {
            throw new IllegalArgumentException("Invalid first name");
        }
        if (!Contact.isValidLastName(lastName)) {
            throw new IllegalArgumentException("Invalid last name");
        }
        if (!Contact.isValidPhone(phone)) {
            throw new IllegalArgumentException("Invalid phone number");
        }
        if (!Contact.isValidAddress(address)) {
            throw new IllegalArgumentException("Invalid address");
        }

        contact.setFirstName(firstName);;
        contact.setLastName(lastName);
        contact.setPhone(phone);
        contact.setAddress(address);

        return contact;
    }

    /**
     * Delete a contact by its ID.
     * @param id the ID of the contact to delete
     * @return true if the contact was deleted, false otherwise
     */
    public boolean deleteContact(String id) {
        return contactRepository.deleteById(id);
    }
}