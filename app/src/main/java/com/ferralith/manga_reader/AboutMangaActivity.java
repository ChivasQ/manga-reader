package com.ferralith.manga_reader;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ferralith.manga_reader.api.ApiProvider;
import com.ferralith.manga_reader.api.models.ChapterItem;
import com.ferralith.manga_reader.api.models.ChaptersResponce;
import com.ferralith.manga_reader.view.ChapterAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutMangaActivity extends AppCompatActivity {

    ImageView imageCover;
    TextView textTitle;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_manga);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        imageCover = findViewById(R.id.imageCover);
        textTitle = findViewById(R.id.textTitle);
        recyclerView = findViewById(R.id.recyclerChapters);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Glide.with(this)
                    .load(bundle.getString("imageCover"))
                    .into(imageCover);
            textTitle.setText(bundle.getString("textTitle") + " " + bundle.getString("slug_url"));

            ApiProvider.getCdnApi().getChapters("manga/"+bundle.getString("slug_url")+"/chapters")
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                try {
                                    String json = response.body().string();
                                    ChaptersResponce chaptersRes = new Gson().fromJson(json, ChaptersResponce.class);

                                    List<ChapterItem> chapters = chaptersRes.getData();
                                    ChapterAdapter adapter = new ChapterAdapter(chapters, bundle.getString("slug_url"));

                                    recyclerView.setLayoutManager(new LinearLayoutManager(AboutMangaActivity.this));
                                    recyclerView.setAdapter(adapter);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });

        }
    }
}