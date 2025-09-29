package com.pichincha.financial.instruction.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Client extends Person{
    private Integer id;
    private String password;
    private String status;
}
