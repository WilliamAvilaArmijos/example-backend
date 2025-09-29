package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.impl;

import com.pichincha.financial.instruction.application.input.port.AccountInputPort;
import com.pichincha.financial.instruction.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/cuentas")
public class AccountController {
    private final AccountInputPort service;

    @GetMapping
    public List<Account> getAll() {
        return service.getAllAccounts();
    }

    @GetMapping("/client/{clientId}")
    public List<Account> byClient(@PathVariable Integer clientId) {
        return service.getAccountsByClientId(clientId);
    }

    @PostMapping
    public Account create(@RequestBody Account account) {
        return service.saveAccount(account);
    }
}