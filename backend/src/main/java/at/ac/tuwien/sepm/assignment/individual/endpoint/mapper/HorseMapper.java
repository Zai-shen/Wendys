package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.SearchHorseCriteriaDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import org.springframework.stereotype.Component;

@Component
public class HorseMapper {

    public HorseDto entityToDto(Horse horse) {
        return new HorseDto(horse.getId(), horse.getName(), horse.getDescription(), horse.getRating(), horse.getBirthDay(),
            horse.getBreed(), horse.getImageURI(), horse.getOwnerId(), horse.getCreatedAt(), horse.getUpdatedAt());
    }

    public Horse dtoToEntity(HorseDto horseDto) {
        return new Horse(horseDto.getId(), horseDto.getName(), horseDto.getDescription(), horseDto.getRating(), horseDto.getBirthDay(),
            horseDto.getBreed(), horseDto.getImageURI(), horseDto.getOwnerId(), horseDto.getCreatedAt(), horseDto.getUpdatedAt());
    }

    public Horse criteriaDtoToEntity(SearchHorseCriteriaDto shcd){
        return new Horse(shcd.getName(),shcd.getDescription(),shcd.getRating(),shcd.getBirthDay(),shcd.getBreed());
    }
}
