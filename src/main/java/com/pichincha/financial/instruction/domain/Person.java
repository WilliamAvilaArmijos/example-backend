package com.pichincha.financial.instruction.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Person {
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
}
