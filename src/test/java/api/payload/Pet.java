package api.payload;

public class Pet {

	   int pet_id;
	   int category_id;
	   String category_name; 
	    
	   String pet_name;
	   String pet_photourls; 
	   int tags_id;
	   String tags_name;
	   String pet_status;
	   
	   

	public int getPet_id() {
		return pet_id;
	}
	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getPet_name() {
		return pet_name;
	}
	public void setPet_name(String pet_name) {
		this.pet_name = pet_name;
	}
	public String getPet_photourls() {
		return pet_photourls;
	}
	public void setPet_photourls(String pet_photourls) {
		this.pet_photourls = pet_photourls;
	}
	public int getTags_id() {
		return tags_id;
	}
	public void setTags_id(int tags_id) {
		this.tags_id = tags_id;
	}
	public String getTags_name() {
		return tags_name;
	}
	public void setTags_name(String tags_name) {
		this.tags_name = tags_name;
	}
	public String getPet_status() {
		return pet_status;
	}
	public void setPet_status(String pet_status) {
		this.pet_status = pet_status;
	}
		
	  
	
}
