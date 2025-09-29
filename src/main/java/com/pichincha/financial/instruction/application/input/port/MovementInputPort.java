package com.pichincha.financial.instruction.application.input.port;

import com.pichincha.financial.instruction.domain.Account;
import com.pichincha.financial.instruction.domain.Movement;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface MovementInputPort {
    List<Movement> getAllMovements();
    Movement createMovement(Movement movement);
    List<Movement> getMovementsByAccountId(Integer accountId);
    BigDecimal calculateInitBalanceBefore(Integer accountId, LocalDateTime reportStartDate);
    List<Movement> findByAccountAndDateBetween(Integer accountId, LocalDateTime init, LocalDateTime end);
}
