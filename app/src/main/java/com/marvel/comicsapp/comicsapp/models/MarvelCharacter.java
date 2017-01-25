package com.marvel.comicsapp.comicsapp.models;

public class MarvelCharacter {

    private long id;
    private String name;
    private String description;
    private ImageResource thumbnail;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ImageResource getThumbnail() {
        return thumbnail;
    }
}
