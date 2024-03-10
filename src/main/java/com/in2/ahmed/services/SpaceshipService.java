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


@Service
@CacheConfig(cacheNames = "spaceships")
public class SpaceshipService {

    @Autowired
    private SpaceshipRepository spaceshipRepository;

    @Autowired
    private SpaceshipMapper spaceshipMapper;


    // Get all the spaceships using pagination
    @Cacheable
    public Page<SpaceshipDTO> getAllSpaceships(Pageable pageable) {
        Page<Spaceship> spaceships = spaceshipRepository.findAll(pageable);
        return spaceships.map(spaceshipMapper::spaceshipToDto);
    }

    // Get only one spaceship by its id
    @Cacheable(key = "#id")
    public SpaceshipDTO getSpaceshipById(String id) {
        Spaceship spaceship = spaceshipRepository.findById(id)
                .orElseThrow(() -> new SpaceshipNotFoundException("Spaceship not found with id: " + id));
        return spaceshipMapper.spaceshipToDto(spaceship);
    }

    // Get all the spaceships that contain certain value in their name
    @Cacheable(key = "#name")
    public Page<SpaceshipDTO> searchSpaceshipsByName(String name, Pageable pageable) {
        Page<Spaceship> spaceships = spaceshipRepository.findByNameContaining(name, pageable);
        return spaceships.map(spaceshipMapper::spaceshipToDto);
    }

    // Create a new spaceship
    public SpaceshipDTO createSpaceship(SpaceshipDTO spaceshipDTO) {
        // Check if the spaceship already exists in the database
        if (spaceshipRepository.existsById(spaceshipDTO.getId())) {
            throw new SpaceshipAlreadyExistsException("Spaceship with ID " + spaceshipDTO.getId() + " already exists.");
        }

        // Convert DTO to entity
        Spaceship spaceship = spaceshipMapper.dtoToSpaceship(spaceshipDTO);

        // Save the spaceship
        Spaceship savedSpaceship = spaceshipRepository.save(spaceship);

        // Convert entity to DTO and return
        return spaceshipMapper.spaceshipToDto(savedSpaceship);
    }

    // Modify an already existing spaceship
    @CachePut(key = "#id")
    public SpaceshipDTO updateSpaceship(String id, SpaceshipDTO spaceshipDTO) {
        Spaceship existingSpaceship = spaceshipRepository.findById(id)
                .orElseThrow(() -> new SpaceshipNotFoundException("Spaceship not found with id: " + id));

        Spaceship updatedSpaceship = spaceshipMapper.dtoToSpaceship(spaceshipDTO);
        updatedSpaceship.setId(existingSpaceship.getId());

        updatedSpaceship = spaceshipRepository.save(updatedSpaceship);

        return spaceshipMapper.spaceshipToDto(updatedSpaceship);
    }

    // Remove a spaceship
    @CacheEvict(key = "#id")
    public void deleteSpaceship(String id) {
        spaceshipRepository.deleteById(id);
    }

}
