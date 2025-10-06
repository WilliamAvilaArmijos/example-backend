package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {
    @NotBlank
    @Size(max = 10)
    private String accountNumber;

    @NotBlank
    @Size(max = 10)
    private String accountType;

    @NotNull
    @DecimalMin(value = "0.0", message = "El saldo inicial debe ser mayor o igual a 0")
    private BigDecimal balance;

    @NotNull
    private Boolean status;

    @NotNull(message = "El ID del cliente es requerido")
    private Integer clientId;
}
