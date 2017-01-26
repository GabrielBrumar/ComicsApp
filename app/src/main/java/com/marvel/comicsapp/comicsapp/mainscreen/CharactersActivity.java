package com.marvel.comicsapp.comicsapp.mainscreen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.marvel.comicsapp.comicsapp.BaseActivity;
import com.marvel.comicsapp.comicsapp.R;
import com.marvel.comicsapp.comicsapp.application.ComicsApplication;
import com.marvel.comicsapp.comicsapp.models.CharactersListWrapper;

import javax.inject.Inject;

import butterknife.BindView;

public class CharactersActivity extends BaseActivity implements CharactersContract.View {
	@Inject
	CharactersPresenter charactersPresenter;

	@BindView(R.id.recycler_main)
	RecyclerView recyclerView;
	@BindView(R.id.toolbar)
	Toolbar toolbar;

	ContentListAdapter contentListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_characters);
		setSupportActionBar(toolbar);
		DaggerCharactersComponent.builder().charactersPresenterModule(new CharactersPresenterModule(this))
				.remoteComponent(((ComicsApplication) getApplication()).getRemoteComponent()).build().inject(this);
		charactersPresenter.loadCharacters();
		contentListAdapter = new ContentListAdapter(getApplicationContext());
		recyclerView.setAdapter(contentListAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
	}

	@Override
	public void showComplete() {
		Toast.makeText(this, "Loading Complete", Toast.LENGTH_SHORT);
	}

	@Override
	public void showData(CharactersListWrapper data) {
		contentListAdapter.addData(data);
	}
}
