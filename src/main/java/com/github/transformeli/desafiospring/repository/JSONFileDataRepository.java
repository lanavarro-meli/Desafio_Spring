package com.github.transformeli.desafiospring.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@AllArgsConstructor
public class JSONFileDataRepository<T> {

    public List<?> readJSONData(String linkFile, Class<T> needClass) {
        ObjectMapper mapper = new ObjectMapper();
        List<?> list;
        try {
            list = Arrays.asList(mapper.readValue(new File(linkFile), needClass));
        } catch (Exception e) {
            list = new ArrayList<>();
        }
        return list;
    }
}
