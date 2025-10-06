package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.mapper;

import com.pichincha.financial.instruction.domain.Movement;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.dto.MovementRequest;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.AccountData;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.MovementData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MovementMapper {
    public Movement toDomain(MovementData data) {
        if (data == null) return null;
        return Movement.builder()
                .id(data.getId())
                .date(data.getDate())
                .type(data.getType())
                .amount(data.getAmount())
                .balance(data.getBalance())
                .description(data.getDescription())
                .accountId(data.getAccount().getId())
                .build();
    }

    public Movement toDomainReq(MovementRequest req) {
        return Movement.builder()
                .id(req.getId())
                .date(LocalDateTime.now())
                .type(req.getType())
                .amount(req.getAmount())
                .date(req.getDate())
                .description(req.getDescription())
                .accountId(req.getAccountId())
                .build();
    }

    public MovementRequest toRequest(Movement movement) {
        if (movement == null) return null;
        MovementRequest request = new MovementRequest();
        request.setId(movement.getId());
        request.setType(movement.getType());
        request.setDate(movement.getDate());
        request.setAmount(movement.getAmount());
        request.setDescription(movement.getDescription());
        request.setAccountId(movement.getAccountId());
        return request;
    }

    public MovementData toEntity(Movement domain, AccountData accountData) {
        if (domain == null || accountData == null) return null;

        MovementData data = new MovementData();
        data.setId(domain.getId());
        data.setDate(domain.getDate());
        data.setType(domain.getType());
        data.setAmount(domain.getAmount());
        data.setBalance(domain.getBalance());
        data.setDescription(domain.getDescription());
        data.setAccount(accountData);
        return data;
    }
}
