package com.pichincha.financial.instruction.infraestructure.output.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 10)
    @Column(length = 10, nullable = false, unique = true)
    private String accountNumber;

    @NotBlank
    @Size(max = 10)
    @Column(length = 10, nullable = false)
    private String accountType;

    @NotNull
    @DecimalMin(value = "0.0", message = "El saldo inicial debe ser mayor o igual a 0")
    @Column(nullable = false)
    private BigDecimal balance;

    @NotNull
    @Column(nullable = false)
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientData client;
}
