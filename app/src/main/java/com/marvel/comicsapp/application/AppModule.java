package com.marvel.comicsapp.application;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class AppModule {
    ComicsApplication comicsApplication;

    public AppModule(ComicsApplication comicsApplication){
        this.comicsApplication = comicsApplication;
    }

    @Singleton
    @Provides
    public SharedPreferences providesSharedPreferences(){
	return comicsApplication .getSharedPreferences("myPref", 0);
    }
}
