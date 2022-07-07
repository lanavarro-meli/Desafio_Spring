package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repo;

    @Override
    public List<Product> getAllProducts() {
        return repo.getAllProducts();
    }

    @Override
    public Product getByCategory(String category) {
         return repo.getByCategory(category);
    }

    @Override
    public void saveProduct(Product product) {
        repo.saveProduct(product);
    }

    @Override
    public List<Product> getAllAsc() {
       // repo.getAllArticles().stream().sorted((x,y) -> x.getName().length() - y.getName().length()).;
        return null;
    }

    @Override
    public List<Product> getAllDesc() {
        return null;
    }

    @Override
    public List<Product> getAllHigherPrice() {
        return null;
    }

    @Override
    public List<Product> getAllLowerPrice() {
        return null;
    }
    public List<ProductDTO> getAllArticles() {
        List<Product> productsModel = repo.getAllProducts();
        List<ProductDTO> productsDTO
                = productsModel
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        return productsDTO;
    }
    
    @Override
    public List<Product> getAllFromFilters(Map<String, String> params) {
        List<Product> allProducts = this.getAllProducts();
        List<Product> filtredProducts;
        if(params.containsKey("category") && params.containsKey("freeShipping")) {
            filtredProducts = allProducts.stream()
                    .filter(p -> p.getCategory().equals(params.getOrDefault("category", "")))
                    .filter(p -> p.getFreeShipping().equals(Boolean.valueOf(params.getOrDefault("freeShipping", "false"))))
                    .collect(Collectors.toList());
            return filtredProducts;
        } else if (params.containsKey("freeShipping") && params.containsKey("prestige")){
            filtredProducts = allProducts.stream()
                    .filter(p -> p.getFreeShipping().equals(Boolean.valueOf(params.getOrDefault("freeShipping", "false"))))
                    .filter(p -> p.getPrestige().equals((params.getOrDefault("prestige", ""))))
                    .collect(Collectors.toList());
            return filtredProducts;
        }
        return allProducts;
    }
}
