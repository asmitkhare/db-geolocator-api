package com.geolocator.model;

public class ShopAddress {
	
	private String number;
	private String postCode;
	
	public ShopAddress(String number, String post) {
		this.number=number;
		this.postCode=post;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}


	
	
}
