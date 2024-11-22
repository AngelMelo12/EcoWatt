package com.fiap.gs.ecowatt.model.dto;

import com.fiap.gs.ecowatt.model.ElectronicDevice;
import com.fiap.gs.ecowatt.model.User;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ElectronicDeviceNewDTO {
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
    @Max(24)
    private Integer usageInHours;

    public static ElectronicDevice toElectronicDevice(ElectronicDeviceNewDTO electronicDeviceRequest, User user) {
        return ElectronicDevice.builder()
                .user(user)
                .name(electronicDeviceRequest.getName())
                .category(electronicDeviceRequest.getCategory())
                .model(electronicDeviceRequest.getModel())
                .watts(electronicDeviceRequest.getWattsPerHour())
                .usageInHours(electronicDeviceRequest.getUsageInHours())
                .build();
    }
}
