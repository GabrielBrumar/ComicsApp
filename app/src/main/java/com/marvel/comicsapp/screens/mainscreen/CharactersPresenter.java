package com.marvel.comicsapp.screens.mainscreen;

import android.util.Log;

import com.marvel.comicsapp.models.MarvelCharacter;
import com.marvel.comicsapp.models.ResponseData;
import com.marvel.comicsapp.remote.ApiInterface;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CharactersPresenter implements CharactersContract.Presenter{

   private  Retrofit retrofit;
   private CharactersContract.View view;

    @Inject
    public CharactersPresenter(Retrofit retrofit, CharactersContract.View view){
        this.retrofit = retrofit;
        this.view = view;
    }
    @Override
    public void loadCharacters() {
        retrofit.create(ApiInterface.class).getMarvelCharacters("x",0,5).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseData<MarvelCharacter>>() {
            @Override
            public void onCompleted() {
                view.showComplete();
            }

            @Override
            public void onError(Throwable e) {
            view.showError(e.toString());
                Log.d("ErrorMessage", e.getMessage());
            }

            @Override
            public void onNext(ResponseData<MarvelCharacter> characterResponseData) {

           view.showData(characterResponseData.getData());
            }
        });

    }
}
