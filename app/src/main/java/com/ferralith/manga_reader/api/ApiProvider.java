package com.ferralith.manga_reader.api;

public class ApiProvider {
    public static final String BASE_API = "https://mangalib.me/api/";
    public static final String BASE_CDN = "https://api.cdnlibs.org/api/";

    private static MangaApi mangaApi;
    private static CdnApi cdnApi;

    public static MangaApi getMangaApi() {
        if (mangaApi == null) {
            mangaApi = ApiClient.getRetrofit(BASE_API).create(MangaApi.class);
        }
        return mangaApi;
    }

    public static CdnApi getCdnApi() {
        if (cdnApi == null) {
            cdnApi = ApiClient.getRetrofit(BASE_CDN).create(CdnApi.class);
        }
        return cdnApi;
    }
}