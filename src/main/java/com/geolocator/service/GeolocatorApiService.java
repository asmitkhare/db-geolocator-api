package com.geolocator.service;

import com.geolocator.model.Shop;

public interface GeolocatorApiService {
	
	public void addShop(Shop shop);
	public Shop getShopByLocation(Double latitude, Double longitude);
	
	
	}
