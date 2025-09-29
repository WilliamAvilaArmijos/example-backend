package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.impl;

import com.pichincha.financial.instruction.application.input.port.MovementInputPort;
import com.pichincha.financial.instruction.domain.Movement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/movimientos")
public class MovementController {

    private final MovementInputPort service;

    @PostMapping
    public ResponseEntity<Movement> create(@RequestBody Movement movement) {
        return ResponseEntity.ok(service.createMovement(movement));
    }

    @GetMapping("/cuenta/{accountId}")
    public ResponseEntity<List<Movement>> getByAccount(@PathVariable Integer accountId) {
        return ResponseEntity.ok(service.getMovementsByAccountId(accountId));
    }
}
