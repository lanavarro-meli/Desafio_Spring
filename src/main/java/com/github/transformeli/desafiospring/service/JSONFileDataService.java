package com.github.transformeli.desafiospring.service;

import com.github.transformeli.desafiospring.model.Product;
import com.github.transformeli.desafiospring.repository.JSONFileDataRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class JSONFileDataService<T> implements IJSONFileDataService {

    @Autowired
    private JSONFileDataRepository repository;


    @Override
    public List<?> readJSONData(String linkFile, Class needClass) {
        return repository.readJSONData(linkFile,needClass);
    }
}
