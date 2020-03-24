package at.ac.tuwien.sepm.assignment.individual.unit.endpoint;


import at.ac.tuwien.sepm.assignment.individual.entity.Owner;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class OwnerEndpointTest {

    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private String OWNER_RESOURCE_URL = "http://localhost:8080/owners";

    //US-13 normal
    @Test
    @DisplayName("Post valid owner should return 201.CREATED")
    public void postOwner_valid_shouldReturn_200OK() throws Exception {
        ResponseEntity<String> response = testRestTemplate.
            postForEntity(OWNER_RESOURCE_URL, new Owner("Peter"), String.class);

        //safeOwner = testRestTemplate.postForObject(OWNER_RESOURCE_URL, new Owner("Peter"),Owner.class);

        Assert.assertEquals((HttpStatus.CREATED), response.getStatusCode());
    }

    //US-13 negative
    @Test
    @DisplayName("Post invalid owner should return 400.BAD_REQUEST")
    public void postOwner_invalid_shouldReturn_400BAD_REQUEST() throws Exception {
        ResponseEntity<String> response = testRestTemplate.
            postForEntity(OWNER_RESOURCE_URL, new Owner(), String.class);

        Assert.assertEquals((HttpStatus.BAD_REQUEST), response.getStatusCode());
    }

    //US-13 normal
    @Test
    @DisplayName("Get existing owner should return 200.OK")
    public void getOwner_existing_shouldReturn_200OK() throws Exception {
        Owner owner = testRestTemplate.postForObject(OWNER_RESOURCE_URL, new Owner("Peter"), Owner.class);

        ResponseEntity<String> response = testRestTemplate.
            getForEntity(OWNER_RESOURCE_URL + "/" + owner.getId(), String.class);

        Assert.assertEquals((HttpStatus.OK), response.getStatusCode());
    }

    //US-13 negative
    @Test
    @DisplayName("Get non-existing owner should return 404.NOT_FOUND")
    public void getOwner_nonExisting_shouldReturn_404NOT_FOUND() throws Exception {
        Owner owner = testRestTemplate.postForObject(OWNER_RESOURCE_URL, new Owner("Peter"), Owner.class);

        ResponseEntity<String> response = testRestTemplate.
            getForEntity(OWNER_RESOURCE_URL + "/" + owner.getId() + 1, String.class);

        Assert.assertEquals((HttpStatus.NOT_FOUND), response.getStatusCode());
    }

    //US-13 normal
    @Test
    @DisplayName("Put existing owner with valid owner should return 200.OK")
    public void putOwner_existingAndValid_shouldReturn_200OK() throws Exception {
        Owner owner = testRestTemplate.postForObject(OWNER_RESOURCE_URL, new Owner("Peter"), Owner.class);

        HttpEntity<Owner> ownerEntity = new HttpEntity<>(new Owner("Frank"));
        ResponseEntity<Owner> response = testRestTemplate.exchange(OWNER_RESOURCE_URL + "/" +owner.getId(), HttpMethod.PUT,ownerEntity,Owner.class);

        //System.out.println(response.getStatusCode());
        //System.out.println(response.getBody());
        Assert.assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    //US-13 negative
    @Test
    @DisplayName("Put non existing owner with valid owner should return 404.NOT_FOUND")
    public void putOwner_nonExistingAndValid_shouldReturn_404NOT_FOUND() throws Exception {
        Owner owner = testRestTemplate.postForObject(OWNER_RESOURCE_URL, new Owner("Peter"), Owner.class);

        HttpEntity<Owner> ownerEntity = new HttpEntity<>(new Owner("Frank"));
        ResponseEntity<Owner> response = testRestTemplate.exchange(OWNER_RESOURCE_URL + "/" +owner.getId() + 1, HttpMethod.PUT,ownerEntity,Owner.class);

        Assert.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    //US-13 negative
    @Test
    @DisplayName("Put invalid owner should return 400.BAD_REQUEST")
    public void putOwner_invalid_shouldReturn_400BAD_REQUEST() throws Exception {
        Owner owner = testRestTemplate.postForObject(OWNER_RESOURCE_URL, new Owner("Peter"), Owner.class);

        HttpEntity<Owner> ownerEntity = new HttpEntity<>(new Owner());
        ResponseEntity<Owner> response = testRestTemplate.exchange(OWNER_RESOURCE_URL + "/" +owner.getId(), HttpMethod.PUT,ownerEntity,Owner.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }

    //US-13 normal
    @Test
    @DisplayName("Put existing owner with valid owner should return different owner")
    public void putOwner_existingAndValid_shouldReturn_differentOwner() throws Exception {
        Owner owner = testRestTemplate.postForObject(OWNER_RESOURCE_URL, new Owner("Peter"), Owner.class);

        testRestTemplate.put(OWNER_RESOURCE_URL + "/" + owner.getId(), new Owner("Frank"));

        Owner updated = testRestTemplate.getForObject(OWNER_RESOURCE_URL + "/" + owner.getId(), Owner.class);

        Assert.assertNotEquals(owner.getName(), updated.getName());
    }

    //US-13 negative
    @Test
    @DisplayName("Put existing owner with invalid owner should not change owner")
    public void putOwner_invalid_shouldNotChange_owner() throws Exception {
        Owner owner = testRestTemplate.postForObject(OWNER_RESOURCE_URL, new Owner("Peter"), Owner.class);

        testRestTemplate.put(OWNER_RESOURCE_URL + "/" + owner.getId(), new Owner());

        Owner updated = testRestTemplate.getForObject(OWNER_RESOURCE_URL + "/" + owner.getId(), Owner.class);

        Assert.assertEquals(owner.getName(), updated.getName());
    }


    //US-13 normal
    @Test
    @DisplayName("Delete existing owner should return 204.NO_CONTENT")
    public void deleteOwner_existing_shouldReturn_204NO_CONTENT() throws Exception {
        Owner owner = testRestTemplate.postForObject(OWNER_RESOURCE_URL, new Owner("Peter"), Owner.class);

        HttpEntity<String> stringEntity = new HttpEntity<>("");
        ResponseEntity<String> response = testRestTemplate.exchange(OWNER_RESOURCE_URL + "/" +owner.getId(), HttpMethod.DELETE,stringEntity,String.class);

        Assert.assertEquals((HttpStatus.NO_CONTENT), response.getStatusCode());
    }

    //US-13 negative
    @Test
    @DisplayName("Delete non existing owner should return 404.NOT_FOUND")
    public void deleteOwner_nonExisting_shouldReturn_404NOT_FOUND() throws Exception {
        Owner owner = testRestTemplate.postForObject(OWNER_RESOURCE_URL, new Owner("Peter"), Owner.class);

        HttpEntity<String> stringEntity = new HttpEntity<>("");
        ResponseEntity<String> response = testRestTemplate.exchange(OWNER_RESOURCE_URL + "/" +owner.getId() + 1, HttpMethod.DELETE,stringEntity,String.class);

        Assert.assertEquals((HttpStatus.NOT_FOUND), response.getStatusCode());
    }
}
