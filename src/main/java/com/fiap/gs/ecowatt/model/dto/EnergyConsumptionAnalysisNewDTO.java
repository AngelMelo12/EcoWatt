package com.fiap.gs.ecowatt.model.dto;

import com.fiap.gs.ecowatt.model.EnergyConsumptionAnalysis;
import com.fiap.gs.ecowatt.model.User;
import com.fiap.gs.ecowatt.model.enums.EnergyConsumptionStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EnergyConsumptionAnalysisNewDTO {
    @NotBlank
    @Size(min = 2)
    private String title;
    @Nullable
    private EnergyConsumptionStatus status;
    @Nullable
    private String response;
    @Nullable
    private BigDecimal wattsPerHourConsumption;

    public static EnergyConsumptionAnalysis toEnergyConsumptionAnalysis(EnergyConsumptionAnalysisNewDTO request, User user) {
        return EnergyConsumptionAnalysis.builder()
                .user(user)
                .title(request.getTitle())
                .createdAt(LocalDateTime.now())
                .status(request.getStatus())
                .response(request.getResponse())
                .wattsPerHourConsumption(request.getWattsPerHourConsumption())
                .build();
    }
}
