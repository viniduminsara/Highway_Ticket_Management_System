package lk.ijse.vehicle_service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {

    @GetMapping
    public String health(){
        return "Vehicle Ok";
    }

}
