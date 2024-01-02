package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {

	public static Response CreateUser (User payload) {
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
		.when()
			.post(Routes.create_user_url);
		
		return response;
		
	}
	public static Response ViewUser (String username) {
		
		Response response = given()				
				.accept(ContentType.JSON)
				.pathParam("username", username)
		.when()
			.get(Routes.view_user_url);
		
		return response;
		
	}
	public static Response ModifyUser (String username,User payload) {
		
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username",username)
				.body(payload)
				
		.when()
			.put(Routes.modify_user_url);			
		
		return response;
		
	}
	
	
	public static Response DeleteUser (String username) {
		
		Response response = given()				
				.accept(ContentType.JSON)
				.pathParam("username", username)
		.when()
			.delete(Routes.delete_user_url);
		
		return response;
		
	}
	
	
	
}
