package at.ac.tuwien.sepm.assignment.individual.unit.persistence;

import static org.junit.jupiter.api.Assertions.*;

import at.ac.tuwien.sepm.assignment.individual.entity.Horse;
import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.exception.OwnershipException;
import at.ac.tuwien.sepm.assignment.individual.exception.PersistenceException;
import at.ac.tuwien.sepm.assignment.individual.persistence.IHorseDao;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public abstract class OwnerDaoTestBase {

    @Autowired
    OwnerDao ownerDao;

    @Autowired
    IHorseDao horseDao;

    private Owner owner;

    @BeforeEach
    @DisplayName("Initialization")
    public void init(){
        try {
        owner = ownerDao.saveOwner(new Owner("Peter"));
        }catch(Exception e){
            fail("Exception fired while saving Owner");
        }
    }

    //US-13 - normal
    @Test
    @DisplayName("Finding owner by existing ID should return correct owner")
    public void findingOwnerById_existing_shouldReturnCorrectOwner(){
        Owner foundOwner = new Owner();
        try {
            foundOwner = ownerDao.findOneById(owner.getId());
        }catch (PersistenceException e){
            fail("Exception fired while saving Owner");
        }
        assertEquals(owner, foundOwner);
    }

    //negative
    @Test
    @DisplayName("Finding owner by non-existing ID should throw NotFoundException")
    public void findingOwnerById_nonExisting_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class,
            () -> ownerDao.findOneById(owner.getId()+100L));
    }


    //US-13 - normal
    @Test
    @DisplayName("Saving new owner should return valid owner")
    public void saveOwner_shouldReturnValidOwner(){
        Owner testOwner = new Owner("Petrovic");
        Owner returnedOwner = null;
        try {
            returnedOwner = ownerDao.saveOwner(testOwner);
        }catch(Exception e){
            fail("Exception fired while saving Owner");
        }
        LocalDateTime now = LocalDateTime.now();
        assertEquals("Petrovic", returnedOwner.getName());
        assertTrue(returnedOwner.getId() > owner.getId());
        assertTrue(returnedOwner.getCreatedAt().compareTo(now) <= 0);
        assertTrue(returnedOwner.getUpdatedAt().compareTo(now) <= 0);
    }


    //US-13 - negative
    @Test
    @DisplayName("Saving owner without name should throw PersistenceException")
    public void saveOwner_nonExistingName_shouldThrowPersistenceException(){
        assertThrows(PersistenceException.class,
            () -> ownerDao.saveOwner(new Owner()));
    }


    //US-13 - normal
    @Test
    @DisplayName("Updating owner should return updated owner")
    public void putOwnerById_shouldReturnUpdatedOwner(){
        Owner updateOwner = new Owner("Niko");
        Owner returnedOwner = null;
        try {
            returnedOwner = ownerDao.putOneById(owner.getId(), updateOwner);
        }catch(Exception e){
            fail("Exception fired while updating Owner");
        }
        assertEquals("Niko", returnedOwner.getName());
        assertNotEquals(owner.getUpdatedAt(),returnedOwner.getUpdatedAt());
        assertEquals(owner.getCreatedAt(),returnedOwner.getCreatedAt());
    }

    //US-13 - negative
    @Test
    @DisplayName("Updating non existing owner should throw NotFoundException")
    public void putOwnerById_nonExisting_shouldThrowNotFoundException(){
        assertThrows(NotFoundException.class,
            () -> ownerDao.putOneById( owner.getId()+1L,new Owner("test")));
    }

    //US-13 normal
    @Test
    @DisplayName("Delete owner should throw NotFoundException on finding deleted owner")
    public void deleteOwnerById_shouldThrowNotFoundExceptionFromFindOneById(){
        try {
            ownerDao.deleteOneById(owner.getId());
        }catch (Exception e){
            fail("Exception fired while deleting Owner");
        }

        assertThrows(NotFoundException.class,
            () -> ownerDao.findOneById(owner.getId()));
    }

    //US-13 negative
    @Test
    @DisplayName("Deleting non existing owner should throw NotFoundException")
    public void deleteOwnerById_nonExisting_shouldThrowNotFoundException(){
        assertThrows(NotFoundException.class,
            () -> ownerDao.findOneById(owner.getId()+1L));
    }

    //US-13 normal
    @Test
    @DisplayName("Delete owner should throw OwnershipException due to ownership")
    public void deleteOwnerById_shouldThrowOwnershipException(){
        try {
            Horse petersHorse = new Horse("Zebra","Peters horse",1,LocalDateTime.now(),"paint");
            petersHorse.setOwnerId(owner.getId());
            horseDao.saveHorse(petersHorse);
        }catch (Exception e){
            fail("Exception fired while deleting Owner");
        }

        assertThrows(OwnershipException.class,
            () -> ownerDao.deleteOneById(owner.getId()));
    }
}
