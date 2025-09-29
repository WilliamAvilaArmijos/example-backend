package com.pichincha.financial.instruction.infraestructure.output.repository;

import com.pichincha.financial.instruction.infraestructure.output.repository.entity.MovementData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<MovementData, Integer> {
    List<MovementData> findByAccountId(Integer accountId);
}
