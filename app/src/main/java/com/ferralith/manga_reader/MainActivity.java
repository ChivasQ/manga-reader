package com.ferralith.manga_reader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ferralith.manga_reader.models.MangaItem;
import com.ferralith.manga_reader.parser.Parser;
import com.ferralith.manga_reader.view.RecycleViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ferralith.manga_reader.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;


    private List<MangaItem> mangaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        recyclerView = findViewById(R.id.recyclerViewManga);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        RecycleViewAdapter adapter = new RecycleViewAdapter(this, mangaList);
        recyclerView.setAdapter(adapter);
        Log.d("MANGA", "AWWWWWWWWWWW");
        Parser.ParseAsync(list -> {
            mangaList.clear();
            mangaList.addAll(list);
            adapter.notifyDataSetChanged();
        });
    }

    public void startNewActivity(View v) {
        Intent intent = new Intent(this, AboutMangaActivity.class);
        startActivity(intent);
    }

}