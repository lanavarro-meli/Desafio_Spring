package com.github.transformeli.desafiospring.dto;

import com.github.transformeli.desafiospring.model.Product;
import lombok.*;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private String name;

    private Integer quantity;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
    }
}
