package com.agileactors.chapteree_app.model;

import java.io.Serializable;

public class ChaptereeCustomerId implements Serializable {

        private Long chaptereeId;

        private Long customerId;

    public ChaptereeCustomerId(Long chaptereeId, Long customerId) {
        this.chaptereeId = chaptereeId;
        this.customerId = customerId;
    }
    public ChaptereeCustomerId() {};
}
