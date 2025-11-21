package com.seanclen.capstone.repository;

import com.seanclen.capstone.model.HasId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple in-memory repository for storing entities that implement the HasId interface.
 * Uses a HashMap for O(1) average-case lookup, insertion, and deletion by ID.
 * This repository provides basic CRUD operations and will eventually be replaced by a database-backed repository.
 */
@Repository
public class InMemoryRepository<T extends HasId> {
    // Use a HashMap to store items, mapping the ID (String) to the entity (T).
    // This allows for O(1) average complexity for findById, save, and deleteById.
    private final HashMap<String, T> items = new HashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    /**
     * Saves the entity. If the entity has an ID, it is updated. If not, a new ID is assigned.
     * @param item The entity to save.
     * @return The saved entity.
     */
    public T save(T item) {
        if (item.getId() == null || item.getId().isEmpty()) {
            item.setId(getNextId()); 
        }

        items.put(item.getId(), item);
        return item;
    }

    /**
     * Finds an entity by its ID.
     * @param id The ID of the entity to find.
     * @return The entity, or null if not found.
     */
    public T findById(String id) {
        return items.get(id);
    }

    /**
     * Finds all entities.
     * @return A List containing all entities.
     */
    public List<T> findAll() {
        // Return a copy to prevent external modification
        return new ArrayList<>(items.values());
    }

    /**
     * Deletes an entity by its ID.
     * @param id The ID of the entity to delete.
     * @return true if the entity was deleted, false otherwise.
     */
    public boolean deleteById(String id) {
        return items.remove(id) != null;
    }

    /**
     * Generates the next sequential ID.
     * @return The next ID as a String.
     */
    public String getNextId() {
        return String.valueOf(idCounter.incrementAndGet());
    }
}