package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndpoint;
import api.payload.Order;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class PetStoreOrdersTest {
	
	Faker faker;
	Order orderPayload;
	public Logger logger;
	@BeforeClass
	public void Setup() {
		faker = new Faker();
		orderPayload = new Order();	
		
		
		orderPayload.setId(faker.idNumber().hashCode());
		orderPayload.setPetid(faker.idNumber().hashCode());
		orderPayload.setQuantity(100);
		orderPayload.setStatus("placed");
		orderPayload.setShipdate("26/05/2024");
		orderPayload.setComplete(true);

		
		// code to initiate the logs
		logger = LogManager.getLogger(this.getClass());


	}
	
	
	@Test (priority =4)	
	public void testdeleteorderbyid() {
		logger.info(" ********** deleting order details  " + this.orderPayload.getId());
		Response response;		
		response = StoreEndpoint.Deleteorderbyid(this.orderPayload.getId());
		response.then().log().all();	
		ResponseBody body = response.getBody();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Deleting order id : " + this.orderPayload.getId() + " completed ");
		logger.info (body.asString());
	}
	
	@Test(priority = 3)
	public void testgetinventorydetails() {
		logger.info(" ********** Starting of get inventory details  " + this.orderPayload.getId());
		Response response;		
		response = StoreEndpoint.Getinventorydetails();
		response.then().log().all();	
		ResponseBody body = response.getBody();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Viw inventory details completed");
		logger.info (body.asString());
	}
	

	@Test(priority = 2)
	public void testgetorderdetails() {
		logger.info(" ********** Starting of order details  " + this.orderPayload.getId());
		Response response;		
		response = StoreEndpoint.GetOrderdetails(this.orderPayload.getId());
		response.then().log().all();	
		ResponseBody body = response.getBody();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("Viw order details completed");
		logger.info (body.asString());
	}
	
	
	@Test(priority = 1)
	public void testCreate() {
		logger.info(" ********** Starting of User Creation ");
		Response response;		
		response = StoreEndpoint.PostPlaceOrder(orderPayload); 
				//UserEndpoints.CreateUser(userPayload);	
		response.then().log().all();	
		ResponseBody body = response.getBody();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info(" Pet order Creation completed");
		logger.info (body.asString());	

	}

}
