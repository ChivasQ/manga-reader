package com.ferralith.manga_reader.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.ferralith.manga_reader.R;
import com.ferralith.manga_reader.ReadActivity;
import com.ferralith.manga_reader.api.models.MangaItem;

import java.util.List;

public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Holder>{

    private Context context;
    private List<String> pageList;

    public ViewPager2Adapter(Context context, List<String> pageList) {
        this.context = context;
        this.pageList = pageList;
    }

    @NonNull
    @Override
    public ViewPager2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPager2Holder(LayoutInflater.from(context).inflate(R.layout.item_page, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPager2Holder holder, int position) {
        String url = pageList.get(position);

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Referer", "https://mangalib.me/")
                .build());

        Glide.with(holder.itemView.getContext())
                .load(glideUrl)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return pageList.size();
    }
}
