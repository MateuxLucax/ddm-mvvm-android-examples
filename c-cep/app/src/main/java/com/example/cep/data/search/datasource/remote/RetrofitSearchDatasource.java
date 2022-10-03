package com.example.cep.data.search.datasource.remote;

import com.example.cep.data.common.util.Result;
import com.example.cep.data.common.util.RetrofitInitializer;
import com.example.cep.data.search.datasource.ISearchDatasource;
import com.example.cep.domain.model.Cep;

import java.util.concurrent.CountDownLatch;

import retrofit2.Response;

public class RetrofitSearchDatasource implements ISearchDatasource {

    private final CepService service;

    public RetrofitSearchDatasource() {
        service = new RetrofitInitializer().getCep();
    }

    @Override
    public Result<Cep> search(String id) {
        final Result<Cep> result = new Result<>();
        final CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            try {
                Response<Cep> response = service.select(id).execute();

                if (response.errorBody() != null) {
                    throw new Exception(response.errorBody().string());
                }

                result.setResponse(response.body());
            } catch (Exception e) {
                result.setError(e);
            }

            latch.countDown();
        }).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

}
