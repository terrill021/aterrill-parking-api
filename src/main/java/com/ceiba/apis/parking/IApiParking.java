package com.ceiba.apis.parking;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.apis.parking.model.ApiResponse;
import com.ceiba.bl.parking.models.Vehicle;

public interface IApiParking {
	
	 @RequestMapping("/")
	    public ApiResponse greeting();

/*
	@RequestMapping("/")
    public ApiResponse root();
	
    @RequestMapping("/parkings/vehicles")
    public ApiResponse registerVehicleIn( String parkingCode, Vehicle vehicle);
    
    @RequestMapping("/parkings/bills")
    public ApiResponse cashParking(String parkingCode, String licensePlate);
    */
}
