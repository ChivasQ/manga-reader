package com.ferralith.manga_reader;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ferralith.manga_reader.view.read_mode.ReadWebToonModeAdapter;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> mangaItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        recyclerView = findViewById(R.id.recyclerview1);
        ReadWebToonModeAdapter adapter = new ReadWebToonModeAdapter(this, mangaItemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
        mangaItemList.clear();
        mangaItemList.add("https://img33.imgslib.link//manga/i-alone-level-up/chapters/214970/1.png");
        mangaItemList.add("https://img33.imgslib.link//manga/i-alone-level-up/chapters/214970/2.png");
        adapter.notifyDataSetChanged();

    }
}