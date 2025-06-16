package com.ferralith.manga_reader.view;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ferralith.manga_reader.R;
import com.ferralith.manga_reader.api.models.ChapterItem;

import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {

    private List<ChapterItem> chapters;

    public ChapterAdapter(List<ChapterItem> chapters) {
        this.chapters = chapters;
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
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    public static class ChapterViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.chapterTitle);
        }
    }
}