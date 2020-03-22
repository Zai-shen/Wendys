package at.ac.tuwien.sepm.assignment.individual.util;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
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
            LOGGER.error("Invalid value for ID {}!",ID);
            throw new ValidationException(MessageFormat.format("Invalid value for ID {0}!",ID));
        }
    }

    public void validateNewOwner(Owner owner) throws ValidationException {
        if (owner.getName() == null  || owner.getName().isEmpty()) {
            LOGGER.error("Invalid value for name: {}!",owner.getName());
            throw new ValidationException(MessageFormat.format("Invalid value for name: {0}!",owner.getName()));
        }
        validateIdOfOwnerNotPresent(owner);
        validateCreatedAtOfOwnerNotPresent(owner);
        validateUpdatedAtOfOwnerNotPresent(owner);
    }

    public void validateSearchOwner(Owner owner) throws ValidationException{
        if (owner.getName() != null && owner.getName().isEmpty()){
            LOGGER.error("Invalid value for name: {}!",owner.getName());
            throw new ValidationException(MessageFormat.format("Invalid value for name: {0}!",owner.getName()));
        }
        validateIdOfOwnerNotPresent(owner);
        validateCreatedAtOfOwnerNotPresent(owner);
        validateUpdatedAtOfOwnerNotPresent(owner);
    }

    public void validateNewHorse(Horse horse) throws ValidationException {
        if (horse.getName() == null || horse.getName().isEmpty()) {
            LOGGER.error("Invalid value for name: {}!",horse.getName());
            throw new ValidationException(MessageFormat.format("Invalid value for name: {0}!",horse.getName()));
        }else if (horse.getRating() == null) {
            LOGGER.error("Invalid value for rating: {}!",horse.getRating());
            throw new ValidationException(MessageFormat.format("Invalid value for rating: {0}!",horse.getRating()));
        }else if (horse.getBirthDay() == null) {
            LOGGER.error("Invalid value for birthday: {}!",horse.getBirthDay());
            throw new ValidationException(MessageFormat.format("Invalid value for birthday: {0}!",horse.getBirthDay()));
        }

        validateRating(horse);
        validateBreed(horse);
        validateOwnerId(horse);
    }

    public void validateSearchHorse(Horse horse) throws ValidationException {
        if (horse.getName() != null && horse.getName().isEmpty()) {
            LOGGER.error("Invalid value for name: {}!",horse.getName());
            throw new ValidationException(MessageFormat.format("Invalid value for name: {0}!",horse.getName()));
        }else if (horse.getCreatedAt() != null) {
            LOGGER.error("Invalid value for createdAt: {}!",horse.getCreatedAt());
            throw new ValidationException(MessageFormat.format("Invalid value for createdAt: {0}!",horse.getCreatedAt()));
        }else if (horse.getUpdatedAt() != null) {
            LOGGER.error("Invalid value for updatedAt: {}!",horse.getUpdatedAt());
            throw new ValidationException(MessageFormat.format("Invalid value for updatedAt: {0}!",horse.getUpdatedAt()));
        }else if (horse.getId() != null){
            LOGGER.error("Invalid value for id: {}!",horse.getId());
            throw new ValidationException(MessageFormat.format("Invalid value for id: {0}!",horse.getId()));
        }

        validateRating(horse);
        validateBreed(horse);
    }


    private void validateBreed(Horse horse) throws ValidationException{
        if (horse.getBreed() != null && (
            !horse.getBreed().equalsIgnoreCase("arabian") && !horse.getBreed().equalsIgnoreCase("morgan")
                && !horse.getBreed().equalsIgnoreCase("paint") && !horse.getBreed().equalsIgnoreCase("appaloosa"))){
            LOGGER.error("Invalid value for breed: {}!",horse.getBreed());
            throw new ValidationException(MessageFormat.format("Invalid value for breed: {0}!",horse.getBreed()));
        }
    }

    private void validateRating(Horse horse) throws ValidationException{
        if (horse.getRating() != null && (horse.getRating() < 1 || horse.getRating() > 5)){
            LOGGER.error("Invalid value for rating: {}!",horse.getRating());
            throw new ValidationException(MessageFormat.format("Invalid value for rating: {0}!",horse.getRating()));
        }
    }

    private void validateOwnerId(Horse horse) throws ValidationException{
        if (horse.getOwnerId() != null && horse.getOwnerId() < 1L){
            LOGGER.error("Invalid value for ownerId: {}!",horse.getOwnerId());
            throw new ValidationException(MessageFormat.format("Invalid value for ownerId: {0}!",horse.getOwnerId()));
        }
    }

    private void validateIdOfOwnerNotPresent(Owner owner) throws ValidationException{
        if (owner.getId() != null){
            LOGGER.error("Should be null id: {}!",owner.getId());
            throw new ValidationException(MessageFormat.format("Should be null id: {0}!",owner.getId()));
        }
    }

    private void validateCreatedAtOfOwnerNotPresent(Owner owner) throws ValidationException{
        if (owner.getCreatedAt() != null){
            LOGGER.error("Should be null createdAt: {}!",owner.getCreatedAt());
            throw new ValidationException(MessageFormat.format("Should be null createdAt: {0}!",owner.getCreatedAt()));
        }
    }

    private void validateUpdatedAtOfOwnerNotPresent(Owner owner) throws ValidationException{
        if (owner.getUpdatedAt() != null){
            LOGGER.error("Should be null updatedAt: {}!",owner.getUpdatedAt());
            throw new ValidationException(MessageFormat.format("Should be null updatedAt: {0}!",owner.getUpdatedAt()));
        }
    }

}
