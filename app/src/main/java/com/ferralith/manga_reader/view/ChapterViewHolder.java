package com.ferralith.manga_reader.view;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ferralith.manga_reader.R;

public class ChapterViewHolder extends RecyclerView.ViewHolder {
    TextView title;

    public ChapterViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.chapterTitle);
    }
}