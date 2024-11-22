package com.fiap.gs.ecowatt.service.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.gs.ecowatt.model.EnergyConsumptionAnalysis;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnergyConsumptionAnalysisProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void notify(EnergyConsumptionAnalysis energyConsumptionAnalysis) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "notificar-analise-exchange",
                "admin",
                objectMapper.writeValueAsString(energyConsumptionAnalysis)
        );
    }
}
