package com.pichincha.financial.instruction.application.output.port;

import com.pichincha.financial.instruction.domain.Account;

import java.util.List;

public interface AccountOutputPort {
    List<Account> getAllAccounts();
    List<Account> getAccountsByClientId(Integer clientId);
    Account saveAccount(Account account);
}
