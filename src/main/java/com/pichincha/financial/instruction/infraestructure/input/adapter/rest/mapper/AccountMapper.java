package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.mapper;

import com.pichincha.financial.instruction.domain.Account;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.dto.AccountRequest;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.AccountData;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.ClientData;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public Account toDomain(AccountData data) {
        if (data == null) return null;

        return Account.builder()
                .id(data.getId())
                .accountNumber(data.getAccountNumber())
                .balance(data.getBalance())
                .accountType(data.getAccountType())
                .status(data.getStatus())
                .clientId(data.getClient().getId())
                .build();
    }

    public Account toDomainReq(AccountRequest req) {
        return Account.builder()
                .id(req.getId())
                .accountNumber(req.getAccountNumber())
                .accountType(req.getAccountType())
                .balance(req.getBalance())
                .status(req.getStatus())
                .clientId(req.getClientId())
                .build();
    }

    public AccountRequest toRequest(Account account) {
        if (account == null) return null;
        AccountRequest request = new AccountRequest();
        request.setId(account.getId());
        request.setAccountNumber(account.getAccountNumber());
        request.setAccountType(account.getAccountType());
        request.setBalance(account.getBalance());
        request.setStatus(account.getStatus());
        request.setClientId(account.getClientId());
        return request;
    }

    public AccountData toEntity(Account account, ClientData clientData) {
        if (account == null || clientData == null) return null;

        AccountData data = new AccountData();
        data.setId(account.getId());
        data.setAccountNumber(account.getAccountNumber());
        data.setBalance(account.getBalance());
        data.setAccountType(account.getAccountType());
        data.setStatus(account.getStatus());
        data.setClient(clientData);
        return data;
    }
}