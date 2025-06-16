package com.ferralith.manga_reader.parser;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.ferralith.manga_reader.api.models.MangaItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Parser {
    public static void ParseAsync(Consumer<List<MangaItem>> callback) {
        new Thread(() -> {
            List<MangaItem> result = new ArrayList<>();

            try {
                URL url = new URL("https://mangalib.me/api/manga?page=1&limit=20");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }

                in.close();
                connection.disconnect();

                String json = response.toString();

                JSONObject jsonObject = new JSONObject(json);
                Log.d("MANGA", jsonObject.toString());
                JSONArray items = jsonObject.getJSONArray("data");
                for (int i = 0; i < items.length(); i++) {
                    JSONObject manga = items.getJSONObject(i);
                    String title = manga.getString("rus_name");
                    String cover = manga.getJSONObject("cover").getString("default");

                    //result.add(new MangaItem(title, cover));
                }

                // Повернення результату на головний потік
                new Handler(Looper.getMainLooper()).post(() -> callback.accept(result));

            } catch (Exception e) {
                e.printStackTrace();
                // Повертаємо порожній список у разі помилки
                new Handler(Looper.getMainLooper()).post(() -> callback.accept(result));
            }
        }).start();
    }
}
