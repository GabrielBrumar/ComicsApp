package com.marvel.comicsapp.remote;

import com.marvel.comicsapp.models.ComicBook;
import com.marvel.comicsapp.models.MarvelCharacter;
import com.marvel.comicsapp.models.ResponseData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

	@GET("characters")
	Observable<ResponseData<MarvelCharacter>> getMarvelCharacters(@Query("nameStartsWith") String query, @Query("offset") int offset, @Query("limit") int limit);

	@GET("characters/{characterId}/comics")
	Observable<ResponseData<ComicBook>> listComics(@Path("characterId") long characterId, @Query("offset") int offset, @Query("limit") int limit);
}
