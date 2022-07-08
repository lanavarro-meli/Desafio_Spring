package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ClientDTO;
import com.github.transformeli.desafiospring.model.Client;
import java.util.List;

public interface IClientService {

    public List<ClientDTO> getAllClients();
    public List<ClientDTO> getClientsByState(String state);
    public ClientDTO saveClient(Client client);
}
