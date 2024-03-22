package com.in2.ahmed.controllers;

import com.in2.ahmed.dto.SpaceshipDTO;
import com.in2.ahmed.services.SpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for managing spaceships.
 */
@RestController
@RequestMapping("/spaceships")
public class SpaceshipController {

    @Autowired
    private SpaceshipService spaceshipService;

    /**
     * Retrieves all spaceships using pagination.
     *
     * @param pageable Pagination information.
     * @return A page of SpaceshipDTO objects.
     */
    @GetMapping
    public Page<SpaceshipDTO> getAllSpaceships(Pageable pageable) {
        return spaceshipService.getAllSpaceships(pageable);
    }

    /**
     * Retrieves a spaceship by its ID.
     *
     * @param id The ID of the spaceship.
     * @return The SpaceshipDTO object.
     */
    @GetMapping("/{id}")
    public SpaceshipDTO getSpaceshipById(@PathVariable String id) {
        return spaceshipService.getSpaceshipById(id);
    }

    /**
     * Searches for spaceships by name.
     *
     * @param name     The name to search for.
     * @param pageable Pagination information.
     * @return A page of SpaceshipDTO objects matching the search criteria.
     */
    @GetMapping("/search")
    public Page<SpaceshipDTO> searchSpaceshipsByName(@RequestParam String name, Pageable pageable) {
        return spaceshipService.searchSpaceshipsByName(name, pageable);
    }

    /**
     * Creates a new spaceship.
     *
     * @param spaceshipDTO The SpaceshipDTO object representing the new spaceship.
     * @return The created SpaceshipDTO object.
     */
    @PostMapping
    public SpaceshipDTO createSpaceship(@RequestBody SpaceshipDTO spaceshipDTO) {
        return spaceshipService.createSpaceship(spaceshipDTO);
    }

    /**
     * Updates an existing spaceship.
     *
     * @param id           The ID of the spaceship to update.
     * @param spaceshipDTO The updated SpaceshipDTO object.
     * @return The updated SpaceshipDTO object.
     */
    @PutMapping("/{id}")
    public SpaceshipDTO updateSpaceship(@PathVariable String id, @RequestBody SpaceshipDTO spaceshipDTO) {
        return spaceshipService.updateSpaceship(id, spaceshipDTO);
    }

    /**
     * Deletes a spaceship by its ID.
     *
     * @param id The ID of the spaceship to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteSpaceship(@PathVariable String id) {
        spaceshipService.deleteSpaceship(id);
    }
}
