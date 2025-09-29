package com.pichincha.financial.instruction.infraestructure.output.repository;

import com.pichincha.financial.instruction.infraestructure.output.repository.entity.AccountData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountData, Integer> {
    List<AccountData> findByClientId(Integer clientId);
}
