package com.ferralith.manga_reader.view;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ferralith.manga_reader.AboutMangaActivity;
import com.ferralith.manga_reader.R;
import com.ferralith.manga_reader.ReadActivity;
import com.ferralith.manga_reader.api.models.ChapterItem;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterViewHolder> {

    private List<ChapterItem> chapters;
    private String mangaSlug;

    public ChapterAdapter(List<ChapterItem> chapters, String mangaSlug) {
        this.chapters = chapters;
        this.mangaSlug = mangaSlug;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        ChapterItem chapter = chapters.get(position);
        holder.title.setText("Глава " + chapter.getNumber() + (chapter.getName() != null ? ": " + chapter.getName() : ""));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ReadActivity .class);
            intent.putExtra("titleName", chapters.get(holder.getAdapterPosition()).getName());
            intent.putExtra("slug_url", mangaSlug);
            intent.putExtra("chapter_number", chapter.getNumber());
            intent.putExtra("chapter_volume", chapter.getVolume());

            view.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return chapters.size();
    }
}