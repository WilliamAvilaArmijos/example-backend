package com.pichincha.financial.instruction.infraestructure.output.adapter;

import com.pichincha.financial.instruction.application.output.port.ClientOutputPort;
import com.pichincha.financial.instruction.domain.Client;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.mapper.ClientMapper;
import com.pichincha.financial.instruction.infraestructure.output.repository.ClientRepository;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.ClientData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientOutputPort {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDomain);
    }

    @Override
    public Client saveClient(Client client) {
        ClientData entity = clientMapper.toEntity(client);
        ClientData saved = clientRepository.save(entity);
        return clientMapper.toDomain(saved);
    }
    @Override
    public Client updateClient(Integer id, Client client) {
        if (!clientRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente no encontrado");
        }
        client.setId(id);
        ClientData updated = clientRepository.save(clientMapper.toEntity(client));
        return clientMapper.toDomain(updated);
    }
    @Override
    public void deleteClient(Integer id) {
        if (!clientRepository.existsById(id)) {
            throw new NoSuchElementException("Cliente no encontrado");
        }
        clientRepository.deleteById(id);
    }
}
