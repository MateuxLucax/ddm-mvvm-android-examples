package com.example.cep.data.common.util;

import java.util.Objects;

public class Result<T> {

    private T response;
    private Exception error;

    public Result() {
    }

    public Result(T response) {
        this.response = response;
    }

    public Result(Exception error) {
        this.error = error;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public boolean hasError() {
        return error != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result<?> result = (Result<?>) o;
        return Objects.equals(response, result.response) && Objects.equals(error, result.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(response, error);
    }
}
