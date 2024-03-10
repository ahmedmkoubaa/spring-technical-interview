package com.in2.ahmed.mappers;


import com.in2.ahmed.dto.SpaceshipDTO;
import com.in2.ahmed.model.Spaceship;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public abstract class SpaceshipMapper {
    public abstract SpaceshipDTO spaceshipToDto(Spaceship spaceship);

    public abstract Spaceship dtoToSpaceship(SpaceshipDTO dto);


}
