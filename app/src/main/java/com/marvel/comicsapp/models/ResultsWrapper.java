package com.marvel.comicsapp.models;

import java.util.List;

public class ResultsWrapper<T> {
    private List<T> results;

    public List<T> getResults() {
        return results;
    }
}
