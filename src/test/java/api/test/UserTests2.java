package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints2;
import api.payload.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class UserTests2 {

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
		Response response = UserEndpoints2.CreateUser(userPayload); 
				//UserEndpoints.CreateUser(userPayload);	
		response.then().log().all();	
		ResponseBody body = response.getBody();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" User Creation completed");
		logger.info (body.asString());	

	}

	@Test(priority = 2)
	public void testgetUSerByName() {
		logger.info(" Getting user details by User Name");
		Response response =UserEndpoints2.ViewUser(this.userPayload.getUsername()) ;
				//UserEndpoints.ViewUser(this.userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" Getting user details by User Name completed");
		ResponseBody responsebody = response.getBody();
		logger.info (responsebody.asString());

	}
	
	@Test(priority = 3)
	public void testUpdateUSerByName() {
		
		//updating user details
		logger.info(" Updating user details by User Name");
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());		
		userPayload.setEmail(faker.internet().emailAddress());

		//calling updated api
		Response response = UserEndpoints2.ModifyUser(this.userPayload.getUsername(), userPayload);				
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(), 200);
		ResponseBody body = response.getBody();
		logger.info("user details update" + body.asString());
		
		// getting the updated user details
		Response responseAfterUpdate = UserEndpoints2.ViewUser(this.userPayload.getUsername()) ;
		ResponseBody body2 = responseAfterUpdate.getBody();
		logger.info ( this.userPayload.getEmail() + " user details udpated " + body2.asString());
			
		Assert.assertEquals(body2.asString().contains(this.userPayload.getEmail()),true, "contains email message");
		
		
		responseAfterUpdate.then().log().all();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		logger.info(" user is updated");

	}

	@Test(priority = 4)
	public void testDeleteUSerByName() {
		logger.info(" Deleting user details by User Name");
		//calling Delete user by name api
		Response response = UserEndpoints2.DeleteUser(this.userPayload.getUsername());					
		Assert.assertEquals(response.getStatusCode(), 200);

		// verifying user is deleted
		Response responseAfterDelete = UserEndpoints2.ViewUser(this.userPayload.getUsername());
		responseAfterDelete.then().log().all();
		Assert.assertEquals(responseAfterDelete.getStatusCode(), 404);
		logger.info(" User is deleted");
	}
	
	
	
}
