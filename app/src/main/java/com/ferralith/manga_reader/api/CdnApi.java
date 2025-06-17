package com.ferralith.manga_reader.api;

import com.ferralith.manga_reader.api.models.ChapterResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CdnApi {
    @GET("{path}")
    Call<ResponseBody> getChapters(@Path("path") String path);

    @GET("manga/{slug}/chapter")
    Call<ChapterResponse> getChapterPageList(
            @Path("slug") String slug,
            @Query("number") String number,
            @Query("volume") String volume
    );
}
