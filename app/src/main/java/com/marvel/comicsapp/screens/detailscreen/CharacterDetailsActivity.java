package com.marvel.comicsapp.screens.detailscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marvel.comicsapp.BaseActivity;
import com.marvel.comicsapp.application.ComicsApplication;
import com.marvel.comicsapp.comicsapp.R;
import com.marvel.comicsapp.models.ComicBook;
import com.marvel.comicsapp.models.ImageResource;
import com.marvel.comicsapp.models.MarvelCharacter;
import com.marvel.comicsapp.models.ResultsWrapper;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;

public class CharacterDetailsActivity extends BaseActivity implements CharacterDetailContract.View {

	@BindView(R.id.details_recycler)
	RecyclerView recyclerView;
	@BindView(R.id.character_description)
	TextView characterDescription;
	@BindView(R.id.collapse_details_image)
	ImageView collapseImage;
	@BindView(R.id.details_appbar)
	AppBarLayout appBarLayout;
	@BindView(R.id.collapsing_details_toolbar)
	CollapsingToolbarLayout collapsingToolbar;
	@BindView(R.id.toolbar_details)
	Toolbar toolbar;
	@Inject
	CharacterDetailsPresenter presenter;

	private ComicsListAdapter comicsListAdapter;
	private MarvelCharacter marvelCharacter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character_details);

		Intent intent = getIntent();
		marvelCharacter = (MarvelCharacter) intent.getParcelableExtra("marvelCharacter");
		ImageResource thumbnail = marvelCharacter.getThumbnail();
		String charactersCollapsingImageUrl = thumbnail.getPath() + "/standard_fantastic." + thumbnail.getExtension();
		initializeCollapsedToolbar(charactersCollapsingImageUrl);

		DaggerCharacterDetailsComponent.builder().remoteComponent(((ComicsApplication) getApplication()).getRemoteComponent())
				.characterDetailsModule(new CharacterDetailsModule(this)).build().inject(this);
		presenter.loadData(marvelCharacter.getId());

		characterDescription.setText(marvelCharacter.getDescription());
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		comicsListAdapter = new ComicsListAdapter(this);
		recyclerView.setAdapter(comicsListAdapter);
	}

	private void initializeCollapsedToolbar(String charactersCollapsingImageUrl) {
		Picasso.with(this).load(charactersCollapsingImageUrl).fit().into(collapseImage);
		setSupportActionBar(toolbar);
		collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.colorBlack));
		// collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CustomTitleStyle);
		// collapsingToolbar.setExpandedTitleTypeface(titleTypeFace);
		int bottomMargin = getResources().getDimensionPixelSize(R.dimen.bottom_margin);
		collapsingToolbar.setExpandedTitleMarginBottom(bottomMargin);
		// collapsingToolbar.setCollapsedTitleTypeface(titleTypeFace);
		appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
			String title = marvelCharacter.getName();
			@Override
			public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
				if ((collapsingToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbar))) {
					collapsingToolbar.setTitle(title);
				}else{
					collapsingToolbar.setTitle(" ");
				}
			}
		});

	}

	@Override
	public void showData(ResultsWrapper<ComicBook> data) {
		comicsListAdapter.addData(data);
	}

	@Override
	public void showError() {
		Toast.makeText(this, "There was an error loading the data. Please try again later.", Toast.LENGTH_SHORT);
	}
}
