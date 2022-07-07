package com.github.transformeli.desafiospring.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.github.transformeli.desafiospring.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class JSONFileDataRepository<T> {

    public List<T> readJSONData(String linkFile) {
        ObjectMapper mapper = new ObjectMapper();
        List<?> list = null;
        try {
            list = Arrays.asList(mapper.readValue(new File(linkFile), Product[].class));
        } catch (Exception e) {
            list = new ArrayList<>();
        }
        return (List<T>) list;
    }
}
