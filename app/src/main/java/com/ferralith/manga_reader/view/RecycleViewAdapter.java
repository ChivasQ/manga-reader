package com.ferralith.manga_reader.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ferralith.manga_reader.AboutMangaActivity;
import com.ferralith.manga_reader.R;
import com.ferralith.manga_reader.api.models.MangaItem;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    private Context context;
    private List<MangaItem> mangaItemList;

    public RecycleViewAdapter(Context context, List<MangaItem> mangaItemList) {
        this.context = context;
        this.mangaItemList = mangaItemList;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_manga, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        holder.textTitle.setText(mangaItemList.get(position).getTitle());
        Glide.with(holder.itemView.getContext())
                .load(mangaItemList.get(position).getCoverUrl())
                .into(holder.imageCover);

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), AboutMangaActivity.class);
            intent.putExtra("imageCover", mangaItemList.get(holder.getAdapterPosition()).getCoverUrl());
            intent.putExtra("textTitle", mangaItemList.get(holder.getAdapterPosition()).getTitle());
            intent.putExtra("slug_url", mangaItemList.get(holder.getAdapterPosition()).getSlug_url());

            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mangaItemList.size();
    }
}
