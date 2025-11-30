package com.seanclen.capstone.repository;

import com.seanclen.capstone.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    // CRUD methods are provided by MongoRepository
}