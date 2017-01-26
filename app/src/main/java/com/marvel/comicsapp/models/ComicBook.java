package com.marvel.comicsapp.models;

public class ComicBook {
	private int id;
	private String title;
	private String description;
	private ImageResource thumbnail;

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public ImageResource getThumbnail() {
		return thumbnail;
	}

}
