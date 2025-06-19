package com.ferralith.manga_reader.view.read_mode;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ferralith.manga_reader.R;

public class ReadWebToonModeHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public ReadWebToonModeHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.page_imagee);
    }
}
