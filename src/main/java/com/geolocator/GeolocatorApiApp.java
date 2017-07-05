package com.geolocator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geolocator.service.GeolocatorApiService;
import com.geolocator.service.GeolocatorApiServiceImpl;
import com.geolocator.util.ShopAddressStore;

@SpringBootApplication(scanBasePackages = { "com.geolocator" })
public class GeolocatorApiApp {

	public static void main(String[] args) {
		SpringApplication.run(GeolocatorApiApp.class, args);
		System.out.println("Application has started successfully at http://localhost:8080/api/");
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public GeolocatorApiService geolocatorApiService() {
		return new GeolocatorApiServiceImpl();
	}

	@Bean
	public ShopAddressStore addressHolder() {
		return new ShopAddressStore();
	}

}
