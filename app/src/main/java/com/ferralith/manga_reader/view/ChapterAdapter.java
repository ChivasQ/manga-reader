package com.ferralith.manga_reader.view;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ferralith.manga_reader.AboutMangaActivity;
import com.ferralith.manga_reader.R;
import com.ferralith.manga_reader.ReadActivity;
import com.ferralith.manga_reader.api.ApiProvider;
import com.ferralith.manga_reader.api.ChapterCallback;
import com.ferralith.manga_reader.api.DownloadManager;
import com.ferralith.manga_reader.api.models.ChapterData;
import com.ferralith.manga_reader.api.models.ChapterItem;
import com.ferralith.manga_reader.api.models.ChapterPage;
import com.ferralith.manga_reader.api.models.ChapterResponse;

import org.jsoup.Jsoup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            Intent intent = new Intent(view.getContext(), ReadActivity.class);
            intent.putExtra("titleName", chapters.get(holder.getAdapterPosition()).getName());
            intent.putExtra("slug_url", mangaSlug);
            intent.putExtra("chapter_number", chapter.getNumber());
            intent.putExtra("chapter_volume", chapter.getVolume());

            view.getContext().startActivity(intent);
        });



        holder.downloadButton.setOnClickListener(view -> {
            DownloadManager downloadManager = new DownloadManager();

            loadPages(position, new ChapterCallback() {
                @Override
                public void onChapterLoaded(ChapterData data) {
                    downloadManager.AddItem(data);
                    new Thread(downloadManager::DownloadChapter).start();
                }

                @Override
                public void onError(Throwable t) {
                    Log.e("ChapterLoad", "Failed to load chapter", t);
                }
            });
            holder.downloadButton.setEnabled(false);
            holder.downloadButton.setVisibility(View.INVISIBLE);
        });
    }


    @Override
    public int getItemCount() {
        return chapters.size();
    }


    public void loadPages(int position, ChapterCallback callback) {
        ChapterItem chapter = chapters.get(position);
        String slugUrl = mangaSlug;
        String chapterNumber = chapter.getNumber();
        String chapterVolume = chapter.getVolume();

        ApiProvider.getCdnApi().getChapterPageList(slugUrl, chapterNumber, chapterVolume)
                .enqueue(new Callback<ChapterResponse>() {
                    @Override
                    public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            callback.onChapterLoaded(response.body().data);
                        } else {
                            callback.onError(new Exception("Empty response or error code"));
                        }
                    }

                    @Override
                    public void onFailure(Call<ChapterResponse> call, Throwable t) {
                        callback.onError(t);
                    }
                });
    }

}