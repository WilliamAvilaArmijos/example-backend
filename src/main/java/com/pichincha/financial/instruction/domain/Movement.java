package com.pichincha.financial.instruction.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Movement {
    private Integer id;
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private BigDecimal balance;
    private Integer accountId;
}
