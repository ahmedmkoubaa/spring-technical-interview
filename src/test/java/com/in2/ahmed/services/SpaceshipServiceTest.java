package com.in2.ahmed.services;

import com.in2.ahmed.dto.SpaceshipDTO;
import com.in2.ahmed.exceptions.SpaceshipNotFoundException;
import com.in2.ahmed.mappers.SpaceshipMapper;
import com.in2.ahmed.model.Spaceship;
import com.in2.ahmed.repository.SpaceshipRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpaceshipServiceTest {
    @Mock
    private SpaceshipRepository spaceshipRepository;

    @Mock
    private SpaceshipMapper spaceshipMapper;

    @InjectMocks
    private SpaceshipService spaceshipService;

    @Mock
    private CacheManager cacheManager; // Add cache manager mock


    @Test
    void GivenSpaceshipsInRepository_WhenRequestingAllSpaceships_ThenReturnPageOfSpaceshipDTOs() {
        List<Spaceship> spaceships = new ArrayList<>();
        spaceships.add(new Spaceship());
        Page<Spaceship> page = createPage(spaceships);
        Pageable pageable = Pageable.unpaged();
        when(spaceshipRepository.findAll(pageable)).thenReturn(page);

        Page<SpaceshipDTO> result = spaceshipService.getAllSpaceships(pageable);

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void GivenExistingSpaceshipId_WhenRequestingSpaceshipById_ThenReturnSpaceshipDTO() {
        String id = "123";
        String name = "spaceship";

        Spaceship spaceship = new Spaceship(id, name);
        SpaceshipDTO spaceshipDto = new SpaceshipDTO();
        spaceshipDto.setId(id);

        when(spaceshipRepository.findById(id)).thenReturn(Optional.of(spaceship));
        when(spaceshipMapper.spaceshipToDto(spaceship)).thenReturn(spaceshipDto);

        SpaceshipDTO result = spaceshipService.getSpaceshipById(id);

        assertEquals(spaceship.getId(), result.getId());
    }

    @Test
    void GivenSpaceshipDTO_WhenCreatingSpaceship_ThenReturnCreatedSpaceshipDTOWithId() {
        String id = "id";
        String name = "name";

        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        SpaceshipDTO spaceshipDTOWithId = new SpaceshipDTO();
        spaceshipDTOWithId.setId(id);

        Spaceship spaceship = new Spaceship();
        Spaceship spaceshipWithId = new Spaceship(id, name);

        when(spaceshipMapper.dtoToSpaceship(spaceshipDTO)).thenReturn(spaceship);
        when(spaceshipRepository.save(spaceship)).thenReturn(spaceshipWithId);
        when(spaceshipMapper.spaceshipToDto(spaceshipWithId)).thenReturn(spaceshipDTOWithId);

        // Invoke the service method
        SpaceshipDTO result = spaceshipService.createSpaceship(spaceshipDTO);

        // Assert that the returned DTO has a non-null ID
        assertNotNull(result.getId());
    }


    @Test
    void GivenExistingSpaceshipIdAndSpaceshipDTO_WhenUpdatingSpaceship_ThenReturnUpdatedSpaceshipDTO() {
        String id = "123";
        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        Spaceship existingSpaceship = new Spaceship();
        SpaceshipDTO updatedSpaceship = new SpaceshipDTO();
        updatedSpaceship.setId(id);

        when(spaceshipRepository.findById(id)).thenReturn(Optional.of(existingSpaceship));
        when(spaceshipMapper.dtoToSpaceship(spaceshipDTO)).thenReturn(existingSpaceship);
        when(spaceshipRepository.save(existingSpaceship)).thenReturn(existingSpaceship);
        when(spaceshipMapper.spaceshipToDto(existingSpaceship)).thenReturn(updatedSpaceship);

        SpaceshipDTO result = spaceshipService.updateSpaceship(id, spaceshipDTO);

        assertNotNull(result.getId());
    }


    @Test
    void GivenNonExistingSpaceshipId_WhenRequestingSpaceshipById_ThenThrowSpaceshipNotFoundException() {
        String id = "123";
        when(spaceshipRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(SpaceshipNotFoundException.class, () -> spaceshipService.getSpaceshipById(id));
    }

    @Test
    void GivenSpaceshipName_WhenSearchingSpaceshipsByName_ThenReturnPageOfMatchingSpaceshipDTOs() {
        String name = "Falcon";
        List<Spaceship> spaceships = new ArrayList<>();
        spaceships.add(new Spaceship());
        Page<Spaceship> page = createPage(spaceships);
        Pageable pageable = Pageable.unpaged();
        when(spaceshipRepository.findByNameContaining(name, pageable)).thenReturn(page);

        Page<SpaceshipDTO> result = spaceshipService.searchSpaceshipsByName(name, pageable);

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void GivenNonExistingSpaceshipIdAndSpaceshipDTO_WhenUpdatingSpaceship_ThenThrowSpaceshipNotFoundException() {
        String id = "123";
        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        when(spaceshipRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(SpaceshipNotFoundException.class, () -> spaceshipService.updateSpaceship(id, spaceshipDTO));
    }

    @Test
    void GivenExistingSpaceshipId_WhenDeletingSpaceship_ThenSpaceshipIsDeletedSuccessfully() {
        String id = "123";
        doNothing().when(spaceshipRepository).deleteById(id);

        assertDoesNotThrow(() -> spaceshipService.deleteSpaceship(id));
    }


    private <T> Page<T> createPage(List<T> content) {
        return new PageImpl<>(content);
    }
}
