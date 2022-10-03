package com.example.cep.data.search.datasource;

import com.example.cep.data.common.util.Result;
import com.example.cep.domain.model.Cep;

public interface ISearchDatasource {

    Result<Cep> search(String id);

}
