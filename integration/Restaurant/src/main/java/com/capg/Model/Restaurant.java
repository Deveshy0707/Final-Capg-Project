package com.capg.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rest_id")
	private int restId;
	
	@NotEmpty
	@Size(min = 5, message = "Enter the restaurant name")
	@Column(name = "rest_name")
	private String restName;
	
	@NotEmpty(message = "Enter the location")
	@Column(name = "rest_loc")
	private String restLoc;
	
	@NotEmpty(message = "Enter the retaurant type")
	@Column(name = "rest_type")
	private String restType;
	
	@Column(name = "rest_contac")
	private int restContact;

	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public String getRestLoc() {
		return restLoc;
	}
	public void setRestLoc(String restLoc) {
		this.restLoc = restLoc;
	}
	public String getRestType() {
		return restType;
	}
	public void setRestType(String restType) {
		this.restType = restType;
	}
	public int getRestContact() {
		return restContact;
	}
	public void setRestContact(int restContact) {
		this.restContact = restContact;
	}
	public Restaurant(int restId, @NotEmpty @Size(min = 5, message = "Enter the restaurant name") String restName,
			@NotEmpty(message = "Enter the location") String restLoc,
			@NotEmpty(message = "Enter the retaurant type") String restType, int restContact) {
		super();
		this.restId = restId;
		this.restName = restName;
		this.restLoc = restLoc;
		this.restType = restType;
		this.restContact = restContact;
	}
	
	
	public Restaurant() {
		super();
	}
	@Override
	public String toString() {
		return "Restaurant [restId=" + restId + ", restName=" + restName + ", restLoc=" + restLoc + ", restType="
				+ restType + ", restContact=" + restContact + "]";
	}
	
	
	
	
}
