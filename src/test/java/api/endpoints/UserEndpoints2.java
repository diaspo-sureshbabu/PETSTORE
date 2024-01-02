
package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

	public class UserEndpoints2 {

		
		static ResourceBundle getURL(){
			ResourceBundle routes = ResourceBundle.getBundle("routes");
			return routes;
		}
		
		public static Response CreateUser (User payload) {
			
			String createUserURL = getURL().getString("create_user_url");
			Response response = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
			.when()
				.post(createUserURL);
			
			return response;
			
		}
		public static Response ViewUser (String username) {
			String viewUserURL = getURL().getString("view_user_url");
			Response response = given()				
					.accept(ContentType.JSON)
					.pathParam("username", username)
			.when()
				.get(viewUserURL);
			
			return response;
			
		}
		public static Response ModifyUser (String username,User payload) {
			String modifyUserURL = getURL().getString("modify_user_url");
			Response response = given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("username",username)
					.body(payload)
					
			.when()
				.put(modifyUserURL);			
			
			return response;
			
		}
		
		
		public static Response DeleteUser (String username) {
			String DeleteUserURL = getURL().getString("delete_user_url");
			Response response = given()				
					.accept(ContentType.JSON)
					.pathParam("username", username)
			.when()
				.delete(DeleteUserURL);
			
			return response;
			
		}
		
		
		
	}

	
	

