package com.pichincha.financial.instruction.infraestructure.output.adapter;

import com.pichincha.financial.instruction.application.output.port.AccountOutputPort;
import com.pichincha.financial.instruction.domain.Account;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.mapper.AccountMapper;
import com.pichincha.financial.instruction.infraestructure.output.repository.AccountRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.ClientRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.AccountData;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.ClientData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountOutputPort {
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final AccountMapper accountMapper;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::toDomain).toList();
    }

    @Override
    public List<Account> getAccountsByClientId(Integer clientId) {
        return accountRepository.findByClientId(clientId).stream().map(accountMapper::toDomain).toList();
    }

    @Override
    public Account saveAccount(Account account) {
        ClientData client = clientRepository.findById(account.getClientId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + account.getClientId()));

        AccountData entity = accountMapper.toEntity(account, client);
        return accountMapper.toDomain(accountRepository.save(entity));
    }
}
