package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.impl;

import com.pichincha.financial.instruction.application.input.port.MovementInputPort;
import com.pichincha.financial.instruction.infraestructure.output.repository.AccountRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.ClientRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.ReportData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReportController {

    private final MovementInputPort movementInputPort;
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    @GetMapping
    public ResponseEntity<List<ReportData>> getReportePorFechas(
            @RequestParam Integer clienteId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin
    ) {
        LocalDateTime inicio = LocalDate.parse(fechaInicio).atStartOfDay();
        LocalDateTime fin = LocalDate.parse(fechaFin).atTime(23, 59, 59);

        var cliente = clientRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        var cuentas = accountRepository.findByClientId(clienteId);

        List<ReportData> reporte = new ArrayList<>();

        for (var cuenta : cuentas) {
            var initBalance = movementInputPort.calculateInitBalanceBefore(cuenta.getId(), inicio);
            BigDecimal currentBalance = initBalance;
            var movimientos = movementInputPort.findByAccountAndDateBetween(cuenta.getId(), inicio, fin);
            for (var mov : movimientos) {
                var data = new ReportData();
                data.setDate(mov.getDate().toLocalDate().toString());
                data.setClient(cliente.getName());
                data.setAccountNumber(cuenta.getAccountNumber());
                data.setAccountType(cuenta.getAccountType());
                data.setInitBalance(currentBalance);
                data.setStatus(cuenta.getStatus());
                data.setAmount(mov.getAmount());
                BigDecimal newBalance;
                if ("CREDITO".equalsIgnoreCase(mov.getType())) {
                    newBalance = currentBalance.add(mov.getAmount());
                } else {
                    newBalance = currentBalance.subtract(mov.getAmount());
                }

                data.setBalance(newBalance);
                currentBalance = newBalance;
                reporte.add(data);
            }
        }

        return ResponseEntity.ok(reporte);
    }
}
