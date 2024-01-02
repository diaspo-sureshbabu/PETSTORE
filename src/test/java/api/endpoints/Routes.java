package api.endpoints;

public class Routes {
	
	// https://petstore.swagger.io/v2
	
	/*
	 // base url:  https://petstore.swagger.io/v2
	
		  END POINTS  urls:
	 * create user  POST   /user
	 * view user   GET  /user/{username} 
	 * modify user PUT    /user/{username}
	 * delete user DELETE /user/{username}
	
	  	  
	 
	 * 
	 */
	
	
	 
	 public static String base_url = "https://petstore.swagger.io/v2";
	 public static String create_user_url = base_url +  "/user";
	  public static String view_user_url = base_url + "/user/{username} ";
	  public static String modify_user_url = base_url + "/user/{username}";
	  public static String delete_user_url = base_url + "/user/{username}";

}
