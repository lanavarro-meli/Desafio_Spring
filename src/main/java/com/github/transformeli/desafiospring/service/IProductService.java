package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.model.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {
    public List<Product> getAllProducts();
    public List<ProductDTO> getAllArticles();
    public List<ProductDTO> getByCategory(String category);
    public void saveProduct(Product product);
    public List<Product> getAllByOrder(Integer order);
    public List<Product> getAllFromFilters(Map<String, String> params);

}
