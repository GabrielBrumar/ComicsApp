package com.marvel.comicsapp.comicsapp.mainscreen;

import com.marvel.comicsapp.comicsapp.PerActivity;

import dagger.Module;
import dagger.Provides;

@PerActivity
@Module
public class CharactersPresenterModule {

    CharactersContract.View view;

    public CharactersPresenterModule(CharactersContract.View view){
        this.view = view;
    }

    @PerActivity
    @Provides
    public CharactersContract.View providesView(){
        return view;
    }
}
