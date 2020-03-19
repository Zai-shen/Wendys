package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;

import java.util.List;

public interface IHorseService {

    //US-0
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
     * @param newHorse to be saved.
     * @return the saved horse.
     * @throws ServiceException will be thrown if something goes wrong during data processing.
     */
    public Horse saveHorseEntity(Horse newHorse) throws ServiceException;

    //US-3
    /**
     * Changes the horse with selected id by the parameters set in horse.
     * @param id of the horse to update.
     * @param updateHorse parameters to update.
     * @return the updated horse.
     * @throws ServiceException will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the horse could not be found in the system.
     */
    Horse putOneById(Long id, Horse updateHorse) throws ServiceException;

    //US-4
    /**
     * Deletes the horse with selected id from the database.
     * @param id of the horse, which should get deleted.
     * @throws ServiceException will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the horse could not be found in the system.
     */
    void deleteOneById(Long id) throws ServiceException, NotFoundException;

    //US-5
    /**
     * Finds horses by filter, or if no filter is set all horses in the database.
     * @param searchHorse the parameters to be filtered by.
     * @return the horses, which fit the filter.
     * @throws ServiceException will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the horses could not be found in the system.
     */
    List<Horse> findAllFiltered(Horse searchHorse) throws ServiceException, NotFoundException;
}
