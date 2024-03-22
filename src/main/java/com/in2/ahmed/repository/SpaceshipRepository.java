package com.in2.ahmed.repository;

import com.in2.ahmed.model.Spaceship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Repository interface for performing CRUD operations on Spaceship entities.
 */
@Repository
public interface SpaceshipRepository extends MongoRepository<Spaceship, String> {

    /**
     * Finds spaceships by name containing the specified value.
     *
     * @param name     the name value to search for
     * @param pageable the pagination information
     * @return a page of spaceships matching the criteria
     */
    Page<Spaceship> findByNameContaining(String name, Pageable pageable);
}
