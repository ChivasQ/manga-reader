package com.ferralith.manga_reader.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CdnApi {
    @GET("{path}")
    Call<ResponseBody> getChapters(@Path("path") String path);
}
