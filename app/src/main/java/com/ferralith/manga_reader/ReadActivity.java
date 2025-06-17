package com.ferralith.manga_reader;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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

        viewPager2 = findViewById(R.id.viewpager2);
        adapter = new ViewPager2Adapter(this, mangaItemList);
        viewPager2.setAdapter(adapter);

        String slugUrl = getIntent().getStringExtra("slug_url");
        String chapterNumber = getIntent().getStringExtra("chapter_number");
        String chapterVolume = getIntent().getStringExtra("chapter_volume");
        Log.d("PAGE", slugUrl);
        Log.d("PAGE", chapterNumber);
        Log.d("PAGE", chapterVolume);

        ApiProvider.getCdnApi().getChapterPageList(slugUrl, chapterNumber, chapterVolume)
                .enqueue(new Callback<ChapterResponse>() {
                    @Override
                    public void onResponse(Call<ChapterResponse> call, Response<ChapterResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            mangaItemList.clear();
                            List<ChapterPage> pages = response.body().data.pages;

                            for (ChapterPage page : pages) {
                                String url = "https://img33.imgslib.link" + page.url;
                                Log.d("Page Images URL", "https://img33.imgslib.link" + url);
                                mangaItemList.add(url);
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
}
