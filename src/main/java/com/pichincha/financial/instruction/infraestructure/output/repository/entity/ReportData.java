package com.pichincha.financial.instruction.infraestructure.output.repository.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ReportData {
    private String date;
    private String client;
    private String accountNumber;
    private String accountType;
    private BigDecimal initBalance;
    private String status;
    private BigDecimal amount;
    private BigDecimal balance;
}
