package com.seanclen.capstone.service;

import com.seanclen.capstone.model.Appointment;
import com.seanclen.capstone.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing appointments.
 * Provides methods to add, retrieve, update, and delete appointments.
 * @author Sean Clendening
 */
@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * Retrieve an appointment by its ID.
     * @param id the ID of the appointment
     * @return the appointment with the given ID, or null if not found
     * @throws IllegalArgumentException if the appointment is not found
     */
    public Appointment getAppointmentById(String id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found with ID: " + id));
    }

    /**
     * Create a new appointment with the given attributes.
     * @param dateTime the date and time of the appointment
     * @param description the description of the appointment
     * @return the created appointment
     * @throws IllegalArgumentException if any of the attributes are invalid
     */
    @Transactional
    public Appointment createAppointment(LocalDateTime dateTime, String description) {
        // Pass 'null' for the ID; MongoDB will auto-generate it.
        Appointment appointment = new Appointment(null, dateTime, description);

        // The Appointment constructor will handle validation before creating the object.
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
    @Transactional
    public Appointment updateAppointment(String id, LocalDateTime dateTime, String description) {
        // Use Optional to handle the case where the appointment may not exist
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if (optionalAppointment.isEmpty()) {
            throw new IllegalArgumentException("Appointment not found with ID: " + id);
        }

        Appointment appointment = optionalAppointment.get();

        // Update fields; validation is handled in the Appointment setters.
        appointment.setDate(dateTime);
        appointment.setDescription(description);

        return appointmentRepository.save(appointment);
    }

    /**
     * Delete an appointment by its ID.
     * @param id the ID of the appointment to delete
     * @return true if the appointment was deleted, false otherwise
     * @throws IllegalArgumentException if the ID is invalid
     */
    public boolean deleteAppointment(String id) {
        if (!appointmentRepository.existsById(id)) {
            throw new IllegalArgumentException("Appointment not found with ID: " + id);
        }

        appointmentRepository.deleteById(id);
        return true;
    }
}