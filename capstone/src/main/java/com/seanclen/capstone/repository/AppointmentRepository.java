package com.seanclen.capstone.repository;

import com.seanclen.capstone.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    // CRUD methods are provided by MongoRepository
}