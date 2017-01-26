package com.marvel.comicsapp.screens.detailscreen;

import android.util.Log;

import com.marvel.comicsapp.models.ComicBook;
import com.marvel.comicsapp.models.ResponseData;
import com.marvel.comicsapp.remote.ApiInterface;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CharacterDetailsPresenter implements CharacterDetailContract.Presenter {
	private CharacterDetailContract.View view;
	private Retrofit retrofit;

	@Inject
	public CharacterDetailsPresenter(CharacterDetailContract.View view, Retrofit retrofit) {
		this.view = view;
		this.retrofit = retrofit;
	}

	@Override
	public void loadData(long characterId) {
		retrofit.create(ApiInterface.class).listComics(characterId, 0, 10).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Observer<ResponseData<ComicBook>>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {
						Log.d("CharacterDetailsPresent", e.getMessage());
						view.showError();
					}

					@Override
					public void onNext(ResponseData<ComicBook> comicBookResponseData) {
						view.showData(comicBookResponseData.getData());
					}
				});
	}
}
