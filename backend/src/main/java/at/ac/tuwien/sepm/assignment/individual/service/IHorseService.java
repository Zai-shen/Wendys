package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;

public interface IHorseService {


    /**
     * @param id of the horse to find.
     * @return the horse with the specified id.
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the owner could not be found in the system.
     */
    Horse findOneById(Long id);

    //US-1

    /**
     * Saves a given horse to the database.
     *
     * @param horse to be saved.
     * @return the saved horse.
     * @throws ServiceException will be thrown if something goes wrong during data processing.
     */
    public Horse saveHorseEntity(Horse horse) throws ServiceException;
}
