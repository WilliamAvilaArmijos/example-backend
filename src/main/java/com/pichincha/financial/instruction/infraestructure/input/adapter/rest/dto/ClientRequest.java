package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {
    private Integer id;
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    private String gender;
    @NotNull
    @Min(value = 18, message = "Edad mínima permitida es 18")
    @Max(value = 120, message = "Edad máxima permitida es 120")
    private Integer age;
    @NotBlank
    @Size(min = 8, max = 10)
    private String identification;
    @Size(max = 150)
    private String address;
    @Size(max = 15 )
    private String phone;
    @NotBlank
    @Size(min = 4, max = 100)
    private String password;
    @NotNull
    private Boolean status;
}