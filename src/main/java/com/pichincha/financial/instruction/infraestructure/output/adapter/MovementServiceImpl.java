package com.pichincha.financial.instruction.infraestructure.output.adapter;

import com.pichincha.financial.instruction.application.output.port.MovementOutputPort;
import com.pichincha.financial.instruction.domain.Movement;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.mapper.MovementMapper;
import com.pichincha.financial.instruction.infraestructure.output.repository.AccountRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.MovementRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.AccountData;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.MovementData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementOutputPort {
    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final MovementMapper movementMapper;

    @Override
    //@Transactional
    public Movement createMovement(Movement movement) {
        AccountData account = accountRepository.findById(movement.getAccountId())
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        BigDecimal newBalance = calculateNewBalance(account.getBalance(), movement.getType(), movement.getAmount());
        account.setBalance(newBalance);
        accountRepository.save(account);

        MovementData movementData = movementMapper.toEntity(movement, account);
        movementData.setBalance(newBalance);
        movementData.setDate(LocalDateTime.now());

        return movementMapper.toDomain(movementRepository.save(movementData));
    }

    @Override
    public List<Movement> getMovementsByAccountId(Integer accountId) {
        return movementRepository.findByAccountId(accountId)
                .stream()
                .map(movementMapper::toDomain)
                .toList();
    }

    private BigDecimal calculateNewBalance(BigDecimal currentBalance, String type, BigDecimal amount) {
        if ("CREDITO".equalsIgnoreCase(type)) {
            return currentBalance.add(amount);
        } else if ("DEBITO".equalsIgnoreCase(type)) {
            if (currentBalance.compareTo(amount) < 0)
                throw new RuntimeException("Saldo insuficiente");
            return currentBalance.subtract(amount);
        } else {
            throw new RuntimeException("Tipo de movimiento inválido");
        }
    }
}
