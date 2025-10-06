package com.pichincha.financial.instruction.application.input.port;

import com.pichincha.financial.instruction.domain.Account;
import com.pichincha.financial.instruction.domain.Client;

import java.util.List;
import java.util.Optional;

public interface AccountInputPort {
    List<Account> getAllAccounts();
    List<Account> getAccountsByClientId(Integer clientId);
    Optional<Account> findById(Integer id);
    Account saveAccount(Account account);
    Account updateAccount(Integer id, Account account);
}
