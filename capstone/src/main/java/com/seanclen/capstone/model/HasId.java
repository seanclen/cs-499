package com.seanclen.capstone.model;

/**
 * A simple utility interface that indicates an object has an ID.
 * This is typically used for entities that are stored in a repository.
 */
public interface HasId {
    String getId();
    void setId(String id);
}