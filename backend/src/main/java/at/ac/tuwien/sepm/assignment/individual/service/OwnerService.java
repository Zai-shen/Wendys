package at.ac.tuwien.sepm.assignment.individual.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;

import java.util.List;

public interface OwnerService {

    //US-0
    /**
     * @param id of the owner to find.
     * @return the owner with the specified id.
     * @throws RuntimeException  will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the owner could not be found in the system.
     */
    Owner findOneById(Long id) throws NotFoundException, ServiceException;

    //US-6
    /**
     * Saves a given owner to the database.
     *
     * @param newOwner to be saved.
     * @return the saved owner.
     * @throws ServiceException will be thrown if something goes wrong during data processing.
     */
    public Owner saveOwnerEntity(Owner newOwner) throws ServiceException;

    //US-7
    /**
     * Changes the owner with selected id by the parameters set in owner.
     * @param id of the owner to update.
     * @param updateOwner parameters to update.
     * @return the updated owner.
     * @throws ServiceException will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the owner could not be found in the system.
     */
    Owner putOneById(Long id, Owner updateOwner) throws ServiceException;

    //US-8
    /**
     * Deletes the owner with selected id from the database.
     * @param id of the owner, which should get deleted.
     * @throws ServiceException will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the owner could not be found in the system.
     */
    void deleteOneById(Long id) throws ServiceException, NotFoundException;

    //US-9
    /**
     * Finds owners by filter, or if no filter is set all owners in the database.
     * @param searchOwner the parameters to be filtered by.
     * @return the owners, which fit the filter.
     * @throws ServiceException will be thrown if something goes wrong during data processing.
     * @throws NotFoundException will be thrown if the owners could not be found in the system.
     */
    List<Owner> findAllFiltered(Owner searchOwner) throws ServiceException, NotFoundException;
}
