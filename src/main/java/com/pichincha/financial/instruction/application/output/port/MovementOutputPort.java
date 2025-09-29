package com.pichincha.financial.instruction.application.output.port;

import com.pichincha.financial.instruction.domain.Movement;

import java.util.List;

public interface MovementOutputPort {
    Movement createMovement(Movement movement);
    List<Movement> getMovementsByAccountId(Integer accountId);
}
