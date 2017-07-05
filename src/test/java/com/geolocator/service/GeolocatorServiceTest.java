package com.geolocator.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.geolocator.BaseTest;
import com.geolocator.model.GeoLocation;
import com.geolocator.model.Shop;
import com.geolocator.model.ShopAddress;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;


@RunWith(SpringJUnit4ClassRunner.class)
public class GeolocatorServiceTest extends BaseTest {

    GeoLocation locationTest = null;

    @Before
    public void setData() throws Exception {
       
    	locationTest = setLatLongFromGoogle("Groenhof".concat(",").concat("1186"));
    }

    @Test
    public void testGeoLocationFinder() {
        String address = "Groenhof".concat(",").concat("1186");
        GeoLocation location = GeolocatorApiServiceImpl.geoLocationFinder(address);
        assert (location.equals(locationTest));
    }



    @Test
    public void testGetShopByLocation() {
    	Shop s1 = buildShopWithLocation(buildShop("Store A", "Number 1", "1"), 1.0, 1.0);
    	Shop s2 = buildShopWithLocation(buildShop("Store B", "Number 2", "2"), 2.0, 2.0);
    	Shop s3 = buildShopWithLocation(buildShop("Store C", "Number 3", "3"), -2.0, -2.0);
    	Shop s4 = buildShopWithLocation(buildShop("Store D", "Number 4", "4"), -2.0, 2.0);
        shopAddressStore.add(s1);
        shopAddressStore.add(s2);
        shopAddressStore.add(s3);
        shopAddressStore.add(s4);
       
        assert geolocatorApiService.getShopByLocation(1.0,1.0).equals(s1);
        
        assert geolocatorApiService.getShopByLocation(2.0,2.0).equals(s2);
        assert geolocatorApiService.getShopByLocation(-2.0,-2.0).equals(s3);

    }

    private Shop buildShop(String name, String number, String post) {
        Shop shop = new Shop();
        shop.setShopName(name);
        shop.setShopAddress(new ShopAddress(number, post));
        return shop;
    }

    private Shop buildShopWithLocation(Shop shop, Double latitude, Double longitude) {
       
    	shop.setShopLattitude(latitude);
    	shop.setShopLongitude(longitude);
        return shop;
    }

    private GeoLocation setLatLongFromGoogle(String address) throws Exception {
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCjKUCYceY2c9Tl6RjLFHvYalgL23VXGlE");
        GeocodingResult result = GeocodingApi.geocode(context, address).await()[0];
        LatLng location = result.geometry.location;
        return new GeoLocation(location.lat, location.lng);
    }
}
