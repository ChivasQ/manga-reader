package com.ferralith.manga_reader.api;

import com.ferralith.manga_reader.api.models.MangaListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MangaApi {
    @GET("manga")
    Call<MangaListResponse> getMangaList(
            @Query("page") int page,
            @Query("limit") int limit);
}