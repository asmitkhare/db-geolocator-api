package com.geolocator.model;

public class GeoLocation {

	private double lattitude;
	private double longitude;

	public GeoLocation(double lattitude, double longitude) {
		super();
		this.lattitude = lattitude;
		this.longitude = longitude;
	}

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

}
