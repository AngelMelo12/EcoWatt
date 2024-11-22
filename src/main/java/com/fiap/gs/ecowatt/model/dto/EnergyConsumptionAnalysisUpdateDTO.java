package com.fiap.gs.ecowatt.model.dto;

import com.fiap.gs.ecowatt.model.ElectronicDevice;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EnergyConsumptionAnalysisUpdateDTO {
    @NotNull
    private Long id;
    @NotBlank
    @Size(min = 2)
    private String name;
    @NotBlank
    @Size(min = 2)
    private String category;
    @NotBlank
    @Size(min = 2)
    private String model;
    @DecimalMin(value = "1.0")
    private BigDecimal wattsPerHour;
    @Min(1)
    private Integer usageInHours;
    public static EnergyConsumptionAnalysisUpdateDTO fromElectronicDevice(ElectronicDevice electronicDevice) {
        var dto = new EnergyConsumptionAnalysisUpdateDTO();
        dto.setId(electronicDevice.getId());
        dto.setName(electronicDevice.getName());
        dto.setCategory(electronicDevice.getCategory());
        dto.setModel(electronicDevice.getModel());
        dto.setWattsPerHour(electronicDevice.getWatts());
        dto.setUsageInHours(electronicDevice.getUsageInHours());
        return dto;
    }

    public static ElectronicDevice toElectronicDevice(ElectronicDevice electronicDeviceToUpdate, EnergyConsumptionAnalysisUpdateDTO electronicDeviceRequest) {
        electronicDeviceToUpdate.setName(electronicDeviceRequest.getName());
        electronicDeviceToUpdate.setCategory(electronicDeviceRequest.getCategory());
        electronicDeviceToUpdate.setModel(electronicDeviceRequest.getModel());
        electronicDeviceToUpdate.setWatts(electronicDeviceRequest.getWattsPerHour());
        electronicDeviceToUpdate.setUsageInHours(electronicDeviceRequest.getUsageInHours());
        return electronicDeviceToUpdate;
    }

}
