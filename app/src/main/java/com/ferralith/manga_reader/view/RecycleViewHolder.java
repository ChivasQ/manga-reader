package com.ferralith.manga_reader.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ferralith.manga_reader.R;

public class RecycleViewHolder extends RecyclerView.ViewHolder {

    ImageView imageCover;
    TextView textTitle;



    public RecycleViewHolder(@NonNull View itemView) {
        super(itemView);
        imageCover = itemView.findViewById(R.id.imageCover);
        textTitle = itemView.findViewById(R.id.textTitle);
    }
}
