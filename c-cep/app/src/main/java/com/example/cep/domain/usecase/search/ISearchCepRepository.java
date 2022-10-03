package com.example.cep.domain.usecase.search;

import com.example.cep.data.common.util.Result;
import com.example.cep.domain.model.Cep;

public interface ISearchCepRepository {

    Result<Cep> search(String id);

}
