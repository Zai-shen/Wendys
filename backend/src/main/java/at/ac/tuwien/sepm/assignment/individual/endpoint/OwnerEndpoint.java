package at.ac.tuwien.sepm.assignment.individual.endpoint;

import at.ac.tuwien.sepm.assignment.individual.endpoint.dto.OwnerDto;
import at.ac.tuwien.sepm.assignment.individual.endpoint.mapper.OwnerMapper;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.ServiceException;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(OwnerEndpoint.BASE_URL)
public class OwnerEndpoint {

    static final String BASE_URL = "/owners";
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final OwnerService ownerService;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerEndpoint(OwnerService ownerService, OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    //US-0
    @GetMapping(value = "/{id}")
    public OwnerDto getOneById(@PathVariable("id") Long id) {
        LOGGER.info("Rest: GET " + BASE_URL + "/{}", id);
        try {
            return ownerMapper.entityToDto(ownerService.findOneById(id));
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading owner", e);
        }
    }

    //US-6
    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public OwnerDto postOwner(@RequestBody OwnerDto newOwnerDto) {
        LOGGER.info("Rest: POST " + BASE_URL + "/" + newOwnerDto.toString());
        try {
            Owner newOwnerEntity = ownerMapper.dtoToEntity(newOwnerDto);
            return ownerMapper.entityToDto(ownerService.saveOwnerEntity(newOwnerEntity));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during saving owner: " + e.getMessage(), e);
        }
    }

    //US-7
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OwnerDto putOneById(@PathVariable("id") Long id, @RequestBody OwnerDto updateOwnerDto) {
        LOGGER.info("Rest: PUT " + BASE_URL + "/" + id);
        try {
            Owner updateOwnerEntity = ownerMapper.dtoToEntity(updateOwnerDto);
            return ownerMapper.entityToDto(ownerService.putOneById(id, updateOwnerEntity));
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error during updating owner:" + e.getMessage(), e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during updating owner: " + e.getMessage(), e);
        }
    }

    //US-8
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOneById(@PathVariable("id") Long id) {
        LOGGER.info("Rest: DELETE " + BASE_URL + "/" + id);
        try {
            ownerService.deleteOneById(id);
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during deleting owner with id " + id, e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during deleting owner: " + e.getMessage(), e);
        }
    }

    //US-9
    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<OwnerDto> getAllFiltered(@RequestBody OwnerDto searchOwnerDto) {
        LOGGER.info("Rest: GET ALL FILTERED " + BASE_URL + "/");
        try {
            Owner searchOwnerEntity = ownerMapper.dtoToEntity(searchOwnerDto);
            List<Owner> ownerEntityList = ownerService.findAllFiltered(searchOwnerEntity);
            List<OwnerDto> ownerDtoList = new ArrayList<>();
            for (Owner owner : ownerEntityList) {
                ownerDtoList.add(ownerMapper.entityToDto(owner));
            }
            return ownerDtoList;
        } catch (ServiceException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error during reading all filtered owners", e);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error during reading owner: " + e.getMessage(), e);
        }
    }

}
