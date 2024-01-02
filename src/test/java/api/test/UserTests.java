package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;
	public Logger logger;
	@BeforeClass
	public void Setup() {
		faker = new Faker();
		userPayload = new User();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setPassword(faker.internet().password());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserstatus(0);
		
		// code to initiate the logs
		logger = LogManager.getLogger(this.getClass());


	}

	@Test(priority = 1)
	public void testCreateUser() {
		logger.info(" ********** Starting of User Creation ");
		Response response = UserEndpoints.CreateUser(userPayload);	
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" User Creation completed");
		

	}

	@Test(priority = 2)
	public void testgetUSerByName() {
		logger.info(" Getting user details by User Name");
		Response response = UserEndpoints.ViewUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" Getting user details by User Name completed");

	}
	
	@Test(priority = 3)
	public void testUpdateUSerByName() {
		
		//updating user details
		logger.info(" Updating user details by User Name");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());		
		userPayload.setEmail(faker.internet().emailAddress());

		//calling updated api
		Response response = UserEndpoints.ModifyUser(this.userPayload.getUsername(), userPayload);				
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);

		// getting the updated user details
		Response responseAfterUpdate = UserEndpoints.ViewUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info(" user is updated");

	}

	@Test(priority = 4)
	public void testDeleteUSerByName() {
		logger.info(" Deleting user details by User Name");
		//calling Delete user by name api
		Response response = UserEndpoints.DeleteUser(this.userPayload.getUsername());					
		Assert.assertEquals(response.getStatusCode(), 200);

		// verifying user is deleted
		Response responseAfterDelete = UserEndpoints.ViewUser(this.userPayload.getUsername());
		responseAfterDelete.then().log().all();
		Assert.assertEquals(responseAfterDelete.getStatusCode(), 404);
		logger.info(" User is deleted");
	}
	
	
	
}
