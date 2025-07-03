package com.ferralith.manga_reader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ferralith.manga_reader.api.ApiProvider;
import com.ferralith.manga_reader.api.models.MangaListResponse;
import com.ferralith.manga_reader.api.models.MangaItem;
import com.ferralith.manga_reader.view.RecycleViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ferralith.manga_reader.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private List<MangaItem> mangaList = new ArrayList<>();
    private RecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        recyclerView = findViewById(R.id.recyclerViewManga);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new RecycleViewAdapter(this, mangaList);
        recyclerView.setAdapter(adapter);

        loadPages();
    }

    public void startNewActivity(View v) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    private void loadPages() {
        ApiProvider
        .getMangaApi()
        .getMangaList(1, 20)
        .enqueue(new Callback<MangaListResponse>()
        {
            @Override
            public void onResponse(Call<MangaListResponse> call, Response<MangaListResponse> response) {
                if (response.isSuccessful()) {
                    List<MangaItem> mangas = response.body().getData();
                    for (int i = 0; i < mangas.size(); i++) {
                        Log.d("MANGA", mangas.get(i).getCoverUrl());
                    }
                    mangaList.clear();
                    mangaList.addAll(mangas);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<MangaListResponse> call, Throwable t) {
                Log.e("API", "Error: " + t.getMessage());
            }
        });
    }
}