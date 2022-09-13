package com.agileactors.chapteree_app.model;

public class Ids{
    private Long chaptereeId;
    private Long coachId;

    public Ids(Long chaptereeId, Long coachId) {
        this.chaptereeId = chaptereeId;
        this.coachId = coachId;
    }

    public Long getChaptereeId() {
        return chaptereeId;
    }

    public void setChaptereeId(Long chaptereeId) {
        this.chaptereeId = chaptereeId;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }
}
