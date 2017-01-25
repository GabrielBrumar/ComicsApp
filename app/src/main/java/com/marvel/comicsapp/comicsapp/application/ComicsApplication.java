package com.marvel.comicsapp.comicsapp.application;

import android.app.Application;

import javax.inject.Inject;


public class ComicsApplication extends Application {

    private static final String BASE_URL="http://gateway.marvel.com/v1/public/";

    @Inject
    RemoteComponent remoteComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerRemoteComponent.builder().remoteModule(new RemoteModule(BASE_URL)).appModule(new AppModule(this)).build().inject(this);
    }

   public RemoteComponent getRemoteComponent(){
       return remoteComponent;
   }
}
