package com.fiap.gs.ecowatt.service.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EnergyConsumptionAnalysisConsumer {

    @RabbitListener(queues = {"notificar-analise-sucesso-queue"})
    public void receiveSucess(@Payload Message message) {
        String payload = String.valueOf(message.getPayload());
        System.out.println("Notificação de análise processada com sucesso: " + payload);
    }

    @RabbitListener(queues = {"notificar-analise-erro-queue"})
    public void receiveErro(@Payload Message message) {
        String payload = String.valueOf(message.getPayload());
        System.out.println("Houve um erro ao notificar análise: " + payload);
    }
}
