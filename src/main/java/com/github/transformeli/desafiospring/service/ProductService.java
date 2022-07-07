package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.exception.NotFoundException;
import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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


}
