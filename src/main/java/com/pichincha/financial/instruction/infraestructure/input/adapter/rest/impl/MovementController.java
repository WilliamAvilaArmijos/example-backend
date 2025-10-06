package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.impl;

import com.pichincha.financial.instruction.application.input.port.MovementInputPort;
import com.pichincha.financial.instruction.domain.Movement;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.dto.MovementRequest;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.mapper.MovementMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/movimientos")
public class MovementController {

    private final MovementInputPort movementInputPort;
    private final MovementMapper movementMapper;

    @GetMapping
    public ResponseEntity<List<MovementRequest>> getAll() {
        List<Movement> movements = movementInputPort.getAllMovements();
        List<MovementRequest> movementRequests = movements.stream()
                .map(movementMapper::toRequest)
                .collect(Collectors.toList());
        return ResponseEntity.ok(movementRequests);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<MovementRequest>> byAccount(@PathVariable Integer accountId) {
        List<Movement> movements = movementInputPort.getMovementsByAccountId(accountId);
        List<MovementRequest> movementRequests = movements.stream()
                .map(movementMapper::toRequest)
                .collect(Collectors.toList());
        return ResponseEntity.ok(movementRequests);
    }

    @PostMapping
    public ResponseEntity<MovementRequest> create(@Valid @RequestBody MovementRequest movementRequest) {
        Movement movement = movementMapper.toDomainReq(movementRequest);
        Movement created = movementInputPort.createMovement(movement);
        MovementRequest response = movementMapper.toRequest(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
