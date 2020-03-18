package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.HorseDto;
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

    //Isn't really needed
    @GetMapping(value = "/{id}")
    public HorseDto getOneById(@PathVariable("id") Long id) {
        LOGGER.info("GET " + BASE_URL + "/{}", id);
        try {
            return horseMapper.entityToDto(horseService.findOneById(id));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading horse", e);
        }
    }

    //US-1
    @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HorseDto postHorse(@RequestBody HorseDto newHorseDto) {
        LOGGER.info("POST " + BASE_URL + "/" + newHorseDto.toString());
        try {
            Horse newHorseEntity = horseMapper.dtoToEntity(newHorseDto);
            return horseMapper.entityToDto(horseService.saveHorseEntity(newHorseEntity));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during saving horse: " + e.getMessage(), e);
        }
    }

    //US-5
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<HorseDto> getAllFiltered(@RequestBody HorseDto searchHorseDto) { //(@Valid HorseDto searchHorseDto) {
        LOGGER.info("GET ALL FILTERED " + BASE_URL + "/");
        try {
            Horse searchHorseEntity = horseMapper.dtoToEntity(searchHorseDto);
            List<Horse> horseEntityList = horseService.findAllFiltered(searchHorseEntity);
            List<HorseDto> horseDtoList = new ArrayList<>();
            for (Horse horse : horseEntityList) {
                horseDtoList.add(horseMapper.entityToDto(horse));
            }
            return horseDtoList;
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during reading all filtered horses", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading horse: " + e.getMessage(), e);
        }
    }


}
