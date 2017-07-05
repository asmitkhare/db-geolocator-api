package com.geolocator.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.geolocator.exp.GeolocatorAPIExceptions;
import com.geolocator.model.GeoLocation;
import com.geolocator.model.Shop;
import com.geolocator.model.ShopAddress;
import com.geolocator.util.ShopAddressStore;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Service("geolocatorApiService")
public class GeolocatorApiServiceImpl implements GeolocatorApiService {

	private static final String GEO_API_KEY = "AIzaSyCjKUCYceY2c9Tl6RjLFHvYalgL23VXGlE";
	private static final Logger logger = LoggerFactory.getLogger(GeolocatorApiServiceImpl.class);

	@Autowired
	private ShopAddressStore shopAddressStore;

	@Override
	public void addShop(Shop shop) {
		if (shop == null || shop.getShopAddress() == null || shop.getShopAddress().getNumber() == null
				|| shop.getShopAddress().getPostCode() == null) {
			logger.debug("Invalid shop - " + shop);
		} else {

			ShopAddress shopAddress = shop.getShopAddress();
			StringBuilder address = new StringBuilder(shopAddress.getNumber()).append(",")
					.append(shopAddress.getPostCode());

			// Get the latitude & longitude
			GeoLocation location = geoLocationFinder(address.toString());
			shop.setShopLattitude(location.getLattitude());
			shop.setShopLongitude(location.getLongitude());

			// Add it to in-memory db
			shopAddressStore.add(shop);

		}
	}

	public static GeoLocation geoLocationFinder(String shopAddress) {
		GeoApiContext context = new GeoApiContext().setApiKey(GEO_API_KEY);
		try {
			GeocodingResult result = GeocodingApi.geocode(context, shopAddress).await()[0];
			LatLng location = result.geometry.location;
			return new GeoLocation(location.lat, location.lng);
		} catch (Exception e) {
			logger.error("Error while fetching data from google geo api.", e);
			throw new GeolocatorAPIExceptions(e, "Error while retrieving location data for the shop. Please try again",
					HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@Override
	public Shop getShopByLocation(Double latitude, Double longitude) {
		List<Shop> allShops = shopAddressStore.getAllShops();

		if (allShops == null || allShops.isEmpty()) {
			logger.info("No shops added yet.");
			throw new GeolocatorAPIExceptions("No Shops Available in Memory", HttpStatus.OK);
		}

		Shop nearestShop = allShops.get(0);
		double nearestShopDist = calcDistance(latitude, longitude, nearestShop);
		double temp;

		for (int i = 1; i < allShops.size(); i++) {
			temp = calcDistance(latitude, longitude, allShops.get(i));
			if (temp < nearestShopDist) {
				nearestShopDist = temp;
				nearestShop = allShops.get(i);
			}
		}

		if (nearestShopDist == 0.0) {
			System.out.println("Location matched. Shop Found");
		}
		return nearestShop;
	}

	// distance calculation as per Formula for finding distance by coordinates
	// for Geocoding api
	private double calcDistance(final double latitude, final double longitude, Shop shop) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(Double.valueOf(shop.getShopLattitude()) - latitude);
		double lonDistance = Math.toRadians(Double.valueOf(shop.getShopLongitude()) - longitude);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(Double.valueOf(shop.getShopLattitude())))
						* Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c * 1000; // convert to meters

	}

}
