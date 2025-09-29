package com.pichincha.financial.instruction.application.service;

import com.pichincha.financial.instruction.application.input.port.MovementInputPort;
import com.pichincha.financial.instruction.application.output.port.MovementOutputPort;
import com.pichincha.financial.instruction.domain.Movement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovementService implements MovementInputPort {
    private final MovementOutputPort movementOutputPort;

    @Override
    public List<Movement> getAllMovements() {
        return movementOutputPort.getAllMovements();
    }

    @Override
    public Movement createMovement(Movement movement) {
        return movementOutputPort.createMovement(movement);
    }

    @Override
    public List<Movement> getMovementsByAccountId(Integer accountId) {
        return movementOutputPort.getMovementsByAccountId(accountId);
    }

    @Override
    public BigDecimal calculateInitBalanceBefore(Integer accountId, LocalDateTime reportStartDate) {
        return movementOutputPort.calculateInitBalanceBefore(accountId, reportStartDate);
    }

    @Override
    public List<Movement> findByAccountAndDateBetween(Integer accountId, LocalDateTime init, LocalDateTime end) {
        return movementOutputPort.findByAccountAndDateBetween(accountId, init, end);
    }
}