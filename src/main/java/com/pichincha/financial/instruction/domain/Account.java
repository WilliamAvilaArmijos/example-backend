package com.pichincha.financial.instruction.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Account {
    private Integer id;
    private String accountNumber;
    private BigDecimal balance;
    private String accountType;
    private String status;
    private Integer clientId;
}
