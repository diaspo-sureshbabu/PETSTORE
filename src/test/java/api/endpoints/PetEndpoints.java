package api.endpoints;

import static io.restassured.RestAssured.given;
import java.util.ResourceBundle;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndpoints {
	
	static ResourceBundle getURL() {
		ResourceBundle routes =	ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response CreatePet (Pet Payload) {
		String CreatePetURL = PetEndpoints.getURL().getString("add_pet_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(Payload)
				
				.when()
				.post(CreatePetURL);
				return response;	
			}
	public static Response viewPet(long petid) {
		String viewPetURL = getURL().getString("view_pet_url");
		Response response = given()				
				.accept(ContentType.JSON)
				.pathParam("petid", petid)
		.when()
			.get(viewPetURL);
		
		return response;
	}
	
	
	
}