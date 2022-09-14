package com.agileactors.chapteree_app.model;

import java.io.Serializable;

public class ChaptereeCustomerId implements Serializable {
    public ChaptereeCustomerId() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChaptereeCustomerId) return customerId == ((ChaptereeCustomerId) obj).getCustomerId()
                && chaptereeId == ((ChaptereeCustomerId) obj).getChaptereeId();
        else return false;
    }

    public Long getChaptereeId() {
        return chaptereeId;
    }

    public void setChaptereeId(Long chaptereeId) {
        this.chaptereeId = chaptereeId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public ChaptereeCustomerId(Long chaptereeId, Long customerId) {
        this.chaptereeId = chaptereeId;
        this.customerId = customerId;
    }

    private Long chaptereeId;
    private Long customerId;
}
