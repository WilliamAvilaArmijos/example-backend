package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.impl;

import com.pichincha.financial.instruction.application.input.port.ClientInputPort;
import com.pichincha.financial.instruction.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/clientes")
public class ClientController {
    private final ClientInputPort clientInputPort;

    @GetMapping
    public ResponseEntity<List<Client>> allClients(){
        List<Client> clients = clientInputPort.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClienteById(@PathVariable Integer id) {
        return clientInputPort.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) {
        Client created = clientInputPort.saveClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Integer id, @RequestBody Client client) {
        try {
            Client updated = clientInputPort.updateClient(id, client);
            return ResponseEntity.ok(updated);
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
