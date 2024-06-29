package lk.ijse.vehicle_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class VehicleDTO {

    @Null
    private String vehicleId;

    @NotBlank(message = "Registration number cannot be null")
    private String registrationNo;

    @NotBlank(message = "Model cannot be null")
    private String model;

    @NotBlank(message = "Year cannot be null")
    private int year;

}
