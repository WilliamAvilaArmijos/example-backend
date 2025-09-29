package com.pichincha.financial.instruction.application.input.port;

import com.pichincha.financial.instruction.domain.Account;

import java.util.List;

public interface AccountInputPort {
    List<Account> getAllAccounts();
    List<Account> getAccountsByClientId(Integer clientId);
    Account saveAccount(Account account);
}
