package com.ferralith.manga_reader.view;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ferralith.manga_reader.R;

public class ViewPager2Holder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public ViewPager2Holder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.page_image);
    }
}
