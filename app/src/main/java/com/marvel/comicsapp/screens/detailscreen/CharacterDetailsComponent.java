package com.marvel.comicsapp.screens.detailscreen;

import com.marvel.comicsapp.PerActivity;
import com.marvel.comicsapp.application.RemoteComponent;

import dagger.Component;

@PerActivity
@Component(dependencies = { RemoteComponent.class }, modules = { CharacterDetailsModule.class })
public interface CharacterDetailsComponent {
	void inject(CharacterDetailsActivity activity);
}
