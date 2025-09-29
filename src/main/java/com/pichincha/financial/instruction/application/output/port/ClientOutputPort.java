package com.pichincha.financial.instruction.application.output.port;

import com.pichincha.financial.instruction.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientOutputPort {
    List<Client> getAllClients();
    Optional<Client> findById(Integer id);
    Client saveClient(Client client);
    Client updateClient(Integer id, Client client);
    void deleteClient(Integer id);

}
