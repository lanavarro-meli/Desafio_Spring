package com.github.transformeli.desafiospring.service;

import java.util.List;

public interface IJSONFileDataService<T> {
    List<T> readJSONData(String linkFile,  Class<T> needClass);
}
