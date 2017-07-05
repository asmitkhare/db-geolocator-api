package com.geolocator.model;
  
public class ServiceResponse {

    private Boolean runningStatus;

    public ServiceResponse(Boolean runningStatus){
        this.runningStatus = runningStatus;
    }

	public Boolean getRunningStatus() {
		return runningStatus;
	}

	public void setRunningStatus(Boolean runningStatus) {
		this.runningStatus = runningStatus;
	}

    

}
