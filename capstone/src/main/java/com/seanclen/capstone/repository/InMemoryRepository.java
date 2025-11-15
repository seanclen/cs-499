package com.seanclen.capstone.repository;

import com.seanclen.capstone.model.HasId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple in-memory repository for storing entities that implement the HasId interface.
 * This repository provides basic CRUD operations and will eventually be replaced by a database-backed repository.
 */
@Repository
public class InMemoryRepository<T extends HasId> {
    private final List<T> items = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public T save(T item) {
        if (!items.contains(item)) {
            items.add(item);
        }

        return item;
    }

    public T findById(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return item;
            }
        }

        return null;
    }

    public List<T> findAll() {
        // Return a copy to prevent external modification
        return new ArrayList<>(items);
    }

    public boolean deleteById(String id) {
        T item = findById(id);
        if (item != null) {
            return items.remove(item);
        }

        return false;
    }

    public String getNextId() {
        return String.valueOf(idCounter.incrementAndGet());
    }
}