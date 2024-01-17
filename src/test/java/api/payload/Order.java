package api.payload;

import java.util.Date;

import com.github.javafaker.DateAndTime;

public class Order {
	
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPetid() {
		return petid;
	}
	public void setPetid(int petid) {
		this.petid = petid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getShipdate() {
		return shipdate;
	}
	public void setShipdate(String date) {
		this.shipdate = date;
	}
	public boolean getComplete() {
		return complete;
	}
	public void setComplete(boolean value) {
		this.complete = value;
	}
	int petid;
	int quantity; 
	String status;
	String shipdate;
	boolean complete;
	
	
	
	
	

}
