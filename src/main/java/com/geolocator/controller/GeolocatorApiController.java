package com.geolocator.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geolocator.exp.GeolocatorAPIExceptions;
import com.geolocator.model.ServiceResponse;
import com.geolocator.model.Shop;
import com.geolocator.service.GeolocatorApiService;

@RestController
@RequestMapping("/api")
public class GeolocatorApiController {

	private static final Logger logger = LoggerFactory.getLogger(GeolocatorApiController.class);
	@Autowired
	GeolocatorApiService geolocatorApiService;

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Autowired
	ObjectMapper objectMapper;

	/**
	 * @param _response
	 * @param shop
	 * @return
	 */
	@RequestMapping(path = "/shop/add", method = RequestMethod.POST)
	public @ResponseBody String addShop(HttpServletResponse _response, @Validated @RequestBody Shop shop) {
		String response;
		try {
			geolocatorApiService.addShop(shop);
			response = objectMapper.writeValueAsString(new ServiceResponse(true));
			_response.setStatus(HttpStatus.CREATED.value());
		} catch (IOException io) {
			logger.error("Error while parsing Request to Add New Shop", io);
			throw new GeolocatorAPIExceptions(io, "Response not Processsed", HttpStatus.OK);
		}
		return response;
	}

	/**
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	@RequestMapping(path = "/shop/getshop", method = RequestMethod.GET)
	public String getNearestShop(@RequestParam("customerLatitude") String latitude,
			@RequestParam("customerLongitude") String longitude) {
		String result = null;
		try {
			result = objectMapper.writeValueAsString(
					geolocatorApiService.getShopByLocation(Double.valueOf(latitude), Double.valueOf(longitude)));
			return result;
		} catch (NumberFormatException e) {
			logger.error("Invalid Input longitude " + longitude + " / lattitude - " + latitude, e);
			throw new GeolocatorAPIExceptions(e, "Invalid Location", HttpStatus.BAD_REQUEST);
		} catch (GeolocatorAPIExceptions e) {
			throw e;
		} catch (Exception e) {
			logger.error("Error finding nearest shop" + latitude + ", " + longitude + ")", e);
			throw new GeolocatorAPIExceptions(e, "Service not Available ", HttpStatus.SERVICE_UNAVAILABLE);
		}

	}
	
	@ExceptionHandler(value = GeolocatorAPIExceptions.class)
    public String handler(GeolocatorAPIExceptions e, HttpServletResponse _response) {
        _response.setStatus(e.getHttpStatusCode().value());
        return e.getMessage();
    }

}
