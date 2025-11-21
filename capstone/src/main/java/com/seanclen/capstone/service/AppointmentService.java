package com.seanclen.capstone.service;

import com.seanclen.capstone.model.Appointment;
import com.seanclen.capstone.repository.InMemoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
     * @param dateTime the date and time of the appointment
     * @param description the description of the appointment
     * @return the created appointment
     * @throws IllegalArgumentException if any of the attributes are invalid
     */
    public Appointment createAppointment(LocalDateTime dateTime, String description) {
        String id = appointmentRepository.getNextId();
        Appointment appointment = new Appointment(id, dateTime, description);
        return appointmentRepository.save(appointment);
    }

    /**
     * Update an existing appointment with new attributes.
     * @param id the ID of the appointment to update
     * @param dateTime the new date and time of the appointment
     * @param description the new description of the appointment
     * @return the updated appointment
     * @throws IllegalArgumentException if the appointment is not found or any of the new attributes are invalid
     */
    public Appointment updateAppointment(String id, LocalDateTime dateTime, String description) {
        Appointment appointment = appointmentRepository.findById(id);
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment not found");
        }

        // Validate new attributes before updating.
        if (!Appointment.isValidDate(dateTime)) {
            throw new IllegalArgumentException("Invalid date");
        }
        if (!Appointment.isValidDescription(description)) {
            throw new IllegalArgumentException("Invalid description");
        }

        appointment.setDate(dateTime);
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