package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.Order;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndpoint {

	static ResourceBundle getURL(){
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	//delete_order_byid_url
	public static Response Deleteorderbyid(int orderid) {
		String deleteorderbyidURL = getURL().getString("delete_order_byid_url");
		Response response= given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("orderId", orderid )
				
		.when()
					.delete(deleteorderbyidURL);
		
		return response;
	}
	
	
	//get_pet_inventory_url
	public static Response Getinventorydetails() {		
		String getInventorydetailsURL = getURL().getString("get_pet_inventory_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)				
				.when()
					.get(getInventorydetailsURL);
		return response; 
	}
	
	
	public static Response GetOrderdetails(int orderid) {
		String getorderdetailsURL = getURL().getString("find_order_byid_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("orderId", orderid)
			.when()
				.get(getorderdetailsURL);
		return response;
	}
	
	public static Response PostPlaceOrder (Order payload) {
		
		String createOrderURL = getURL().getString("placing_order_url");
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
		.when()
			.post(createOrderURL);
		
		return response;
		
	}
	
}
