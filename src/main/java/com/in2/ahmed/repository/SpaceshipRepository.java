package com.in2.ahmed.repository;

import com.in2.ahmed.model.Spaceship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface SpaceshipRepository extends MongoRepository<Spaceship, String> {

    // Define the method to find spaceships by name containing a certain value
    Page<Spaceship> findByNameContaining(String name, Pageable pageable);
}
