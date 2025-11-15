package com.seanclen.capstone.model;

/**
 * This class represents a contact in the address book.
 * It has the following attributes:
 * - id: a unique identifier for the contact
 * - firstName: the first name of the contact
 * - lastName: the last name of the contact
 * - phone: the phone number of the contact
 * - address: the address of the contact
 * @author Sean Clendening
 */
public class Contact implements HasId {
	private String id;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;

	/**
	 * Create a new contact with the given attributes.
	 * @param id the ID of the contact
	 * @param firstName the first name of the contact
	 * @param lastName the last name of the contact
	 * @param phone the phone number of the contact
	 * @param address the address of the contact
	 * @throws IllegalArgumentException if any of the attributes are invalid
	 */
	public Contact(String id, String firstName, String lastName, String phone, String address) {
		if (!isValidId(id)) {
			throw new IllegalArgumentException("Invalid ID");
		}

		if (!isValidFirstName(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}

		if (!isValidLastName(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}

		if (!isValidPhone(phone)) {
			throw new IllegalArgumentException("Invalid phone number");
		}

		if (!isValidAddress(address)) {
			throw new IllegalArgumentException("Invalid address");
		}

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
	}

	/**
	 * Get the ID of the contact.
	 * @return the ID of the contact
	 */
	public String getId() {
		return id;
	}

	/**
	 * Get the first name of the contact.
	 * @return the first name of the contact
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Get the last name of the contact.
	 * @return the last name of the contact
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Get the phone number of the contact.
	 * @return the phone number of the contact
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Get the address of the contact.
	 * @return the address of the contact
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the first name of the contact.
	 * @param firstName
	 * @throws IllegalArgumentException if the first name is invalid
	 */
	public void setFirstName(String firstName) {
		if (!isValidFirstName(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}

		this.firstName = firstName;
	}

	/**
	 * Set the last name of the contact.
	 * @param lastName
	 * @throws IllegalArgumentException if the last name is invalid
	 */
	public void setLastName(String lastName) {
		if (!isValidLastName(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}

		this.lastName = lastName;
	}

	/**
	 * Set the phone number of the contact.
	 * @param phone
	 * @throws IllegalArgumentException if the phone number is invalid
	 */
	public void setPhone(String phone) {
		if (!isValidPhone(phone)) {
			throw new IllegalArgumentException("Invalid phone number");
		}

		this.phone = phone;
	}

	/**
	 * Set the address of the contact.
	 * @param address
	 * @throws IllegalArgumentException if the address is invalid
	 */
	public void setAddress(String address) {
		if (!isValidAddress(address)) {
			throw new IllegalArgumentException("Invalid address");
		}

		this.address = address;
	}

	/**
	 * Check if the ID is valid.
	 * The contact object shall have a required unique contact ID String that cannot be longer than 10 characters. The contact ID shall not be null and shall not be updatable.
	 *
	 * @param id the ID to check
	 * @return true if the ID is valid, false otherwise
	 */
	public final static boolean isValidId(String id) {
		if (id == null || id.trim().length() == 0 || id.trim().length() > 10) {
			return false;
		}

		return true;
	}

	/**
	 * Check if the first name is valid.
	 * The contact object shall have a required firstName String field that cannot be longer than 10 characters. The firstName field shall not be null.
	 *
	 * @param firstName the first name to check
	 * @return true if the first name is valid, false otherwise
	 */
	public final static boolean isValidFirstName(String firstName) {
		if (firstName == null || firstName.trim().length() == 0 || firstName.trim().length() > 10) {
			return false;
		}

		return true;
	}

	/**
	 * Check if the last name is valid.
	 * The contact object shall have a required lastName String field that cannot be longer than 10 characters. The lastName field shall not be null.
	 *
	 * @param lastName the last name to check
	 * @return true if the last name is valid, false otherwise
	 */
	public final static boolean isValidLastName(String lastName) {
		if (lastName == null || lastName.trim().length() == 0 || lastName.trim().length() > 10) {
			return false;
		}

		return true;
	}

	/**
	 * Check if the phone number is valid.
	 * The contact object shall have a required phone String field that must be exactly 10 digits. The phone field shall not be null.
	 *
	 * @param phone the phone number to check
	 * @return true if the phone number is valid, false otherwise
	 */
	public final static boolean isValidPhone(String phone) {
		if (phone == null || phone.trim().length() != 10) {
			return false;
		}

		return true;
	}

	/**
	 * Check if the address is valid.
	 * The contact object shall have a required address field that must be no longer than 30 characters. The address field shall not be null.
	 *
	 * @param address the address to check
	 * @return true if the address is valid, false otherwise
	 */
	public final static boolean isValidAddress(String address) {
		if (address == null || address.trim().length() == 0 || address.trim().length() > 30) {
			return false;
		}

		return true;
	}
}
