package com.geolocator.model;

public class Shop {
	
	private String shopName;
	private ShopAddress shopAddress;
	private Double shopLongitude;
	private Double shopLattitude;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public ShopAddress getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}
	public Double getShopLongitude() {
		return shopLongitude;
	}
	public void setShopLongitude(Double shopLongitude) {
		this.shopLongitude = shopLongitude;
	}
	public Double getShopLattitude() {
		return shopLattitude;
	}
	public void setShopLattitude(Double shopLattitude) {
		this.shopLattitude = shopLattitude;
	}
	
	
	
	//for tests
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        Shop shop = (Shop) o;

	        if (shopName != null ? !shopName.equals(shop.shopName) : shop.shopName != null) return false;
	        if (shopLattitude != null ? !shopLattitude.equals(shop.shopLattitude) : shop.shopLattitude != null) return false;
	        return shopLongitude != null ? shopLongitude.equals(shop.shopLongitude) : shop.shopLongitude == null;

	    }

	    @Override
	    public int hashCode() {
	        int result = shopName != null ? shopName.hashCode() : 0;
	        result = 2 * result + (shopLattitude != null ? shopLattitude.hashCode() : 0);
	        result = 3 * result + (shopLongitude != null ? shopLongitude.hashCode() : 0);
	        return result;
	    }

	
	

}
