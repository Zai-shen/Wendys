package at.ac.tuwien.sepm.assignment.individual.service.impl;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
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

    @Override
    public Horse findOneById(Long id) {
        LOGGER.trace("findOneById({})", id);
        validator.validateID(id);

        return horseDao.findOneById(id);
    }

    @Override
    public void saveHorseDto(HorseDto horseDto) throws ServiceException{
        LOGGER.info("Service: Saving new horse" + horseDto.toString());
        validator.validateNewHorseDto(horseDto);

        try {
            horseDao.saveOne(horseDto);
        } catch (PersistenceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
