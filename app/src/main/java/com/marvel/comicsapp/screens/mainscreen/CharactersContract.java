package com.marvel.comicsapp.screens.mainscreen;


import com.marvel.comicsapp.models.MarvelCharacter;
import com.marvel.comicsapp.models.ResultsWrapper;

public interface CharactersContract {
	public interface View {
		void showComplete();

		void showData(ResultsWrapper<MarvelCharacter> data);

		void showError(String s);
	}

	public interface Presenter {

		void loadCharacters();
	}
}
