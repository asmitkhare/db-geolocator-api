package com.geolocator.exp;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;

public class GeolocatorAPIExceptions extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1640987504328056514L;
	@NotNull
    private HttpStatus httpStatus;

    public GeolocatorAPIExceptions(Exception e, String msg, HttpStatus httpStatus) {
        super(msg, e);
        this.httpStatus = httpStatus;
    }

    public GeolocatorAPIExceptions(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatus;
    }

}
