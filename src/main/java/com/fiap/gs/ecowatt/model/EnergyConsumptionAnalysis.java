package com.fiap.gs.ecowatt.model;

import com.fiap.gs.ecowatt.model.enums.EnergyConsumptionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "energy_consumption_analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnergyConsumptionAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    @Enumerated(value = EnumType.STRING)
    private EnergyConsumptionStatus status;
    private String response;
    @Column(precision = 10, scale = 2)
    private BigDecimal wattsPerHourConsumption;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
