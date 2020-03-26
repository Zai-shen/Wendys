package at.ac.tuwien.sepm.assignment.individual.unit.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.exception.NotFoundException;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
import at.ac.tuwien.sepm.assignment.individual.util.ValidationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class SimpleOwnerServiceTest {

    @Autowired
    OwnerService ownerService;

    //US-13 normal
    @Test
    @DisplayName("Find owner by invalid ID should return Owner.class Object")
    public void findingOwnerById_existing_shouldReturn_ownerClassObject() throws Exception{
        Owner owner = ownerService.findOneById(1L);
        assertEquals(Owner.class,owner.getClass());
    }

    //US-13 negative
    @Test
    @DisplayName("Find owner by non existing ID should throw NotFoundException")
    public void findingOwnerById_nonExisting_shouldThrow_NotFoundException() throws Exception{
        assertThrows(NotFoundException.class,
            () -> ownerService.findOneById(10000L));
    }

    //US-13 negative
    @Test
    @DisplayName("Find owner by invalid ID should throw ValidationException")
    public void findingOwnerBy_invalidId_shouldThrow_ValidationException() throws Exception{
        assertThrows(ValidationException.class,
            () -> ownerService.findOneById(0L));
    }

    //US-13 normal
    @Test
    @DisplayName("Post valid owner should return same owner")
    public void postOwner_valid_shouldReturn_sameOwner() throws Exception{
        Owner owner = ownerService.saveOwnerEntity(new Owner("Peter"));
        assertEquals(Owner.class,owner.getClass());
        assertEquals("Peter",owner.getName());
    }

    //US-13 negative
    @Test
    @DisplayName("Post invalid owner should throw ValidationException")
    public void postOwner_invalid_shouldThrow_ValidationException() throws Exception{
        assertThrows(ValidationException.class,
            () -> ownerService.saveOwnerEntity(new Owner()));
    }

    //US-13 normal
    @Test
    @DisplayName("Put valid owner to existing id should return same owner")
    public void putOwner_validAndExisting_shouldReturn_sameOwner() throws Exception{
        Owner owner = ownerService.putOneById(1L, new Owner("Petersboy"));
        assertEquals(Owner.class,owner.getClass());
        assertEquals("Petersboy",owner.getName());
    }

    //US-13 negative
    @Test
    @DisplayName("Put invalid owner should throw ValidationException")
    public void putOwner_invalidOwner_shouldThrow_ValidationException() throws Exception{
        assertThrows(ValidationException.class,
            () -> ownerService.putOneById(1L, new Owner()));
    }

    //US-13 negative
    @Test
    @DisplayName("Put invalid id should throw ValidationException")
    public void putOwner_invalidId_shouldThrow_ValidationException() throws Exception{
        assertThrows(ValidationException.class,
            () -> ownerService.putOneById(0L, new Owner("Petersboy")));
    }

    //US-13 negative
    @Test
    @DisplayName("Put valid owner to non existing id should throw NotFoundException")
    public void putOwner_nonExistingId_shouldThrow_NotFoundException() throws Exception{
        assertThrows(NotFoundException.class,
            () -> ownerService.putOneById(9999L, new Owner("Petersboy")));
    }

    //US-13 negative
    @Test
    @DisplayName("Delete invalid id should throw ValidationException")
    public void deleteOwner_invalidId_shouldThrow_ValidationException() throws Exception{
        assertThrows(ValidationException.class,
            () -> ownerService.deleteOneById(0L));
    }

    //US-13 negative
    @Test
    @DisplayName("Delete non existing owner should throw NotFoundException")
    public void deleteOwner_nonExisting_shouldThrow_NotFoundException() throws Exception{
        assertThrows(NotFoundException.class,
            () -> ownerService.deleteOneById(9999L));
    }

}
