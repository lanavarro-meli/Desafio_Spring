package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.exception.NotFoundException;
import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
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
    public List<ProductDTO> getByCategory(String category) {
        try {
            List<Product> productsByCategory = repo.getByCategory(category);
            List<ProductDTO> treatedProducts = productsByCategory
                    .stream().map(ProductDTO::new).collect(Collectors.toList());


            return treatedProducts;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        throw new NotFoundException("Sorry, this category has no products yet");
    }

    @Override
    public void saveProduct(Product product) {
        repo.saveProduct(product);
    }

    @Override
    public List<Product> getAllByOrder(Integer order) {
        List<Product> productList = getAllProducts();
        List<Product> result = null;
        switch (order) {
            case 0: {
                result = productList.stream()
                        .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                        .collect(Collectors.toList());
                break;
            }
            case 1: {
                result = productList.stream()
                        .sorted((p1, p2) -> p2.getName().compareTo(p1.getName()))
                        .collect(Collectors.toList());
                break;
            }
            case 2: {
                result = productList.stream()
                        .sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()))
                        .collect(Collectors.toList());
                break;
            }
            case 3: {
                result = productList.stream()
                        .sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
                        .collect(Collectors.toList());
                break;
            }
        }
        return result;
    }

    @Override
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
