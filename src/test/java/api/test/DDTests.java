package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.Dataproviders;
import io.restassured.response.Response;

public class DDTests {
	
//	/*Faker faker;
//	User userPayload;
//
//	@BeforeClass
//	public void SetupData() {
//		faker = new Faker();
//		userPayload = new User();
//		userPayload.setId(faker.idNumber().hashCode());
//	}
	
	@Test(priority=1, dataProvider = "Data",
			dataProviderClass = Dataproviders.class)
	public void testPostuser(String	UserID,String username,String	firstName,String lastName,String emailAddress,String password,
			String phone) 
	{
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(username);
		userPayload.setFirstname(firstName);
		userPayload.setLastname(lastName);
		userPayload.setPassword(password);
		userPayload.setEmail(emailAddress);
		userPayload.setPhone(phone);
		userPayload.setUserstatus(0);
		//listing user details updated
		
		Response response = UserEndpoints.CreateUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	
	@Test(priority=2, dataProvider = "UserNames", dataProviderClass = Dataproviders.class)
	public void testViewUserDetails(String username)
	{
		Response response = UserEndpoints.ViewUser(username);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3, dataProvider = "Data", dataProviderClass = Dataproviders.class)
	public void testUpdateUserDetails(String	UserID,String username,String	firstName,String lastName,String emailAddress,String password,
			String phone) 
	{
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(username);
		userPayload.setFirstname(String.join(" ",firstName , "updated"));
		userPayload.setLastname(String.join(" ",lastName , "updated"));
		userPayload.setPassword(password);
		userPayload.setEmail(String.join("", "updated",emailAddress ));		
		userPayload.setPhone(phone);
		userPayload.setUserstatus(0);		
		
		Response response = UserEndpoints.ModifyUser(username, userPayload);
		Response response_view = UserEndpoints.ViewUser(username);
		response_view.then().log().all();	
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test(priority=4, dataProvider = "UserNames", dataProviderClass = Dataproviders.class)
	public void testDeleteUser(String username)
	{
		Response response = UserEndpoints.DeleteUser(username);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	

}
