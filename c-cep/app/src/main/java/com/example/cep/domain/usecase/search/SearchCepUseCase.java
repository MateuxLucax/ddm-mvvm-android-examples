package com.example.cep.domain.usecase.search;

import com.example.cep.data.common.util.Result;
import com.example.cep.domain.model.Cep;

public class SearchCepUseCase {

    private final ISearchCepRepository repository;

    public SearchCepUseCase(ISearchCepRepository repository) {
        this.repository = repository;
    }

    public Result<Cep> execute(String id) {
        if (id == null || id.length() != 8) {
            return new Result<>(new Exception("Invalid CEP format"));
        }

        return repository.search(id);
    }

}
