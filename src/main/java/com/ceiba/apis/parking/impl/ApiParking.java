package com.ceiba.apis.parking.impl;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.apis.parking.model.ApiResponse;
import com.ceiba.apis.parking.model.Greeting;
import com.ceiba.bl.parking.IParking;
import com.ceiba.bl.parking.models.Vehicle;

@RestController
public class ApiParking{

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    IParking parkingImpl;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    @RequestMapping("/parking/{parkingId}/vehicles")
    public ApiResponse registerVehicleIn(@PathVariable(name="parkingId") String parkingId, @RequestBody Vehicle vehicle) {
		
		ApiResponse apiResponse = new ApiResponse();
		try {
			parkingImpl.registerVehicle(parkingId, vehicle);
			apiResponse.setError(Boolean.FALSE);			
			return apiResponse;
		} catch (Exception e) {
			apiResponse.setError(Boolean.TRUE);
			apiResponse.setMessage(e.getMessage());
			return apiResponse;			
		}
				
	}

    @RequestMapping("/parking/{parkingId}/bills/{licensePlate}")
	public ApiResponse cashParking(@PathVariable(name="parkingId") String parkingId, @PathVariable(name="licensePlate") String licensePlate) {
		// TODO Auto-generated method stub
		ApiResponse apiResponse = new ApiResponse();
		
		try {
			apiResponse.setPayload(parkingImpl.charge(parkingId, licensePlate));
			apiResponse.setError(Boolean.FALSE);
			apiResponse.setMessage("okokokok");
			return apiResponse;
		} catch (Exception e) {
			// TODO: handle exception
			apiResponse.setError(Boolean.TRUE);
			e.printStackTrace();
			apiResponse.setMessage("Exception: " + e.getMessage() +  e.getCause());
			return apiResponse;
		}
	}

	public ApiResponse root() {
		
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setPayload("Api parqueadero");
		return apiResponse;
	}
}