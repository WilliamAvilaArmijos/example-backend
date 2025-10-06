package com.pichincha.financial.instruction.application.input.port;

import com.pichincha.financial.instruction.domain.Account;
import com.pichincha.financial.instruction.domain.Client;

import java.util.List;

public interface AccountInputPort {
    List<Account> getAllAccounts();
    List<Account> getAccountsByClientId(Integer clientId);
    Account saveAccount(Account account);
    Account updateAccount(Integer id, Account account);
}
