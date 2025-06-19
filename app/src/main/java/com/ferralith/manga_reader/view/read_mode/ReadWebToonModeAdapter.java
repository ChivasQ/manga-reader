package com.ferralith.manga_reader.view.read_mode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.Target;
import com.ferralith.manga_reader.R;
import com.ferralith.manga_reader.view.ViewPager2Holder;

import java.util.List;

public class ReadWebToonModeAdapter extends RecyclerView.Adapter<ReadWebToonModeHolder>{
    private final Context context;
    private final List<String> pageList;

    public ReadWebToonModeAdapter(Context context, List<String> pageList) {
        this.context = context;
        this.pageList = pageList;
    }

    @NonNull
    @Override
    public ReadWebToonModeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.webtoon_page, parent, false);
        return new ReadWebToonModeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadWebToonModeHolder holder, int position) {
        String url = pageList.get(position);

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Referer", "https://mangalib.me/")
                .build());

        Glide.with(holder.itemView.getContext())
                .load(glideUrl)
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return pageList.size();
    }
}
