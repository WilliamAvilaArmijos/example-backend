package com.pichincha.financial.instruction.infraestructure.output.adapter;

import com.pichincha.financial.instruction.application.output.port.MovementOutputPort;
import com.pichincha.financial.instruction.domain.Movement;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.mapper.MovementMapper;
import com.pichincha.financial.instruction.infraestructure.output.repository.AccountRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.ClientRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.MovementRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.AccountData;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.MovementData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementOutputPort {
    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;
    private final MovementMapper movementMapper;

    @Override
    public List<Movement> getAllMovements() {
        return movementRepository.findAll()
                .stream()
                .map(movementMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
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

    @Override
    public List<Movement> findByAccountAndDateBetween(Integer accountId, LocalDateTime init, LocalDateTime end) {
        return movementRepository.findByAccountIdAndDateBetween(accountId, init, end)
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

    @Override
    public BigDecimal calculateInitBalanceBefore(Integer accountId, LocalDateTime reportStartDate) {
        var lastMovementBefore = movementRepository
                .findTopByAccountIdAndDateBeforeOrderByDateDesc(accountId, reportStartDate);
        if (lastMovementBefore.isPresent()) {
            return lastMovementBefore.get().getBalance();
        }
        var firstMovement = movementRepository
                .findTopByAccountIdOrderByDateAsc(accountId);
        if (firstMovement.isPresent()) {
            MovementData mov = firstMovement.get();
            if ("CREDITO".equalsIgnoreCase(mov.getType())) {
                return mov.getBalance().subtract(mov.getAmount());
            } else if ("DEBITO".equalsIgnoreCase(mov.getType())) {
                return mov.getBalance().add(mov.getAmount());
            }
        }
        AccountData account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return account.getBalance();
    }

}
