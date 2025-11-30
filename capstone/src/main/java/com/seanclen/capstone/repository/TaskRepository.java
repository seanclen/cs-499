package com.seanclen.capstone.repository;

import com.seanclen.capstone.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
    // CRUD methods are provided by MongoRepository
}