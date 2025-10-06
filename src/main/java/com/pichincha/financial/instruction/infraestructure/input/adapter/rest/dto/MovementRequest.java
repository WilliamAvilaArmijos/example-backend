package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class MovementRequest {
    private Integer id;
    @NotBlank
    @Size(max = 10)
    private String type;
    private LocalDateTime date;
    @NotNull
    @DecimalMin(value = "0.0", message = "El monto debe ser mayor o igual a 0")
    private BigDecimal amount;
    @NotBlank
    @Size(max = 50)
    private String description;

    @NotNull(message = "El ID de la cuenta es requerido")
    private Integer accountId;
}
