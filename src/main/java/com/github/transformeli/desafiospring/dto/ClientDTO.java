package com.github.transformeli.desafiospring.dto;

import com.github.transformeli.desafiospring.model.Client;
import lombok.*;

@Data
public class ClientDTO {
    private Integer id;
    private String name;
    private String state;
    private String country;

    public ClientDTO(Client client)
    {
        this.id = client.getId();
        this.name = client.getName();
        this.state = client.getState();
        this.country = client.getCountry();
    }
}
