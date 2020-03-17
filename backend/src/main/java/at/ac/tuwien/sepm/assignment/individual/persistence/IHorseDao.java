package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import org.springframework.dao.DataAccessException;

public interface IHorseDao {

    /**
     * @param id of the horse to find.
     * @return the horse with the specified id.
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the owner could not be found in the database.
     */
    Horse findOneById(Long id);

    //US-1
    /**
     * Saves the given horse to the database.
     * @param horse the horse to be saved.
     * @return the saved instance, including the generated ID.
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     */
    Horse saveHorse(Horse horse) throws PersistenceException;
}
