package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.persistence.IHorseDao;
import at.ac.tuwien.sepm.assignment.individual.service.IHorseService;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Service
public class HorseService implements IHorseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final IHorseDao horseDao;
    private final Validator validator;

    @Autowired
    public HorseService(IHorseDao horseDao, Validator validator) {
        this.horseDao = horseDao;
        this.validator = validator;
    }

    //US-0
    @Override
    public Horse findOneById(Long id) {
        LOGGER.trace("Service: findOneById({})", id);
        validator.validateID(id);

        return horseDao.findOneById(id);
    }

    //US-1
    @Override
    public Horse saveHorseEntity(Horse newHorse) throws ServiceException{
        LOGGER.trace("Service: Saving new {}",newHorse.toString());
        validator.validateNewHorse(newHorse);

        try {
            return horseDao.saveHorse(newHorse);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //US-3
    @Override
    public Horse putOneById(Long id, Horse updateHorse) throws ServiceException{
        LOGGER.trace("Service: Put horse with id {}",id);
        validator.validateNewHorse(updateHorse);
        validator.validateID(id);
        try {
            return horseDao.putOneById(id, updateHorse);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //US-4
    @Override
    public void deleteOneById(Long id) throws ServiceException{
        LOGGER.trace("Delete horse with id {}",id);
        validator.validateID(id);
        try {
            horseDao.deleteOneById(id);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    //US-5
    @Override
    public List<Horse> findAllFiltered(Horse searchHorse) throws ServiceException{
        LOGGER.trace("Service: Get all horses filtered by: {}",searchHorse.toString());
        validator.validateSearchHorse(searchHorse);
        try {
            return horseDao.findAllFiltered(searchHorse);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
