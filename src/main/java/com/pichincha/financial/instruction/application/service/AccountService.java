package com.pichincha.financial.instruction.application.service;

import com.pichincha.financial.instruction.application.input.port.AccountInputPort;
import com.pichincha.financial.instruction.application.output.port.AccountOutputPort;
import com.pichincha.financial.instruction.domain.Account;
import com.pichincha.financial.instruction.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements AccountInputPort {
    private final AccountOutputPort output;

    @Override
    public List<Account> getAllAccounts() {
        return output.getAllAccounts();
    }

    @Override
    public List<Account> getAccountsByClientId(Integer clientId) {
        return output.getAccountsByClientId(clientId);
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return output.findById(id);
    }

    @Override
    public Account saveAccount(Account account) {
        return output.saveAccount(account);
    }

    @Override
    public Account updateAccount(Integer id, Account account) {
        return output.updateAccount(id, account);
    }
}
