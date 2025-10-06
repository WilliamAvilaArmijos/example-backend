package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.impl;

import com.pichincha.financial.instruction.application.input.port.ClientInputPort;
import com.pichincha.financial.instruction.domain.Client;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.dto.ClientRequest;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.mapper.ClientMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/clientes")
public class ClientController {
    private final ClientInputPort clientInputPort;
    private final ClientMapper clientMapper;

    @GetMapping
    public ResponseEntity<List<ClientRequest>> allClients() {
        List<Client> clients = clientInputPort.getAllClients();
        List<ClientRequest> clientRequests = clients.stream()
                .map(clientMapper::toRequest)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientRequests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientRequest> getClienteById(@PathVariable Integer id) {
        return clientInputPort.findById(id)
                .map(clientMapper::toRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientRequest> create(@Valid @RequestBody ClientRequest clientRequest) {
        Client client = clientMapper.toDomainReq(clientRequest);
        Client created = clientInputPort.saveClient(client);
        ClientRequest response = clientMapper.toRequest(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientRequest> update(
            @PathVariable Integer id,
            @Valid @RequestBody ClientRequest clientRequest) {
        try {
            Client client = clientMapper.toDomainReq(clientRequest);
            Client updated = clientInputPort.updateClient(id, client);
            ClientRequest response = clientMapper.toRequest(updated);
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clientInputPort.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}