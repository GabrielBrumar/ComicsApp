package com.marvel.comicsapp.comicsapp.mainscreen;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.marvel.comicsapp.comicsapp.BaseActivity;
import com.marvel.comicsapp.comicsapp.R;
import com.marvel.comicsapp.comicsapp.application.ComicsApplication;

import javax.inject.Inject;

import butterknife.BindView;

public class CharactersActivity extends BaseActivity implements CharactersContract.View {
	@Inject
	CharactersPresenter charactersPresenter;

	@BindView(R.id.recycler_main)
	RecyclerView recyclerView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_characters);
		DaggerCharactersComponent.builder().charactersPresenterModule(new CharactersPresenterModule(this))
				.remoteComponent(((ComicsApplication) getApplication()).getRemoteComponent()).build().inject(this);
        charactersPresenter.loadCharacters();
		recyclerView.setAdapter(new ContentListAdapter());
	}

	@Override
	public void showComplete() {
		Toast.makeText(this,"Loading Complete", Toast.LENGTH_SHORT);
	}
}
