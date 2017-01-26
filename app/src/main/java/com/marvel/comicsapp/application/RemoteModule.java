package com.marvel.comicsapp.application;


import com.marvel.comicsapp.remote.AuthInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module
public class RemoteModule {
	String baseUrl;

	public RemoteModule(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Singleton
	@Provides
	GsonConverterFactory provideGsonConverterFactory() {
		return GsonConverterFactory.create();
	}

	@Singleton
	@Provides
	OkHttpClient provideOkHttpClient() {
		AuthInterceptor authInterceptor = new AuthInterceptor();
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).addInterceptor(authInterceptor).build();
		return okHttpClient;
	}

	@Singleton
	@Provides
	RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
		return RxJavaCallAdapterFactory.create();

	}

	@Singleton
	@Provides
	Retrofit provideRetrofit(OkHttpClient okHttpClient, RxJavaCallAdapterFactory rxJavaCallAdapterFactory, GsonConverterFactory gsonConverterFactory) {
		return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).addCallAdapterFactory(rxJavaCallAdapterFactory)
				.client(okHttpClient).build();
	}
}
