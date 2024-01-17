package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndpoints;
import api.payload.Pet;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class PetTest {

	Faker faker;
	Pet petPayload;
	public Logger logger;
	static String str_id;
	@BeforeClass
	public void petsetup() {
		faker = new Faker();
		petPayload = new Pet();

		petPayload.setPet_id(faker.idNumber().hashCode());
		petPayload.setCategory_id(faker.idNumber().hashCode());
		petPayload.setCategory_name(faker.name().fullName());
		petPayload.setPet_name(faker.name().firstName());
		petPayload.setPet_photourls("https://www.dummy.com");
		petPayload.setTags_id(faker.idNumber().hashCode());
		petPayload.setTags_name(faker.name().name());
		petPayload.setPet_status("available");
		// code to initiate the logs
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void testCreatePet() {
		logger.info(" ********** Starting of PET Creation ");
		Response response = PetEndpoints.CreatePet(petPayload);
		response.then().log().all();
		ResponseBody body = response.getBody();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" Pet Creation completed" + this.petPayload.getPet_id());
		logger.info("response body1" + body.asString());
		str_id = body.asString().substring(6, 25);
		logger.info("response pet id : " + str_id);

	}

	@Test(priority = 2)
	public void testgetPetByPETID() {
		logger.info(" Getting pet details by PET Id" + Integer.parseInt(str_id));
		
		Response response = PetEndpoints.viewPet(Integer.parseInt(str_id));
		// UserEndpoints.ViewUser(this.userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" Getting Pet details by PET ID completed");
		ResponseBody responsebody = response.getBody();
		logger.info(responsebody.asString());

	}

}
