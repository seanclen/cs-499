package com.seanclen.capstone.service;

import com.seanclen.capstone.model.Contact;
import com.seanclen.capstone.repository.ContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing contacts.
 * Provides methods to add, retrieve, update, and delete contacts.
 * @author Sean Clendening
 */
@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Retrieve a contact by its ID.
     * @param id the ID of the contact
     * @return the contact with the given ID
     * @throws IllegalArgumentException if the contact is not found
     */
    public Contact getContactById(String id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found with ID: " + id));
    }

    /**
     * Create a new contact with the given attributes.
     * @param firstName the first name of the contact
     * @param lastName the last name of the contact
     * @param phone the phone number of the contact
     * @param address the address of the contact
     * @return the created contact with the MongoDB-assigned ID
     * @throws IllegalArgumentException if any of the attributes are invalid
     */
    @Transactional
    public Contact createContact(String firstName, String lastName, String phone, String address) {
        // Pass 'null' for the ID; MongoDB will auto-generate it.
        Contact contact = new Contact(null, firstName, lastName, phone, address);

        // The Contact constructor will handle validation before creating the object.
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
    @Transactional
    public Contact updateContact(String id, String firstName, String lastName, String phone, String address) {
        // Use Optional to handle missing contact gracefully
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if (contactOptional.isEmpty()) {
            throw new IllegalArgumentException("Contact not found with ID: " + id);
        }

        Contact contact = contactOptional.get();

        // The validation logic is already present in the Contact model's setters.
        // If any of these are invalid, the setters will throw an IllegalArgumentException.
        contact.setFirstName(firstName);;
        contact.setLastName(lastName);
        contact.setPhone(phone);
        contact.setAddress(address);

        return contactRepository.save(contact);
    }

    /**
     * Delete a contact by its ID.
     * @param id the ID of the contact to delete
     * @return true if the contact existed and was deleted
     * @throws IllegalArgumentException if the ID is invalid
     */
    @Transactional
    public boolean deleteContact(String id) {
        if (!contactRepository.existsById(id)) {
            throw new IllegalArgumentException("Contact not found with ID: " + id);
        }

        contactRepository.deleteById(id);
        return true;
    }
}