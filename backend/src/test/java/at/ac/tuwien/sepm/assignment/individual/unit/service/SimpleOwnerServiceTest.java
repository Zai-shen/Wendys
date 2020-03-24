package at.ac.tuwien.sepm.assignment.individual.unit.service;

import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import at.ac.tuwien.sepm.assignment.individual.persistence.OwnerDao;
import at.ac.tuwien.sepm.assignment.individual.service.OwnerService;
import at.ac.tuwien.sepm.assignment.individual.service.impl.SimpleOwnerService;
import at.ac.tuwien.sepm.assignment.individual.util.Validator;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class SimpleOwnerServiceTest {

    @Autowired
    private OwnerService ownerService;

    @MockBean
    OwnerDao ownerDao;

    Validator validator = new Validator();

    @MockBean
    SimpleOwnerService simpleOwnerService = new SimpleOwnerService(ownerDao,validator);

    @Test
    @DisplayName("Finding owner by existing ID should return correct owner")
    public void findingOwnerById_existing_shouldReturn_CorrectOwner(){
        Owner saveOwner = new Owner("peteeeee");
        Owner returnedOwner = new Owner("peter");
/*       try {
            returnedOwner = ownerDao.saveOwner(saveOwner);
        }catch (PersistenceException e){
            fail("Exception fired while saving Owner");
        }
*/
        try {
            Mockito.when(simpleOwnerService.saveOwnerEntity(Mockito.any(Owner.class))).thenReturn(new Owner("notpete"));
            returnedOwner = ownerService.saveOwnerEntity(saveOwner);
        }catch (Exception e){
            System.out.println((e.getMessage()) + e.getCause());
            e.printStackTrace();
        }

        assertEquals("notpete",returnedOwner.getName());
        //assertEquals(returnedOwner.getClass(),Owner.class);
    }

}
