package com.example.cep.data.common.util;

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

}
