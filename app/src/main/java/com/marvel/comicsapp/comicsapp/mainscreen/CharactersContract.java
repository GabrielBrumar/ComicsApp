package com.marvel.comicsapp.comicsapp.mainscreen;

import com.marvel.comicsapp.comicsapp.models.CharactersListWrapper;

public interface CharactersContract {
	public interface View {
		void showComplete();

		void showData(CharactersListWrapper data);
	}

	public interface Presenter {

		void loadCharacters();
	}
}
