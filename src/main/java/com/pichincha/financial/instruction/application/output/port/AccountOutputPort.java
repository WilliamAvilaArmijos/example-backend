package com.pichincha.financial.instruction.application.output.port;

import com.pichincha.financial.instruction.domain.Account;
import com.pichincha.financial.instruction.domain.Client;

import java.util.List;

public interface AccountOutputPort {
    List<Account> getAllAccounts();
    List<Account> getAccountsByClientId(Integer clientId);
    Account saveAccount(Account account);
    Account updateAccount(Integer id, Account account);
}
