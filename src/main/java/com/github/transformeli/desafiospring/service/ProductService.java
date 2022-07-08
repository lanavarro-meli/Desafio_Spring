package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.dto.ProductDTO;
import com.github.transformeli.desafiospring.enums.ParamOrderEnum;
import com.github.transformeli.desafiospring.exception.BadRequestException;
import com.github.transformeli.desafiospring.exception.NotFoundException;
import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository repo;

    /**
     * This method call getAllProducts() in ProductRepository and return list.
     *
     * @author Isaias Finger
     */
    @Override
    public List<Product> getAllProducts() {
        return repo.getAllProducts();
    }

    /**
     * This method call getByCategory(String category) in ProductRepository, change Product to ProductDTO and return list.
     *
     * @param category Product Category
     * @author Lucas Pinheiro Rocha
     */
    @Override
    public List<ProductDTO> getByCategory(String category) {
        try {
            List<Product> productsByCategory = repo.getByCategory(category);
            List<ProductDTO> treatedProducts = productsByCategory
                    .stream().map(ProductDTO::new).collect(Collectors.toList());
            return treatedProducts;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        throw new NotFoundException("Sorry, this category has no products yet");
    }

    /**
     * This method call saveProduct(String category) in ProductRepository.
     *
     * @param product Product to be saved
     * @author Isaias Finger
     */
    @Override
    public void saveProduct(Product product) {
        repo.saveProduct(product);
    }

    /**
     * This method order attributes and return list.
     *
     * @param orderBy ParamOrderEnum
     * @param productList ProductList to be ordered
     * @author Rebecca Cunha Cruz and Isaias Finger
     */
    @Override
    public List<Product> getAllByOrder(ParamOrderEnum orderBy, List<Product> productList) {
        List<Product> result = new ArrayList<>();
        switch (orderBy) {
            case ASCENDING_ALPHA: {
                result = productList.stream()
                        .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                        .collect(Collectors.toList());
                break;
            }
            case DESCENDING_ALPHA: {
                result = productList.stream()
                        .sorted((p1, p2) -> p2.getName().compareTo(p1.getName()))
                        .collect(Collectors.toList());
                break;
            }
            case LOWER_PRICE: {
                result = productList.stream()
                        .sorted((p1, p2) -> p2.getPrice().compareTo(p1.getPrice()))
                        .collect(Collectors.toList());
                break;
            }
            case HIGHER_PRICE: {
                result = productList.stream()
                        .sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
                        .collect(Collectors.toList());
                break;
            }
            default:
                throw new BadRequestException("invalid orderId");
        }
        return result;
    }

    /**
     * This method get list param, change Product to ProductDTO and return list.
     *
     * @param productList ProductList conversion to DTO
     * @author Evelyn Cristini Oliveira and Isaias Finger
     */
    @Override
    public List<ProductDTO> getAllArticles(List<Product> productList) {
        List<Product> productsModel = productList;
        List<ProductDTO> productsDTO
                = productsModel
                .stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
        return productsDTO;
    }

    /**
     * This method call getAllProducts(), filter by params and return list
     *
     * @param category Category
     * @param brand Brand
     * @param freeShipping Is freeShipping
     * @param prestige Prestige
     * @param order Ordering
     * @author Evelyn Cristini Oliveira and Isaias Finger
     */
    public List<ProductDTO> getProductsByFilter(
            Optional<String> category,
            Optional<String> brand,
            Optional<Boolean> freeShipping,
            Optional<String> prestige,
            Optional<Integer> order
    ) {
        List<Product> filteredProducts = new ArrayList<>(this.getAllProducts());
        if (category.isPresent()) {
            filteredProducts = this.getProductsFilterByCategory(filteredProducts, category.get());
        }
        if (brand.isPresent()) {
            filteredProducts = this.getProductsFilterByBrand(filteredProducts, brand.get());
        }
        if (freeShipping.isPresent()) {
            filteredProducts = this.getProductsFilterByFreeShipping(filteredProducts, freeShipping.get());
        }
        if (prestige.isPresent()) {
            filteredProducts = this.getProductsFilterByPrestige(filteredProducts, prestige.get());
        }
        if (order.isPresent()) {
            filteredProducts = this.getAllByOrder(ParamOrderEnum.valueOf(order.get()), filteredProducts);
        }
        return this.getAllArticles(filteredProducts);
    }

    private List<Product> getProductsFilterByCategory(List<Product> products, String category) {
        List<Product> filteredProducts = new ArrayList<>(products);
        filteredProducts = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        return filteredProducts;
    }

    private List<Product> getProductsFilterByFreeShipping(List<Product> products, Boolean freeShipping) {
        List<Product> filteredProducts = new ArrayList<>(products);
        filteredProducts = products.stream()
                .filter(p -> p.getFreeShipping().equals(Boolean.valueOf(freeShipping)))
                .collect(Collectors.toList());
        return filteredProducts;
    }

    private List<Product> getProductsFilterByBrand(List<Product> products, String brand) {
        List<Product> filteredProducts = new ArrayList<>(products);
        filteredProducts = products.stream()
                .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
        return filteredProducts;
    }

    private List<Product> getProductsFilterByPrestige(List<Product> products, String prestige) {
        List<Product> filteredProducts = new ArrayList<>(products);
        filteredProducts = products.stream()
                .filter(p -> p.getPrestige().equals(prestige))
                .collect(Collectors.toList());
        return filteredProducts;
    }
}
