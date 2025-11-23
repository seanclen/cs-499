package com.seanclen.capstone.service;

import com.seanclen.capstone.model.Task;
import com.seanclen.capstone.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing tasks.
 * Provides methods to add, retrieve, update, and delete tasks.
 * @author Sean Clendening
 */
@Service
public class TaskService {
    private final TaskRepository<Task> taskRepository;

    public TaskService(TaskRepository<Task> taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retrieve a task by its ID.
     * @param id the ID of the task
     * @return the task with the given ID, or null if not found
     */
    public Task getTaskById(String id) {
        return taskRepository.findById(id);
    }

    /**
     * Create a new task with the given attributes.
     * @param name the name of the task
     * @param description the description of the task
     * @return the created task
     * @throws IllegalArgumentException if any of the attributes are invalid
     */
    public Task createTask(String name, String description) {
        String id = taskRepository.getNextId();
        Task task = new Task(id, name, description);
        return taskRepository.save(task);
    }

    /**
     * Update an existing task with new attributes.
     * @param id the ID of the task to update
     * @param name the new name of the task
     * @param description the new description of the task
     * @return the updated task
     * @throws IllegalArgumentException if the task is not found or any of the new attributes are invalid
     */
    public Task updateTask(String id, String name, String description) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new IllegalArgumentException("Task not found");
        }

        // Validate new attributes
        if (!Task.isValidName(name)) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (!Task.isValidDescription(description)) {
            throw new IllegalArgumentException("Invalid description");
        }

        task.setName(name);
        task.setDescription(description);
        
        return task;
    }

    /**
     * Delete a task by its ID.
     * @param id the ID of the task to delete
     * @throws IllegalArgumentException if the task is not found
     */
    public boolean deleteTask(String id) {
        return taskRepository.deleteById(id);
    }
}