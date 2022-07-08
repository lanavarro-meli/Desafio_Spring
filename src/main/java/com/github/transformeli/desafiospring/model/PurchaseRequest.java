package com.github.transformeli.desafiospring.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {
    private Long productId;
    private Integer quantity;
}
