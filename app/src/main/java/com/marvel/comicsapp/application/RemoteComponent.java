package com.marvel.comicsapp.application;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {RemoteModule.class, AppModule.class})
public interface RemoteComponent {
    public void inject(ComicsApplication comicsApplication);

    public Retrofit exposeRetrofit();
}
