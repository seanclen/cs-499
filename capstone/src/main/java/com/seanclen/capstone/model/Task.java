package com.seanclen.capstone.model;

/**
 * This class represents a task in the task list.
 * It has the following required attributes:
 * - id: a unique identifier for the task
 * - name: the name or title of the task
 * - description: the description of the task
 * @author Sean Clendening
 */
public class Task implements HasId {
    private String id;
    private String name;
    private String description;

    // Public constants for validation
    public final static int MAX_ID_LENGTH = 10;
    public final static int MAX_NAME_LENGTH = 20;
    public final static int MAX_DESCRIPTION_LENGTH = 50;

    /**
     * Create a new task with the given attributes.
     * @param id the ID of the task
     * @param name the name of the task
     * @param description the description of the task
     * @throws IllegalArgumentException if any of the attributes are invalid
     */
    public Task(String id, String name, String description) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }

        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }

        if (!isValidDescription(description)) {
            throw new IllegalArgumentException("Invalid description");
        }

        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Get the ID of the task.
     * @return the ID of the task
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * Get the name of the task.
     * @return the name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Get the description of the task.
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the ID of the task.
     * @param id the new ID of the task
     * @throws IllegalArgumentException if the ID is invalid
     */
    public void setId(String id) {
        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }

        this.id = id;
    }

    /**
     * Set the name of the task.
     * @param name the new name of the task
     * @throws IllegalArgumentException if the name is invalid
     */
    public void setName(String name) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }

        this.name = name;
    }

    /**
     * Set the description of the task.
     * @param description the new description of the task
     * @throws IllegalArgumentException if the description is invalid
     */
    public void setDescription(String description) {
        if (!isValidDescription(description)) {
            throw new IllegalArgumentException("Invalid description");
        }

        this.description = description;
    }

    /**
     * Validate the ID.
     * @param id the ID to validate
     * @return true if the ID is valid, false otherwise
     */
    public static boolean isValidId(String id) {
        return id != null && !id.isEmpty() && id.length() <= MAX_ID_LENGTH;
    }

    /**
     * Validate the name.
     * @param name the name to validate
     * @return true if the name is valid, false otherwise
     */
    public static boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.length() <= MAX_NAME_LENGTH;
    }

    /**
     * Validate the description.
     * @param description the description to validate
     * @return true if the description is valid, false otherwise
     */
    public static boolean isValidDescription(String description) {
        return description != null && !description.isEmpty() && description.length() <= MAX_DESCRIPTION_LENGTH;
    }
}
