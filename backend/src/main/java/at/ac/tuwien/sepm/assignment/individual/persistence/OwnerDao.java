package at.ac.tuwien.sepm.assignment.individual.persistence;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface OwnerDao {

    //US-0
    /**
     * @param id of the owner to find.
     * @return the owner with the specified id.
     * @throws DataAccessException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException   will be thrown if the owner could not be found in the database.
     */
    Owner findOneById(Long id) throws PersistenceException, NotFoundException;

    //US-6
    /**
     * Saves the given owner to the database.
     * @param newOwner the owner to be saved.
     * @return the saved instance, including the generated ID.
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     */
    Owner saveOwner(Owner newOwner) throws PersistenceException, NotFoundException;

    //US-7
    /**
     * Updates the owner with id by parameter owner.
     * @param id of the owner to be updated.
     * @param updateOwner parameters to be set.
     * @return the changed owner.
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException    will be thrown if the owners could not be found in the database.
     */
    Owner putOneById(Long id, Owner updateOwner) throws PersistenceException, NotFoundException;

    //US-8
    /**
     * Deletes the owner with id from the database.
     * @param id of the owner to be deleted.
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException    will be thrown if the owners could not be found in the database.
     */
    void deleteOneById(Long id) throws PersistenceException, NotFoundException;

    //US-9
    /**
     * Finds all owners by filter, or if no filter is set all owners in the database.
     * @param searchOwner the parameters to be filtered by.
     * @return the filtered owners.
     * @throws PersistenceException will be thrown if something goes wrong during the database access.
     * @throws NotFoundException    will be thrown if the owners could not be found in the database.
     */
    List<Owner> findAllFiltered(Owner searchOwner) throws PersistenceException, NotFoundException;
}
