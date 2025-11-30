package com.seanclen.capstone.service;

import com.seanclen.capstone.model.Task;
import com.seanclen.capstone.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing tasks.
 * Provides methods to add, retrieve, update, and delete tasks.
 * @author Sean Clendening
 */
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Retrieve a task by its ID.
     * @param id the ID of the task
     * @return the task with the given ID
     * @throws IllegalArgumentException if the task is not found
     */
    public Task getTaskById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found with ID: " + id));
    }

    /**
     * Create a new task with the given attributes.
     * @param name the name of the task
     * @param description the description of the task
     * @return the created task with the MongoDB-assigned ID
     * @throws IllegalArgumentException if any of the attributes are invalid
     */
    @Transactional
    public Task createTask(String name, String description) {
        // Pass 'null' for the ID; MongoDB will auto-generate it.
        Task task = new Task(null, name, description);

        // The Task constructor will handle validation before creating the object.
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
    @Transactional
    public Task updateTask(String id, String name, String description) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException("Task not found with ID: " + id);
        }

        Task task = optionalTask.get();

        // The validation is handled in the setters.
        task.setName(name);
        task.setDescription(description);
        
        return taskRepository.save(task);
    }

    /**
     * Delete a task by its ID.
     * @param id the ID of the task to delete
     * @throws IllegalArgumentException if the task is not found
     */
    public boolean deleteTask(String id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task not found with ID: " + id);
        }

        taskRepository.deleteById(id);
        return true;
    }
}