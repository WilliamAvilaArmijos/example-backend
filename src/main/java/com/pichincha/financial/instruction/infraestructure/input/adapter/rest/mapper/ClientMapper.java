package com.pichincha.financial.instruction.infraestructure.input.adapter.rest.mapper;

import com.pichincha.financial.instruction.domain.Client;
import com.pichincha.financial.instruction.infraestructure.input.adapter.rest.dto.ClientRequest;
import com.pichincha.financial.instruction.infraestructure.output.repository.entity.ClientData;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
//@Mapper(componentModel = "spring")
public class ClientMapper {
//    Client toDomain(ClientData data);
//    ClientData toEntity(Client client);
//    Client toDomainReq(ClientRequest req);
    public Client toDomain(ClientData data) {
        if (data == null) return null;

        return Client.builder()
                .id(data.getId())
                .password(data.getPassword())
                .status(data.getStatus())
                .name(data.getName())
                .gender(data.getGender())
                .age(data.getAge())
                .identification(data.getIdentification())
                .address(data.getAddress())
                .phone(data.getPhone())
                .build();
    }

    public Client toDomainReq(ClientRequest req) {
        return Client.builder()
                .name(req.getName())
                .gender(req.getGender())
                .age(req.getAge())
                .identification(req.getIdentification())
                .address(req.getAddress())
                .phone(req.getPhone())
                .password(req.getPassword())
                .status(req.getStatus())
                .build();
    }
    public ClientData toEntity(Client client) {
        if (client == null) return null;

        ClientData data = new ClientData();
        data.setId(client.getId());
        data.setName(client.getName());
        data.setGender(client.getGender());
        data.setAge(client.getAge());
        data.setIdentification(client.getIdentification());
        data.setAddress(client.getAddress());
        data.setPhone(client.getPhone());
        data.setPassword(client.getPassword());
        data.setStatus(client.getStatus());
        return data;
    }
}
