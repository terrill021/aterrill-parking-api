package com.ceiba.apis.parking.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.apis.parking.model.ApiResponse;
import com.ceiba.bl.parking.IParking;
import com.ceiba.bl.parking.models.Parking;
import com.ceiba.bl.parking.models.Vehicle;

@RestController
public class ApiParking {
    
    @Autowired
    IParking parkingImpl;

    @RequestMapping("/")
    public ApiResponse root() {		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setPayload("Api parqueadero");
		return apiResponse;
	}

    @CrossOrigin(origins="*")
    @RequestMapping("/parking/{parkingId}/vehicles")
    public ApiResponse registerVehicleIn(@PathVariable(name="parkingId") String parkingId, @RequestBody Vehicle vehicle) {
		
		ApiResponse apiResponse = new ApiResponse();
		try {
			apiResponse.setPayload(parkingImpl.registerVehicle(parkingId, vehicle));
			apiResponse.setError(Boolean.FALSE);			
			apiResponse.setMessage("Vehicle registered.");
			return apiResponse;
		} catch (Exception e) {
			apiResponse.setError(Boolean.TRUE);
			apiResponse.setMessage(e.getMessage());
			return apiResponse;			
		}
				
	}

    @RequestMapping("/parking/{parkingId}/bills/{licensePlate}")
	public ApiResponse cashParking(@PathVariable(name="parkingId") String parkingId, 
				@PathVariable(name="licensePlate") String licensePlate) {
		
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			apiResponse.setPayload(parkingImpl.charge(parkingId, licensePlate));
			apiResponse.setError(Boolean.FALSE);
			apiResponse.setMessage("okokokok");
			return apiResponse;
		} catch (Exception e) {
			
			apiResponse.setError(Boolean.TRUE);
			e.printStackTrace();
			apiResponse.setMessage("Exception: " + e.getMessage() +  e.getCause());
			return apiResponse;
		}
	}
    
    @CrossOrigin(origins="*")
    @RequestMapping("/parking")
    public ApiResponse registerParking(@RequestBody Parking parking) {
		
		ApiResponse apiResponse = new ApiResponse();
		try {
			parking = parkingImpl.registerParking(parking);
			apiResponse.setError(Boolean.FALSE);	
			apiResponse.setPayload(parking);
			return apiResponse;
		} catch (Exception e) {
			apiResponse.setError(Boolean.TRUE);
			apiResponse.setMessage(e.getMessage());
			return apiResponse;			
		}
				
	}
}