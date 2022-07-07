package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getAllProducts();
    public List<ProductDTO> getByCategory(String category);
    public void saveProduct(Product product);
    public List<Product> getAllAsc();
    public List<Product> getAllDesc();
    public List<Product> getAllHigherPrice();
    public List<Product> getAllLowerPrice();

}
