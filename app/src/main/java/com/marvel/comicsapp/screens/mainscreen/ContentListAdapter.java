package com.marvel.comicsapp.screens.mainscreen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvel.comicsapp.comicsapp.R;
import com.marvel.comicsapp.models.ImageResource;
import com.marvel.comicsapp.models.MarvelCharacter;
import com.marvel.comicsapp.models.ResultsWrapper;
import com.marvel.comicsapp.screens.detailscreen.CharacterDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private List<MarvelCharacter> results;
	private Context context;

	public ContentListAdapter(Context applicationContext) {
		this.context = applicationContext;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card_details, parent, false);
		return new ContentListAdapter.CustomViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (results == null) {
			return;
		}
		final MarvelCharacter marvelCharacter = results.get(position);

		CustomViewHolder viewHolder = (CustomViewHolder) holder;
		ImageResource thumbnail = marvelCharacter.getThumbnail();
		final String urlPath = thumbnail.getPath() + "/landscape_large." + thumbnail.getExtension();
		Picasso.with(context).load(urlPath).fit().into(viewHolder.cardImage);
		viewHolder.imageName.setText(marvelCharacter.getName() + "\n" + marvelCharacter.getDescription());
		View.OnClickListener onClickListener = new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(context, CharacterDetailsActivity.class);
				intent.putExtra("marvelCharacter", marvelCharacter);
				context.startActivity(intent);
			}
		};
		viewHolder.cardImage.setOnClickListener(onClickListener);
		viewHolder.imageName.setOnClickListener(onClickListener);
	}

	@Override
	public int getItemCount() {
		return results == null ? 0 : results.size();
	}

	public void addData(ResultsWrapper data) {
		if (data != null) {
			List<MarvelCharacter> results = data.getResults();
			if (results.size() > 0) {
				this.results = results;
				notifyDataSetChanged();
			}
		}
	}

	public static class CustomViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.card_image)
		public ImageView cardImage;
		@BindView(R.id.image_name)
		public TextView imageName;

		public CustomViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
