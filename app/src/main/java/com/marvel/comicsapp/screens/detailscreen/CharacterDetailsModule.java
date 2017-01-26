package com.marvel.comicsapp.screens.detailscreen;

import com.marvel.comicsapp.PerActivity;

import dagger.Module;
import dagger.Provides;

@PerActivity
@Module
public class CharacterDetailsModule {
    private CharacterDetailContract.View view;
public CharacterDetailsModule(CharacterDetailContract.View view){
    this.view = view;
}
    @PerActivity
    @Provides
    public CharacterDetailContract.View providesView(){
        return  view;
    }
}
