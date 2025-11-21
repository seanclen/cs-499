package com.seanclen.capstone.service;

import com.seanclen.capstone.model.Appointment;
import com.seanclen.capstone.repository.InMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Service class for managing appointments.
 * Provides methods to add, retrieve, update, and delete appointments.
 * @author Sean Clendening
 */
@Service
public class AppointmentService {
    private final InMemoryRepository<Appointment> appointmentRepository;

    public AppointmentService(InMemoryRepository<Appointment> appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * Retrieve an appointment by its ID.
     * @param id the ID of the appointment
     * @return the appointment with the given ID, or null if not found
     */
    public Appointment getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }

    /**
     * Create a new appointment with the given attributes.
     * @param date the date of the appointment
     * @param description the description of the appointment
     * @return the created appointment
     * @throws IllegalArgumentException if any of the attributes are invalid
     */
    public Appointment createAppointment(Date date, String description) {
        String id = appointmentRepository.getNextId();
        Appointment appointment = new Appointment(id, date, description);
        return appointmentRepository.save(appointment);
    }

    /**
     * Update an existing appointment with new attributes.
     * @param id the ID of the appointment to update
     * @param date the new date of the appointment
     * @param description the new description of the appointment
     * @return the updated appointment
     * @throws IllegalArgumentException if the appointment is not found or any of the new attributes are invalid
     */
    public Appointment updateAppointment(String id, Date date, String description) {
        Appointment appointment = appointmentRepository.findById(id);
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment not found");
        }

        // Validate new attributes before updating.
        if (!Appointment.isValidDate(date)) {
            throw new IllegalArgumentException("Invalid date");
        }
        if (!Appointment.isValidDescription(description)) {
            throw new IllegalArgumentException("Invalid description");
        }

        appointment.setDate(date);
        appointment.setDescription(description);
        return appointmentRepository.save(appointment);
    }

    /**
     * Delete an appointment by its ID.
     * @param id the ID of the appointment to delete
     * @return true if the appointment was deleted, false otherwise
     */
    public boolean deleteAppointment(String id) {
        return appointmentRepository.deleteById(id);
    }
}