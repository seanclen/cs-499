package com.seanclen.capstone.model;

import java.util.Date;

/**
 * This class represents an appointment in the scheduling system.
 * It has the following attributes:
 * - id: a unique identifier for the appointment
 * - date: the date of the appointment
 * - time: the time of the appointment
 * - description: a brief description of the appointment
 * @author Sean Clendening
 */
public class Appointment implements HasId {
    private String id;
	private Date date;
	private String description;

	/**
	 * Create a new appointment with the given attributes.
	 * @param id the ID of the appointment
	 * @param date the date of the appointment
	 * @param description the description of the appointment
	 * @throws IllegalArgumentException if any of the attributes are invalid
	 */
	public Appointment(String id, Date date, String description) {
		if (!isValidId(id)) {
			throw new IllegalArgumentException("Invalid ID");
		}

		if (!isValidDate(date)) {
			throw new IllegalArgumentException("Invalid date");
		}

		if (!isValidDescription(description)) {
			throw new IllegalArgumentException("Invalid description");
		}

		this.id = id;
		this.date = date;
		this.description = description;
	}

	/**
	 * Get the ID of the appointment.
	 * @return the ID of the appointment
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get the date of the appointment.
	 * @return the date of the appointment
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Get the description of the appointment.
	 * @return the description of the appointment
	 */
	public String getDescription() {
		return description;
	}

    /**
     * Set the ID of the appointment.
     * @param id the new ID of the appointment
     * @throws IllegalArgumentException if the ID is invalid
     */
    public void setId(String id) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }

        this.id = id;
    }

	/**
	 * Set the date of the appointment.
	 * @param date the new date of the appointment
	 * @throws IllegalArgumentException if the date is invalid
	 */
	public void setDate(Date date) {
		if (!isValidDate(date)) {
			throw new IllegalArgumentException("Invalid date");
		}

		this.date = date;
	}

	/**
	 * Set the description of the appointment.
	 * @param description the new description of the appointment
	 * @throws IllegalArgumentException if the description is invalid
	 */
	public void setDescription(String description) {
		if (!isValidDescription(description)) {
			throw new IllegalArgumentException("Invalid description");
		}

		this.description = description;
	}

	/**
	 * Check if the given ID is valid.
	 * The ID must not be null or empty, and cannot be longer than 10 characters.
	 * @param id the ID to check
	 * @return true if the ID is valid, false otherwise
	 */
	private boolean isValidId(String id) {
		return id != null && !id.isEmpty() && id.length() <= 10;
	}

	/**
	 * Check if the given date is valid.
	 * The date must not be null and must be in the future.
	 * @param date the date to check
	 * @return true if the date is valid, false otherwise
	 */
	public final static boolean isValidDate(Date date) {
		return date != null && date.after(new Date());
	}

	/**
	 * Check if the given description is valid.
	 * The description must not be null or empty, and cannot be longer than 50 characters.
	 * @param description the description to check
	 * @return true if the description is valid, false otherwise
	 */
	public final static boolean isValidDescription(String description) {
		return description != null && !description.isEmpty() && description.length() <= 50;
	}
}