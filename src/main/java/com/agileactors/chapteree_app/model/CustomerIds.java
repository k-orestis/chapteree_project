package com.agileactors.chapteree_app.model;

import java.io.Serializable;

public class CustomerIds implements Serializable {
    public CustomerIds() {
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CustomerIds) return customerId == ((CustomerIds) obj).getCustomerId()
                && chaptereeId == ((CustomerIds) obj).getChaptereeId();
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

    public CustomerIds(Long chaptereeId, Long customerId) {
        this.chaptereeId = chaptereeId;
        this.customerId = customerId;
    }

    private Long chaptereeId;
    private Long customerId;
}
