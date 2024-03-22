package com.in2.ahmed.services;

import com.in2.ahmed.dto.SpaceshipDTO;
import com.in2.ahmed.exceptions.SpaceshipAlreadyExistsException;
import com.in2.ahmed.exceptions.SpaceshipNotFoundException;
import com.in2.ahmed.mappers.SpaceshipMapper;
import com.in2.ahmed.model.Spaceship;
import com.in2.ahmed.repository.SpaceshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service class for performing operations related to Spaceship entities.
 */
@Service
@CacheConfig(cacheNames = "spaceships")
public class SpaceshipService {

    @Autowired
    private SpaceshipRepository spaceshipRepository;

    @Autowired
    private SpaceshipMapper spaceshipMapper;

    /**
     * Retrieves all spaceships from the database.
     *
     * @param pageable pagination information
     * @return a page of SpaceshipDTOs
     */
    @Cacheable
    public Page<SpaceshipDTO> getAllSpaceships(Pageable pageable) {
        Page<Spaceship> spaceships = spaceshipRepository.findAll(pageable);
        return spaceships.map(spaceshipMapper::spaceshipToDto);
    }

    /**
     * Retrieves a spaceship by its ID.
     *
     * @param id the ID of the spaceship to retrieve
     * @return the SpaceshipDTO corresponding to the provided ID
     * @throws SpaceshipNotFoundException if the spaceship with the given ID is not found
     */
    @Cacheable(key = "#id")
    public SpaceshipDTO getSpaceshipById(String id) {
        Spaceship spaceship = spaceshipRepository.findById(id)
                .orElseThrow(() -> new SpaceshipNotFoundException("Spaceship not found with id: " + id));
        return spaceshipMapper.spaceshipToDto(spaceship);
    }

    /**
     * Searches for spaceships by name.
     *
     * @param name     the name to search for
     * @param pageable pagination information
     * @return a page of SpaceshipDTOs matching the provided name
     */
    @Cacheable(key = "#name")
    public Page<SpaceshipDTO> searchSpaceshipsByName(String name, Pageable pageable) {
        Page<Spaceship> spaceships = spaceshipRepository.findByNameContaining(name, pageable);
        return spaceships.map(spaceshipMapper::spaceshipToDto);
    }

    /**
     * Creates a new spaceship.
     *
     * @param spaceshipDTO the DTO representing the spaceship to create
     * @return the created SpaceshipDTO
     * @throws SpaceshipAlreadyExistsException if a spaceship with the same ID already exists
     */
    public SpaceshipDTO createSpaceship(SpaceshipDTO spaceshipDTO) {
        if (spaceshipRepository.existsById(spaceshipDTO.getId())) {
            throw new SpaceshipAlreadyExistsException("Spaceship with ID " + spaceshipDTO.getId() + " already exists.");
        }

        Spaceship spaceship = spaceshipMapper.dtoToSpaceship(spaceshipDTO);
        Spaceship savedSpaceship = spaceshipRepository.save(spaceship);
        return spaceshipMapper.spaceshipToDto(savedSpaceship);
    }

    /**
     * Updates an existing spaceship.
     *
     * @param id           the ID of the spaceship to update
     * @param spaceshipDTO the DTO representing the updated spaceship
     * @return the updated SpaceshipDTO
     * @throws SpaceshipNotFoundException if the spaceship with the given ID is not found
     */
    @CachePut(key = "#id")
    public SpaceshipDTO updateSpaceship(String id, SpaceshipDTO spaceshipDTO) {
        Spaceship existingSpaceship = spaceshipRepository.findById(id)
                .orElseThrow(() -> new SpaceshipNotFoundException("Spaceship not found with id: " + id));

        Spaceship updatedSpaceship = spaceshipMapper.dtoToSpaceship(spaceshipDTO);
        updatedSpaceship.setId(existingSpaceship.getId());

        updatedSpaceship = spaceshipRepository.save(updatedSpaceship);

        return spaceshipMapper.spaceshipToDto(updatedSpaceship);
    }

    /**
     * Deletes a spaceship by its ID.
     *
     * @param id the ID of the spaceship to delete
     */
    @CacheEvict(key = "#id")
    public void deleteSpaceship(String id) {
        spaceshipRepository.deleteById(id);
    }
}
