package com.in2.ahmed.controllers;

import com.in2.ahmed.dto.SpaceshipDTO;
import com.in2.ahmed.services.SpaceshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spaceships")
public class SpaceshipController {

    @Autowired
    private SpaceshipService spaceshipService;

    // Get all the spaceships using pagination
    @GetMapping
    public Page<SpaceshipDTO> getAllSpaceships(Pageable pageable) {
        return spaceshipService.getAllSpaceships(pageable);
    }

    // Get only one spaceship by its id
    @GetMapping("/{id}")
    public SpaceshipDTO getSpaceshipById(@PathVariable String id) {
        return spaceshipService.getSpaceshipById(id);
    }

    // Get all the spaceships that contain certain value in their name
    @GetMapping("/search")
    public Page<SpaceshipDTO> searchSpaceshipsByName(@RequestParam String name, Pageable pageable) {
        return spaceshipService.searchSpaceshipsByName(name, pageable);
    }

    // Create a new spaceship
    @PostMapping
    public SpaceshipDTO createSpaceship(@RequestBody SpaceshipDTO spaceshipDTO) {
        return spaceshipService.createSpaceship(spaceshipDTO);
    }

    // Modify an already existing spaceship
    @PutMapping("/{id}")
    public SpaceshipDTO updateSpaceship(@PathVariable String id, @RequestBody SpaceshipDTO spaceshipDTO) {
        return spaceshipService.updateSpaceship(id, spaceshipDTO);
    }

    // Remove a spaceship
    @DeleteMapping("/{id}")
    public void deleteSpaceship(@PathVariable String id) {
        spaceshipService.deleteSpaceship(id);
    }
}
