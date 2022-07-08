package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.exception.NotFoundException;
import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.model.PurchaseRequest;
import com.github.transformeli.desafiospring.model.Ticket;
import com.github.transformeli.desafiospring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class PurchaseService implements IPurchaseService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Ticket processPurchase(List<PurchaseRequest> purchaseList) {
        Integer id = new Random().nextInt(Integer.SIZE - 1);
        List<Product> articles;
        AtomicReference<Double> total = new AtomicReference<>(0D);

        articles = productRepository.getAllProducts().stream()
                .filter(p -> purchaseList.stream().anyMatch(c -> c.getProductId().equals(p.getProductId())))
                .collect(Collectors.toList());

        articles.stream().forEach(p -> total.updateAndGet(v -> v += p.getPrice()));

        if(purchaseList.size() != articles.size())
        {
            throw new NotFoundException("One or more productId not found");
        }

        return new Ticket(id, articles, total.get());
    }

}
