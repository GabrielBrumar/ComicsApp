package com.marvel.comicsapp.comicsapp.remote;


import com.marvel.comicsapp.comicsapp.models.CharacterResponseData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiInterface {

    @GET("characters")
    Observable<CharacterResponseData> getMarvelCharacters(
                                                          @Query("nameStartsWith") String query,
                                                          @Query("offset") int offset,
                                                          @Query("limit") int limit);
}
