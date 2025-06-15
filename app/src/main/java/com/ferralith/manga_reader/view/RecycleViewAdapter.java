package com.ferralith.manga_reader.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ferralith.manga_reader.R;
import com.ferralith.manga_reader.models.MangaItem;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewHolder> {

    Context context;
    List<MangaItem> mangaItemList;

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
                .load(mangaItemList.get(position).coverUrl)
                .into(holder.imageCover);
    }

    @Override
    public int getItemCount() {
        return mangaItemList.size();
    }
}
