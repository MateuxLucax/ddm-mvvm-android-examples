package com.example.cep.data.search.repository;

import com.example.cep.data.common.util.Result;
import com.example.cep.data.search.datasource.ISearchDatasource;
import com.example.cep.domain.model.Cep;
import com.example.cep.domain.usecase.search.ISearchCepRepository;

import java.util.HashMap;
import java.util.Map;

public class SearchCepRepository implements ISearchCepRepository {

    private final ISearchDatasource datasource;
    private final Map<String, Cep> cepCache = new HashMap<>();

    public SearchCepRepository(ISearchDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Result<Cep> search(String id) {
        if (cepCache.containsKey(id)) {
            return new Result<>(cepCache.get(id));
        }

        Result<Cep> result = this.datasource.search(id);
        if (!result.hasError()) {
            cepCache.put(id, result.getResponse());
        }

        return result;
    }

}
