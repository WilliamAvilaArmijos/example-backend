package com.pichincha.financial.instruction.application.service;

import com.pichincha.financial.instruction.application.input.port.ClientInputPort;
import com.pichincha.financial.instruction.application.output.port.ClientOutputPort;
import com.pichincha.financial.instruction.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements ClientInputPort {
    private final ClientOutputPort clientOutputPort;
    @Override
    public List<Client> getAllClients() {
        return clientOutputPort.getAllClients();
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return clientOutputPort.findById(id);
    }

    @Override
    public Client saveClient(Client client) {
        return clientOutputPort.saveClient(client);
    }

    @Override
    public Client updateClient(Integer id, Client client) {
        return clientOutputPort.updateClient(id, client);
    }

    @Override
    public void deleteClient(Integer id) {
        clientOutputPort.deleteClient(id);
    }
}
