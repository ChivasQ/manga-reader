package com.ferralith.manga_reader.api;

import android.util.Log;

import com.ferralith.manga_reader.api.models.ChapterData;
import com.ferralith.manga_reader.api.models.ChapterPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class DownloadManager {
    private Deque<ChapterData> ChaptersToDownload;

    public DownloadManager() {
        this.ChaptersToDownload = new ArrayDeque<ChapterData>();
    }

    public void DownloadChapter() {
        if (this.ChaptersToDownload.isEmpty()) return;

        ChapterData chapterData = this.ChaptersToDownload.removeFirst();
        List<ChapterPage> pageUrl = chapterData.pages;

        for (ChapterPage page : pageUrl) {
            String fullUrl = "https://img33.imgslib.link" + page.url;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(fullUrl)
                    .addHeader("Referer", "https://mangalib.me/")
                    .build();

            try {
                okhttp3.Response response = client.newCall(request).execute();

                if (!response.isSuccessful() || response.body() == null) {
                    Log.e("MANGA", "Download failed: " + response.code());
                    return;
                }

                InputStream inputStream = response.body().byteStream();

                java.io.File directory = new java.io.File(android.os.Environment.getExternalStoragePublicDirectory(
                        android.os.Environment.DIRECTORY_DOWNLOADS), "MangaReader/mangalib/" + pageUrl.get(0).url.split("/")[3] + "/" + chapterData.number);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String fileName = page.image;
                java.io.File file = new java.io.File(directory, fileName);

                java.io.FileOutputStream outputStream = new java.io.FileOutputStream(file);
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();
                Log.d("MANGA", page.url);
                Log.d("MANGA", "Saved file size: " + file.length() + " bytes");

                Log.d("MANGA", "Downloaded to: " + file.getAbsolutePath());

            } catch (IOException e) {
                Log.e("MANGA", "IOException while saving file", e);
            }
        }

    }


    public void AddItem(ChapterData chapterData) {
        this.ChaptersToDownload.addLast(chapterData);
    }
}
