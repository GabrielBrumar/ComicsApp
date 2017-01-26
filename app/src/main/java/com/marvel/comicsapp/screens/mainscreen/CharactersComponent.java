package com.marvel.comicsapp.screens.mainscreen;

import com.marvel.comicsapp.PerActivity;
import com.marvel.comicsapp.application.RemoteComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = {RemoteComponent.class}, modules = CharactersPresenterModule.class)
public interface CharactersComponent {
    void inject(CharactersActivity charactersActivity);
}
