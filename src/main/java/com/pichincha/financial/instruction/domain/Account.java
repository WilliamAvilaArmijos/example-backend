package com.pichincha.financial.instruction.domain;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Account {
    private Integer id;
    private String accountNumber;
    private BigDecimal balance;
    private String accountType;
    private Boolean status;
    private Integer clientId;
}
