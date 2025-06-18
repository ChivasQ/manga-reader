package com.ferralith.manga_reader.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.Target;
import com.ferralith.manga_reader.R;
import com.ferralith.manga_reader.ReadActivity;
import com.ferralith.manga_reader.api.models.MangaItem;

import java.util.List;
public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Holder> {
    private final Context context;
    private final List<String> pageList;
    private boolean isWebtoon;

    public ViewPager2Adapter(Context context, List<String> pageList, boolean isWebtoon) {
        this.context = context;
        this.pageList = pageList;
        this.isWebtoon = isWebtoon;
    }

    public void setWebtoonMode(boolean isWebtoon) {
        this.isWebtoon = isWebtoon;
        notifyDataSetChanged();
    }

    public boolean isWebtoonMode() {
        return isWebtoon;
    }

    @NonNull
    @Override
    public ViewPager2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_page, parent, false);
        return new ViewPager2Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPager2Holder holder, int position) {
        String url = pageList.get(position);

        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("Referer", "https://mangalib.me/")
                .build());

        Glide.with(holder.itemView.getContext())
                .load(glideUrl)
                .override(Target.SIZE_ORIGINAL)
                .into(holder.imageView);

        if (isWebtoon) {
            ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            holder.imageView.setLayoutParams(params);
            holder.imageView.setAdjustViewBounds(true);
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_START);
        } else {
            ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            holder.imageView.setLayoutParams(params);
            holder.imageView.setAdjustViewBounds(false);
            holder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }

    @Override
    public int getItemCount() {
        return pageList.size();
    }
}
