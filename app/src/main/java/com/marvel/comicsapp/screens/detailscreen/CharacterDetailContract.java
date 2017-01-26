package com.marvel.comicsapp.screens.detailscreen;


import com.marvel.comicsapp.models.ComicBook;
import com.marvel.comicsapp.models.ResultsWrapper;

public interface CharacterDetailContract {
    public interface View{

        void showData(ResultsWrapper<ComicBook> data);

        void showError();
    }
    public interface Presenter{

        void loadData(long characterId);
    }
}
