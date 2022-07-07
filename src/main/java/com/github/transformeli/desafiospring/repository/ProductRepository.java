package com.github.transformeli.desafiospring.repository;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.exception.NotFoundException;
import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.service.IJSONFileDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    IJSONFileDataService wrapper;
    private final String linkFile = "src/main/resources/products.json";

    public List<Product> getAllProducts() {
        return readFile();
    }

    public Product getByCategory(String category) {
        try {
            List<Product> list = readFile();
            for (Product a : list) {
                if (a.getCategory().equals(category)) {
                    return a;
                }
            }
        } catch (Exception e) {

        }
        throw new NotFoundException("Category not found");
    }

    public void saveProduct(Product product) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        List<Product> productList, productListNew;
        try {
            productList = readFile();
            productListNew = new ArrayList<>(productList);
            productListNew.add(product);
            writer.writeValue(new File(linkFile), productListNew);
        } catch (Exception e) {
            // TODO: Exception
            System.out.println("Erro ao inserir as informacoes");
        }
    }

    private List<Product> readFile() {
        List<Product> list = wrapper.readJSONData(linkFile);
        return list;
    }

}
