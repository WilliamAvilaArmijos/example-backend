package com.pichincha.financial.instruction.infraestructure.output.repository;

import com.pichincha.financial.instruction.infraestructure.output.repository.entity.ClientData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientData, Integer> {
}
