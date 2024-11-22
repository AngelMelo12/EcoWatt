package com.fiap.gs.ecowatt.service;

import com.fiap.gs.ecowatt.model.ElectronicDevice;
import com.fiap.gs.ecowatt.model.EnergyConsumptionAnalysis;
import com.fiap.gs.ecowatt.model.dto.EnergyConsumptionAnalysisNewDTO;
import com.fiap.gs.ecowatt.model.enums.EnergyConsumptionStatus;
import com.fiap.gs.ecowatt.repository.EnergyConsumptionAnalysisRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnergyConsumptionAnalysisService {

    private final EnergyConsumptionAnalysisRepository energyConsumptionAnalysisRepository;
    private final ElectronicDeviceService electronicDeviceService;
    private final UserService userService;

    public List<EnergyConsumptionAnalysis> findAllByUserId(Long userId) {
        return energyConsumptionAnalysisRepository.findByUser_Id(userId);
    }

    public void create(EnergyConsumptionAnalysisNewDTO request) {
        var authenticatedUser = userService.getAuthenticatedUser();
        var devices = electronicDeviceService.findAllByUserId(authenticatedUser.getId());

        var kiloWattsPerMonth = EnergyConsumptionCalculator.calculateKilloWattsPerHourOnMonth(devices);

        var status = EnergyConsumptionStatus.BAIXO;
        var response = EnergyConsumptionStatus.BAIXO.getResponse();

        if (kiloWattsPerMonth.compareTo(EnergyConsumptionStatus.MEDIO.getWattsPerHourAverage()) > 0
                && kiloWattsPerMonth.compareTo(EnergyConsumptionStatus.ALTO.getWattsPerHourAverage()) <= 0) {
            status = EnergyConsumptionStatus.MEDIO;
            response = EnergyConsumptionStatus.MEDIO.getResponse();
        } else if (kiloWattsPerMonth.compareTo(EnergyConsumptionStatus.ALTO.getWattsPerHourAverage()) > 0
                && kiloWattsPerMonth.compareTo(EnergyConsumptionStatus.CRITICO.getWattsPerHourAverage()) <= 0) {
            status = EnergyConsumptionStatus.ALTO;
            response = EnergyConsumptionStatus.ALTO.getResponse();
        } else if (kiloWattsPerMonth.compareTo(EnergyConsumptionStatus.CRITICO.getWattsPerHourAverage()) > 0) {
            status = EnergyConsumptionStatus.CRITICO;
            response = EnergyConsumptionStatus.CRITICO.getResponse();
        }

        request.setStatus(status);
        request.setResponse(response);
        request.setWattsPerHourConsumption(kiloWattsPerMonth);

        energyConsumptionAnalysisRepository.save(EnergyConsumptionAnalysisNewDTO.toEnergyConsumptionAnalysis(request, authenticatedUser));
    }

    public void delete(Long id) {
        verificarSeExisteEnergyConsumptionAnalysis(id);
        energyConsumptionAnalysisRepository.deleteById(id);
    }

    private EnergyConsumptionAnalysis verificarSeExisteEnergyConsumptionAnalysis(Long id) {
        return energyConsumptionAnalysisRepository
                .findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException("Analise não encontrada" )
                );
    }
}
