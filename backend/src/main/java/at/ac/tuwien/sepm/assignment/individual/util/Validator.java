package at.ac.tuwien.sepm.assignment.individual.util;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;

@Component
public class Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public void validateID(Long ID) throws ValidationException{
        if (ID < 1L) {
            LOGGER.error("Error during validating ID");
            throw new IllegalArgumentException(MessageFormat.format("Invalid value for ID {0}!",ID));
        }
    }

    public void validateNewOwner(Owner owner) throws ValidationException {
    }

    public void validateUpdateOwner(Owner owner) throws ValidationException {
    }

    public void validateNewHorseDto(HorseDto horseDto) throws ValidationException {
        if (horseDto.getName() == null) {
            LOGGER.error("Error during validating horseDto->name");
            throw new IllegalArgumentException(MessageFormat.format("Invalid value for name: {0}!",horseDto.getName()));
        }else if (horseDto.getRating() == null) {
            LOGGER.error("Error during validating horseDto->rating");
            throw new IllegalArgumentException(MessageFormat.format("Invalid value for rating: {0}!",horseDto.getRating()));
        }else if (horseDto.getBirthDay() == null) {
            LOGGER.error("Error during validating horseDto->birthDay");
            throw new IllegalArgumentException(MessageFormat.format("Invalid value for birthday: {0}!",horseDto.getBirthDay()));
        }

        if (horseDto.getRating() < 1 || horseDto.getRating() > 5){
            LOGGER.error("Error during validating horseDto->rating: " + horseDto.getRating());
            throw new IllegalArgumentException(MessageFormat.format("Invalid value for rating: {0}!",horseDto.getRating()));
        }

        if (!horseDto.getBreed().equalsIgnoreCase("arabian") && !horseDto.getBreed().equalsIgnoreCase("morgan")
            && !horseDto.getBreed().equalsIgnoreCase("paint") && !horseDto.getBreed().equalsIgnoreCase("appaloosa")){
            LOGGER.error("Error during validating horseDto->breed: " + horseDto.getBreed());
            throw new IllegalArgumentException(MessageFormat.format("Invalid value for breed: {0}!",horseDto.getBreed()));
        }

        ///TODO: more
    }


}
