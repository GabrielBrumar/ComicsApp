package com.marvel.comicsapp.comicsapp.mainscreen;

import com.marvel.comicsapp.comicsapp.PerActivity;
import com.marvel.comicsapp.comicsapp.application.RemoteComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = {RemoteComponent.class}, modules = CharactersPresenterModule.class)
public interface CharactersComponent {
    void inject(CharactersActivity charactersActivity);
}
