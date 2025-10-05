package com.pichincha.financial.instruction.infraestructure.output.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Size(max = 100)
    @Column(length = 100, nullable = false)
    private String name;
    @NotBlank
    @Column(length = 10, nullable = false)
    private String gender;
    @NotNull
    @Min(value = 18, message = "Edad mínima permitida es 18")
    @Max(value = 120, message = "Edad máxima permitida es 120")
    @Column(length = 3, nullable = false)
    private Integer age;
    @NotBlank
    @Size(min = 8, max = 10)
    @Column(length = 10, nullable = false)
    private String identification;
    @Size(max = 150)
    @Column(length = 150)
    private String address;
    @Size(max = 15)
    @Column(length = 15)
    private String phone;
    @NotBlank
    @Size(min = 4, max = 100)
    @Column(nullable = false)
    private String password;
    @NotNull
    @Column(nullable = false)
    private Boolean status;
}
