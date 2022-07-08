package com.github.transformeli.desafiospring.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.transformeli.desafiospring.dto.ClientDTO;
import com.github.transformeli.desafiospring.exception.ClientExistsException;
import com.github.transformeli.desafiospring.exception.InternalServerException;
import com.github.transformeli.desafiospring.exception.NotFoundException;
import com.github.transformeli.desafiospring.model.Client;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ClientRepository {

    private final String linkFile = "src/main/resources/clients.json";

    public List<Client> getAllClients() {
        return this.readFile();
    }

    public ClientDTO saveClient(Client client) {
        List<Client> clients = this.getAllClients();
        if(clients.stream().anyMatch(c -> c.getCpf().equalsIgnoreCase(client.getCpf())))
        {
            throw new ClientExistsException("Client create request (" + client.getCpf() + ") already exists.");
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Client> newClients = new ArrayList<>(clients);
            newClients.add(client);
            mapper.writeValue(new File(linkFile), newClients);
        } catch (Exception ex) {
            throw new InternalServerException(ex.getMessage());
        }
        return new ClientDTO(client);
    }

    public List<Client> getByState(String state){
        try{
            List<Client> clientList = this.readFile();
            return clientList.stream()
                    .filter(c -> c.getState().equalsIgnoreCase(state))
                    .collect(Collectors.toList());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        throw new NotFoundException("Couldn`t read file");
    }

    private List<Client> readFile() {
        ObjectMapper mapper = new ObjectMapper();
        List<Client> list = new ArrayList<>();
        try {
            list = Arrays.asList(mapper.readValue(new File(linkFile), Client[].class));
        } catch (Exception ex) {
            System.out.println("Couldn't read file");
        }
        return list;
    }

}
