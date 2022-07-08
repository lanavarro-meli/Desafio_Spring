package com.github.transformeli.desafiospring.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private Integer id;
    private List<Product> articles;
    private Double total;
}
