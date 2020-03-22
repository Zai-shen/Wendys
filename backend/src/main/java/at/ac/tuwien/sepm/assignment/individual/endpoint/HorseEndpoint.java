package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.SearchHorseCriteriaDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.HorseMapper;
import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.service.IHorseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(HorseEndpoint.BASE_URL)
public class HorseEndpoint {

    static final String BASE_URL = "/horses";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final IHorseService horseService;
    private final HorseMapper horseMapper;

    @Autowired
    public HorseEndpoint(IHorseService horseService, HorseMapper horseMapper) {
        this.horseService = horseService;
        this.horseMapper = horseMapper;
    }

    //US-0
    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HorseDto getOneById(@PathVariable("id") Long id) {
        LOGGER.info("Rest: GET " + BASE_URL + "/{}", id);
        try {
            return horseMapper.entityToDto(horseService.findOneById(id));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during reading horse", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error finding horse", e);
        }
    }

    //US-1
    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public HorseDto postHorse(@RequestBody HorseDto newHorseDto) {
        LOGGER.info("Rest: POST " + BASE_URL + "/" + newHorseDto.toString());
        try {
            Horse newHorseEntity = horseMapper.dtoToEntity(newHorseDto);
            return horseMapper.entityToDto(horseService.saveHorseEntity(newHorseEntity));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during saving horse", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error finding saved horse", e);
        }
    }

    //US-3
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HorseDto putOneById(@PathVariable("id") Long id, @RequestBody HorseDto updateHorseDto) {
        LOGGER.info("Rest: PUT " + BASE_URL + "/" + id);
        try {
            Horse updateHorseEntity = horseMapper.dtoToEntity(updateHorseDto);
            return horseMapper.entityToDto(horseService.putOneById(id, updateHorseEntity));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during updating horse", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error finding updated horse", e);
        }
    }

    //US-4
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOneById(@PathVariable("id") Long id) {
        LOGGER.info("Rest: DELETE " + BASE_URL + "/" + id);
        try {
            horseService.deleteOneById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during deleting horse", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error finding horse to delete", e);
        }
    }

    //US-5
    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<HorseDto> getAllFiltered(@Valid SearchHorseCriteriaDto searchHorseCriteriaDto) {
        LOGGER.info("Rest: GET ALL FILTERED " + BASE_URL + "/");
        try {
            Horse searchHorseEntity = horseMapper.criteriaDtoToEntity(searchHorseCriteriaDto);
            List<Horse> horseEntityList = horseService.findAllFiltered(searchHorseEntity);
            List<HorseDto> horseDtoList = new ArrayList<>();
            for (Horse horse : horseEntityList) {
                horseDtoList.add(horseMapper.entityToDto(horse));
            }
            return horseDtoList;
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during reading all (filtered) horses", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error finding any (filtered) horses", e);
        }
    }
}
