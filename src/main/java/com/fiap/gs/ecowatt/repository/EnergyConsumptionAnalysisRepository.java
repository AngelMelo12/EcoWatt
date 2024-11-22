package com.fiap.gs.ecowatt.repository;

import com.fiap.gs.ecowatt.model.ElectronicDevice;
import com.fiap.gs.ecowatt.model.EnergyConsumptionAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnergyConsumptionAnalysisRepository extends JpaRepository<EnergyConsumptionAnalysis, Long> {
    List<EnergyConsumptionAnalysis> findByUser_Id(Long id);
}
