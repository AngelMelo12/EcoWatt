package com.fiap.gs.ecowatt.service;

import com.fiap.gs.ecowatt.model.ElectronicDevice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class EnergyConsumptionCalculator {

    private static final BigDecimal ONE_THOUSAND = BigDecimal.valueOf(1000);
    private static final BigDecimal DAYS_OF_MONTH = BigDecimal.valueOf(30);

    public static BigDecimal calculateKilloWattsPerHourOnMonth(List<ElectronicDevice> devices) {
        var totalConsumption = BigDecimal.ZERO;
        for (var device : devices) {
            var hours = BigDecimal.valueOf(device.getUsageInHours());
            var wattConsumptionPerMonth = device.getWatts()
                    .multiply(hours)
                    .multiply(DAYS_OF_MONTH)
                    .divide(ONE_THOUSAND, RoundingMode.HALF_UP);
            totalConsumption = totalConsumption.add(wattConsumptionPerMonth);
        }
        return totalConsumption;
    }
}
