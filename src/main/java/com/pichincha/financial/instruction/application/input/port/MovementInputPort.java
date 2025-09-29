package com.pichincha.financial.instruction.application.input.port;

import com.pichincha.financial.instruction.domain.Movement;

import java.util.List;

public interface MovementInputPort {
    Movement createMovement(Movement movement);
    List<Movement> getMovementsByAccountId(Integer accountId);
}
