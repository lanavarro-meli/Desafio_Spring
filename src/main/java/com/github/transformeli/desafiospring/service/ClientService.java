package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ClientDTO;
import com.github.transformeli.desafiospring.model.Client;
import com.github.transformeli.desafiospring.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository repo;

    @Override
    public List<ClientDTO> getAllClients() {
        return repo.getAllClients().stream()
                .map(ClientDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> getClientsByState(String state) {
        return this.getAllClients(); // TODO
    }

    @Override
    public ClientDTO saveClient(Client client) {
        return repo.saveClient(client);
    }

}
