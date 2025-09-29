package com.pichincha.financial.instruction.application.service;

import com.pichincha.financial.instruction.application.input.port.MovementInputPort;
import com.pichincha.financial.instruction.application.output.port.MovementOutputPort;
import com.pichincha.financial.instruction.domain.Movement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovementService implements MovementInputPort {
    private final MovementOutputPort output;

    @Override
    public Movement createMovement(Movement movement) {
        return output.createMovement(movement);
    }

    @Override
    public List<Movement> getMovementsByAccountId(Integer accountId) {
        return output.getMovementsByAccountId(accountId);
    }
}