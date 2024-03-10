package com.in2.ahmed.controllers;

import com.in2.ahmed.dto.SpaceshipDTO;
import com.in2.ahmed.services.SpaceshipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpaceshipControllerTest {
    @Mock
    private SpaceshipService spaceshipService;

    @InjectMocks
    private SpaceshipController spaceshipController;

    @Test
    void GivenEmptyListOfSpaceships_WhenRequestingAllSpaceships_ThenReturnEmptyPage() {
        Pageable pageable = Pageable.unpaged();
        when(spaceshipService.getAllSpaceships(pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<SpaceshipDTO> result = spaceshipController.getAllSpaceships(pageable);

        verify(spaceshipService, times(1)).getAllSpaceships(pageable);

        assertEquals(0, result.getTotalElements());
    }

    @Test
    void GivenExistingSpaceshipId_WhenRequestingSpaceshipById_ThenReturnSpaceshipDTO() {
        String id = "123";
        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        when(spaceshipService.getSpaceshipById(id)).thenReturn(spaceshipDTO);

        SpaceshipDTO result = spaceshipController.getSpaceshipById(id);

        verify(spaceshipService, times(1)).getSpaceshipById(id);

        assertEquals(spaceshipDTO, result);
    }

    @Test
    void GivenSpaceshipName_WhenSearchingSpaceshipsByName_ThenReturnEmptyPage() {
        String name = "Falcon";
        Pageable pageable = Pageable.unpaged();
        when(spaceshipService.searchSpaceshipsByName(name, pageable)).thenReturn(new PageImpl<>(Collections.emptyList()));

        Page<SpaceshipDTO> result = spaceshipController.searchSpaceshipsByName(name, pageable);

        verify(spaceshipService, times(1)).searchSpaceshipsByName(name, pageable);

        assertEquals(0, result.getTotalElements());
    }

    @Test
    void GivenSpaceshipDTO_WhenCreatingNewSpaceship_ThenReturnCreatedSpaceshipDTO() {
        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        when(spaceshipService.createSpaceship(spaceshipDTO)).thenReturn(spaceshipDTO);

        SpaceshipDTO result = spaceshipController.createSpaceship(spaceshipDTO);

        verify(spaceshipService, times(1)).createSpaceship(spaceshipDTO);

        assertEquals(spaceshipDTO, result);
    }

    @Test
    void GivenExistingSpaceshipIdAndSpaceshipDTO_WhenUpdatingSpaceship_ThenReturnUpdatedSpaceshipDTO() {
        String id = "123";
        SpaceshipDTO spaceshipDTO = new SpaceshipDTO();
        when(spaceshipService.updateSpaceship(id, spaceshipDTO)).thenReturn(spaceshipDTO);

        SpaceshipDTO result = spaceshipController.updateSpaceship(id, spaceshipDTO);

        verify(spaceshipService, times(1)).updateSpaceship(id, spaceshipDTO);

        assertEquals(spaceshipDTO, result);
    }

    @Test
    void GivenExistingSpaceshipId_WhenDeletingSpaceship_ThenSpaceshipIsDeletedSuccessfully() {
        String id = "123";
        doNothing().when(spaceshipService).deleteSpaceship(id);

        spaceshipController.deleteSpaceship(id);

        verify(spaceshipService, times(1)).deleteSpaceship(id);
    }

}
