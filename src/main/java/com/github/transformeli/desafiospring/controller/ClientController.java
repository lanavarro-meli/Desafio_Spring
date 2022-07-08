package com.github.transformeli.desafiospring.controller;

import com.github.transformeli.desafiospring.dto.ClientDTO;
import com.github.transformeli.desafiospring.model.Client;
import com.github.transformeli.desafiospring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients()
    {
        return new ResponseEntity<>(service.getAllClients(), HttpStatus.OK);
    }
    @GetMapping("/clients//")
    public ResponseEntity<List<ClientDTO>> getByState(@RequestParam String state){
        List<ClientDTO> clientsByState  = service.getClientsByState(state);
        return ResponseEntity.ok().body(clientsByState);
    }

    @PostMapping("/clients")
    public ResponseEntity<ClientDTO> createClient(@Validated @RequestBody Client client)
    {
        ClientDTO clientCreated = service.saveClient(client);
        return new ResponseEntity<>(clientCreated, HttpStatus.CREATED);
    }

}
