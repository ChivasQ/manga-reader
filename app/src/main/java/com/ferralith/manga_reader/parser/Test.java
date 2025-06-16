package com.ferralith.manga_reader.parser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Test {
    public static void test1() {
        new Thread(() -> {
            Document doc = null;
            try {
                doc = Jsoup.connect("https://mangalib.me/ru/206--one-piece/read/v108/c1151")
                        .userAgent("Mozilla/5.0")
                        .get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Log.d("MANGA_IMAGE", doc.html());
            Log.d("MANGA_IMAGE", "SSSSSS");
        }).start();
    }
}
