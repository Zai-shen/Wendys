package at.ac.tuwien.sepm.assignment.individual.endpoint.mapper;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import org.springframework.stereotype.Component;

@Component
public class HorseMapper {

    public HorseDto entityToDto(Horse horse) {
        return new HorseDto(horse.getId(), horse.getName(), horse.getDescription(), horse.getRating(), horse.getBirthDay(), horse.getBreed(), horse.getImageURI(), horse.getCreatedAt(), horse.getUpdatedAt());
    }

    //US-1

    public Horse dtoToEntity(HorseDto horseDto) {
        return new Horse(horseDto.getId(), horseDto.getName(), horseDto.getDescription(), horseDto.getRating(), horseDto.getBirthDay(),
            horseDto.getBreed(), horseDto.getImageURI(), horseDto.getCreatedAt(), horseDto.getUpdatedAt());
    }

}
