package com.fiap.gs.ecowatt.model.enums;

import java.math.BigDecimal;

public enum EnergyConsumptionStatus {
    BAIXO(BigDecimal.valueOf(30),"Baixo", "Seu consumo está baixo!\nVocê está economizando energia e ajudando o meio ambiente!"),
    MEDIO(BigDecimal.valueOf(100), "Médio", "Seu consumo está médio!\n Você pode melhorar, porém ainda está dentro de uma média confiável!"),
    ALTO(BigDecimal.valueOf(220),"Alto", "Seu consumo está alto!\n Melhore seu consumo seguindo nossas dicas, você precisa se atentar!"),
    CRITICO(BigDecimal.valueOf(300), "Crítico", "Seu consumo está crítico!\n Seu consumo está muito acima da média, siga nossas dicas urgentemente!");

    private final BigDecimal wattsPerHourAverage;
    private final String title;
    private final String response;

    EnergyConsumptionStatus(BigDecimal wattsPerHourAverage, String title, String response) {
        this.wattsPerHourAverage = wattsPerHourAverage;
        this.title = title;
        this.response = response;
    }

    public BigDecimal getWattsPerHourAverage() {
        return wattsPerHourAverage;
    }

    public String getTitle() {
        return title;
    }

    public String getResponse() {
        return response;
    }
}
