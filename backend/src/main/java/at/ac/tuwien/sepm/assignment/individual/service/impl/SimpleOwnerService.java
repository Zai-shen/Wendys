package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
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
    public Owner findOneById(Long id) {
        LOGGER.trace("Service: findOneById({})", id);
        validator.validateID(id);
        return ownerDao.findOneById(id);
    }

    //US-6
    @Override
    public Owner saveOwnerEntity(Owner newOwner) throws ServiceException {
        LOGGER.trace("Service: Saving new {}",newOwner.toString());
        validator.validateNewOwner(newOwner);

        try {
            return ownerDao.saveOwner(newOwner);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
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
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //US-8
    @Override
    public void deleteOneById(Long id) throws ServiceException{
        LOGGER.trace("Delete owner with id {}",id);
        validator.validateID(id);
        try {
            ownerDao.deleteOneById(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //US-9
    @Override
    public List<Owner> findAllFiltered(Owner searchOwner) throws ServiceException{
        LOGGER.trace("Service: Get all owners filtered by: {}",searchOwner.toString());
        //validator.validateNewOwner(searchOwner); TODO
        try {
            return ownerDao.findAllFiltered(searchOwner);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
