package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.OwnershipException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleOwnerService implements OwnerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final OwnerDao ownerDao;
    private final Validator validator;

    @Autowired
    public SimpleOwnerService(OwnerDao ownerDao, Validator validator) {
        this.ownerDao = ownerDao;
        this.validator = validator;
    }

    @Override
    public Owner findOneById(Long id) throws ServiceException, NotFoundException {
        LOGGER.trace("Service: findOneById({})", id);
        validator.validateID(id);
        try {
            System.out.println("test"+ownerDao.findOneById(id));
            return ownerDao.findOneById(id);
        }catch (PersistenceException e) {
            throw new ServiceException("Error while finding owner by id", e);
        }
    }

    //US-6
    @Override
    public Owner saveOwnerEntity(Owner newOwner) throws ServiceException {
        LOGGER.trace("Service: Saving new {}",newOwner.toString());
        validator.validateNewOwner(newOwner);

        try {
            return ownerDao.saveOwner(newOwner);
        } catch (PersistenceException e) {
            throw new ServiceException("Error while saving owner", e);
        }
    }

    //US-7
    @Override
    public Owner putOneById(Long id, Owner updateOwner) throws ServiceException{
        LOGGER.trace("Service: Put owner with id {}",id);
        validator.validateNewOwner(updateOwner);
        validator.validateID(id);
        try {
            return ownerDao.putOneById(id, updateOwner);
        } catch (PersistenceException e) {
            throw new ServiceException("Error while updating owner", e);
        }
    }

    //US-8
    @Override
    public void deleteOneById(Long id) throws ServiceException, ValidationException{
        LOGGER.trace("Delete owner with id {}",id);
        validator.validateID(id);
        try {
            ownerDao.deleteOneById(id);
        }catch (OwnershipException e){
            throw new ValidationException("Error while deleting owner with horses " + e.getMessage());
        } catch (PersistenceException e) {
            throw new ServiceException("Error while deleting owner", e);
        }
    }

    //US-9
    @Override
    public List<Owner> findAllFiltered(Owner searchOwner) throws ServiceException{
        LOGGER.trace("Service: Get all owners filtered by: {}",searchOwner.toString());
        validator.validateSearchOwner(searchOwner);
        try {
            return ownerDao.findAllFiltered(searchOwner);
        } catch (PersistenceException e) {
            throw new ServiceException("Error while finding all owners", e);
        }
    }
}
