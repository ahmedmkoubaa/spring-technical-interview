package com.in2.ahmed.mappers;

import com.in2.ahmed.dto.SpaceshipDTO;
import com.in2.ahmed.model.Spaceship;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Component;

/**
 * Mapper interface for converting Spaceship entities to DTOs and vice versa.
 */
@Component
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public abstract class SpaceshipMapper {

    /**
     * Converts a Spaceship entity to a SpaceshipDTO.
     *
     * @param spaceship the Spaceship entity to convert
     * @return the corresponding SpaceshipDTO
     */
    public abstract SpaceshipDTO spaceshipToDto(Spaceship spaceship);

    /**
     * Converts a SpaceshipDTO to a Spaceship entity.
     *
     * @param dto the SpaceshipDTO to convert
     * @return the corresponding Spaceship entity
     */
    public abstract Spaceship dtoToSpaceship(SpaceshipDTO dto);
}
