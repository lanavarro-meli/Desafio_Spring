package com.github.transformeli.desafiospring.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String cpf;
    @NonNull
    private String state;
    @NonNull
    private String country;
}
