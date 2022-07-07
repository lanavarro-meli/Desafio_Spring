package com.github.transformeli.desafiospring.dto;

import com.github.transformeli.desafiospring.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;
    private Integer quantity;

    public ProductDTO(Product p) {
    }
}
