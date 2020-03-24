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
    public void init() {
        try {
            owner = ownerDao.saveOwner(new Owner("Peter"));
        } catch (Exception e) {
            fail("Exception fired while initializing Owner");
        }
    }


    //US-13 - normal
    @Test
    @DisplayName("Finding owner by existing ID should return correct owner")
    public void findingOwnerById_existing_shouldReturn_CorrectOwner() throws Exception {
        Owner foundOwner = new Owner();
        foundOwner = ownerDao.findOneById(owner.getId());

        assertEquals(owner, foundOwner);
    }

    //negative
    @Test
    @DisplayName("Finding owner by non-existing ID should throw NotFoundException")
    public void findingOwnerById_nonExisting_shouldThrow_NotFoundException() {
        assertThrows(NotFoundException.class,
            () -> ownerDao.findOneById(owner.getId() + 100L));
    }

    //US-13 - normal
    @Test
    @DisplayName("Saving new owner should return valid owner")
    public void saveOwner_shouldReturn_ValidOwner() throws Exception {
        Owner testOwner = new Owner("Petrovic");
        Owner returnedOwner = null;
        returnedOwner = ownerDao.saveOwner(testOwner);

        LocalDateTime now = LocalDateTime.now();
        assertEquals("Petrovic", returnedOwner.getName());
        assertTrue(returnedOwner.getId() > owner.getId());
        assertTrue(returnedOwner.getCreatedAt().compareTo(now) <= 0);
        assertTrue(returnedOwner.getUpdatedAt().compareTo(now) <= 0);
    }

    //US-13 - negative
    @Test
    @DisplayName("Saving owner without name should throw PersistenceException")
    public void saveOwner_nonExistingName_shouldThrow_PersistenceException() {
        assertThrows(PersistenceException.class,
            () -> ownerDao.saveOwner(new Owner()));
    }

    //US-13 - normal
    @Test
    @DisplayName("Updating owner should return updated owner")
    public void putOwnerById_shouldReturn_UpdatedOwner() throws Exception {
        Owner updateOwner = new Owner("Niko");
        Owner returnedOwner = null;
        returnedOwner = ownerDao.putOneById(owner.getId(), updateOwner);

        assertEquals("Niko", returnedOwner.getName());
        assertNotEquals(owner.getUpdatedAt(), returnedOwner.getUpdatedAt());
        assertEquals(owner.getCreatedAt(), returnedOwner.getCreatedAt());
    }

    //US-13 - negative
    @Test
    @DisplayName("Updating non existing owner should throw NotFoundException")
    public void putOwnerById_nonExisting_shouldThrow_NotFoundException() {
        assertThrows(NotFoundException.class,
            () -> ownerDao.putOneById(owner.getId() + 1L, new Owner("test")));
    }

    //US-13 normal
    @Test
    @DisplayName("Delete owner should throw NotFoundException on finding deleted owner")
    public void deleteOwnerById_shouldThrow_NotFoundException_FromFindOneById() throws Exception {
        ownerDao.deleteOneById(owner.getId());

        assertThrows(NotFoundException.class,
            () -> ownerDao.findOneById(owner.getId()));
    }

    //US-13 negative
    @Test
    @DisplayName("Deleting non existing owner should throw NotFoundException")
    public void deleteOwnerById_nonExisting_shouldThrow_NotFoundException() {
        assertThrows(NotFoundException.class,
            () -> ownerDao.findOneById(owner.getId() + 1L));
    }

    //US-13 normal
    @Test
    @DisplayName("Delete owner should throw OwnershipException due to ownership")
    public void deleteOwnerById_shouldThrow_OwnershipException() throws Exception {
        Horse petersHorse = new Horse("Zebra", "Peters horse", 1, LocalDateTime.now(), "paint");
        petersHorse.setOwnerId(owner.getId());
        horseDao.saveHorse(petersHorse);

        assertThrows(OwnershipException.class,
            () -> ownerDao.deleteOneById(owner.getId()));
    }
}
