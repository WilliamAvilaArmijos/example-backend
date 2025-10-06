package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.impl;

import com.pichincha.financial.instruction.application.input.port.AccountInputPort;
import com.pichincha.financial.instruction.domain.Account;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.dto.AccountRequest;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.mapper.AccountMapper;
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
@RequestMapping("/api/cuentas")
public class AccountController {
    private final AccountInputPort accountInputPort;
    private final AccountMapper accountMapper;
    @GetMapping
    public ResponseEntity<List<AccountRequest>> getAll() {
        List<Account> accounts = accountInputPort.getAllAccounts();
        List<AccountRequest> accountRequests = accounts.stream()
                .map(accountMapper::toRequest)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accountRequests);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<AccountRequest>> byClient(@PathVariable Integer clientId) {
        List<Account> accounts = accountInputPort.getAccountsByClientId(clientId);
        List<AccountRequest> accountRequests = accounts.stream()
                .map(accountMapper::toRequest)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accountRequests);
    }

    @PostMapping
    public ResponseEntity<AccountRequest> create(@Valid @RequestBody AccountRequest accountRequest) {
        Account account = accountMapper.toDomainReq(accountRequest);
        Account created = accountInputPort.saveAccount(account);
        AccountRequest response = accountMapper.toRequest(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountRequest> update(
            @PathVariable Integer id,
            @Valid @RequestBody AccountRequest accountRequest) {
        Account account = accountMapper.toDomainReq(accountRequest);
        Account updated = accountInputPort.updateAccount(id, account);
        AccountRequest response = accountMapper.toRequest(updated);
        return ResponseEntity.ok(response);
    }
}