package com.marvel.comicsapp.comicsapp.mainscreen;

public interface CharactersContract {
	public interface View {
		void showComplete();
	}

	public interface Presenter {

		void loadCharacters();
	}
}
