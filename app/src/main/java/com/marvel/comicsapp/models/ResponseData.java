package com.marvel.comicsapp.models;


public class ResponseData<T> {
    private ResultsWrapper<T> data;

    public ResultsWrapper<T> getData() {
        return data;
    }
}
