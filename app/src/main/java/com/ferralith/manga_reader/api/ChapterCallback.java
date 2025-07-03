package com.ferralith.manga_reader.api;

import com.ferralith.manga_reader.api.models.ChapterData;

public interface ChapterCallback {
    void onChapterLoaded(ChapterData data);
    void onError(Throwable t);
}

