package com.marvel.comicsapp.comicsapp.mainscreen;

import android.util.Log;

import com.marvel.comicsapp.comicsapp.models.CharacterResponseData;
import com.marvel.comicsapp.comicsapp.remote.ApiInterface;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CharactersPresenter implements CharactersContract.Presenter{

    Retrofit retrofit;
    CharactersContract.View view;

    @Inject
    public CharactersPresenter(Retrofit retrofit, CharactersContract.View view){
        this.retrofit = retrofit;
        this.view = view;
    }
    @Override
    public void loadCharacters() {
        retrofit.create(ApiInterface.class).getMarvelCharacters("x",0,5).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<CharacterResponseData>() {
            @Override
            public void onCompleted() {
                view.showComplete();
            }

            @Override
            public void onError(Throwable e) {
    Log.d("ErrorMessage", e.getMessage());
            }

            @Override
            public void onNext(CharacterResponseData characterResponseData) {

               Log.d("Data", characterResponseData.getData().getResults().get(0).getName());
            }
        });

    }
}
