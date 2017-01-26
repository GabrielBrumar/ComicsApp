package com.marvel.comicsapp.screens.detailscreen;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marvel.comicsapp.comicsapp.R;
import com.marvel.comicsapp.models.ComicBook;
import com.marvel.comicsapp.models.ImageResource;
import com.marvel.comicsapp.models.ResultsWrapper;
import com.marvel.comicsapp.screens.mainscreen.ContentListAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ComicsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private ResultsWrapper<ComicBook> data;
    private List<ComicBook> comicBookList;

    public ComicsListAdapter(Context context) {
        this.context = context;
    }

    public void addData(ResultsWrapper<ComicBook> data) {
        if (data != null) {
            this.data = data;
            comicBookList = data.getResults();
            notifyDataSetChanged();
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_card_details, parent, false);
        return new ContentListAdapter.CustomViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ContentListAdapter.CustomViewHolder viewHolder = (ContentListAdapter.CustomViewHolder) holder;

        ComicBook comicBook = comicBookList.get(position);
        viewHolder.imageName.setText(comicBook.getTitle());
        ImageResource thumbnail = comicBook.getThumbnail();
        String urlPath =  thumbnail.getPath() + "/landscape_large." + thumbnail.getExtension();
        Picasso.with(context).load(urlPath).fit().into(viewHolder.cardImage);
    }

    @Override
    public int getItemCount() {
        return comicBookList != null ? comicBookList.size() : 0;
    }


}
