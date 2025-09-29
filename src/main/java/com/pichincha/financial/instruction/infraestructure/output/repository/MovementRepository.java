package com.pichincha.financial.instruction.infraestructure.output.repository;

import com.pichincha.financial.instruction.infraestructure.output.repository.entity.MovementData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovementRepository extends JpaRepository<MovementData, Integer> {
    List<MovementData> findByAccountId(Integer accountId);
    List<MovementData> findByAccountIdAndDateBetween(Integer accountId, LocalDateTime init, LocalDateTime end);
    Optional<MovementData> findTopByAccountIdAndDateBeforeOrderByDateDesc(Integer accountId, LocalDateTime date);
    Optional<MovementData> findTopByAccountIdOrderByDateAsc(Integer accountId);
}
