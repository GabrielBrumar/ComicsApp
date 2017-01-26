package com.marvel.comicsapp.comicsapp.mainscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvel.comicsapp.comicsapp.R;
import com.marvel.comicsapp.comicsapp.models.CharactersListWrapper;
import com.marvel.comicsapp.comicsapp.models.ImageResource;
import com.marvel.comicsapp.comicsapp.models.MarvelCharacter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContentListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MarvelCharacter> results;
    private Context context;

    public ContentListAdapter(Context applicationContext) {
        this.context = applicationContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card_details, parent, false);
        return  new ContentListAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(results==null){
            return;
        }
        MarvelCharacter marvelCharacter = results.get(position);

        CustomViewHolder viewHolder = (CustomViewHolder)holder;
        ImageResource thumbnail = marvelCharacter.getThumbnail();
        Picasso.with(context).load(thumbnail.getPath()+"/landscape_large."+thumbnail.getExtension()).fit().into(viewHolder.cardImage);
        viewHolder.imageName.setText(marvelCharacter.getName()+ "\n" + marvelCharacter.getDescription());
    }

    @Override
    public int getItemCount() {
        return results==null?0:results.size();
    }

    public void addData(CharactersListWrapper data) {
        if (data != null) {
            List<MarvelCharacter> results = data.getResults();
            if (results.size() > 0) {
                this.results = results;
                notifyDataSetChanged();
            }
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView cardImage;
        TextView imageName;

        public CustomViewHolder(View itemView) {
            super(itemView);
            cardImage = (ImageView) itemView.findViewById(R.id.card_image);
            imageName = (TextView) itemView.findViewById(R.id.image_name);
        }
    }
}
