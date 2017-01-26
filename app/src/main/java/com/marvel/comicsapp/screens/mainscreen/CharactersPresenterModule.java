package com.marvel.comicsapp.screens.mainscreen;

import com.marvel.comicsapp.PerActivity;

import dagger.Module;
import dagger.Provides;

@PerActivity
@Module
public class CharactersPresenterModule {

    private CharactersContract.View view;

    public CharactersPresenterModule(CharactersContract.View view){
        this.view = view;
    }

    @PerActivity
    @Provides
    public CharactersContract.View providesView(){
        return view;
    }
}
