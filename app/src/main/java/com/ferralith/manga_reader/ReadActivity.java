package com.ferralith.manga_reader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ferralith.manga_reader.api.ApiProvider;
import com.ferralith.manga_reader.api.models.ChapterPage;
import com.ferralith.manga_reader.api.models.ChapterResponse;
import com.ferralith.manga_reader.view.ViewPager2Adapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    List<String> mangaItemList = new ArrayList<>();
    ViewPager2Adapter adapter;
    RecyclerView recyclerView;
    boolean isWebtoonMode = false;
    RadioButton radio_manga;
    RadioButton radio_webtoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        radio_manga = findViewById(R.id.radio_manga);
        radio_webtoon = findViewById(R.id.radio_webtoon);

        viewPager2 = findViewById(R.id.viewpager2);
        adapter = new ViewPager2Adapter(this, mangaItemList, isWebtoonMode);
        viewPager2.setAdapter(adapter);
        recyclerView.setAdapter(adapter);
        setReadingMode(false);

        radio_manga.setOnClickListener(v -> setReadingMode(false));
        radio_webtoon.setOnClickListener(v -> setReadingMode(true));

        loadPages();
    }
    private void loadPages() {
        String slugUrl = getIntent().getStringExtra("slug_url");
        String chapterNumber = getIntent().getStringExtra("chapter_number");
        String chapterVolume = getIntent().getStringExtra("chapter_volume");

        ApiProvider.getCdnApi().getChapterPageList(slugUrl, chapterNumber, chapterVolume)
                .enqueue(new Callback<ChapterResponse>() {
                    @Override
                    public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mangaItemList.clear();
                            for (ChapterPage page : response.body().data.pages) {
                                mangaItemList.add("https://img33.imgslib.link" + page.url);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ChapterResponse> call, Throwable t) {
                        Log.e("API", "Error loading chapter", t);
                    }
                });
    }

    public void setReadingMode(boolean webtoonMode) {
        isWebtoonMode = webtoonMode;

        adapter.setWebtoonMode(webtoonMode);

        if (webtoonMode) {
            recyclerView.setVisibility(View.VISIBLE);
            viewPager2.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            viewPager2.setVisibility(View.VISIBLE);
        }
    }
}
