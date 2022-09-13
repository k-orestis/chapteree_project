package com.agileactors.chapteree_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CoachChapteree {
    private Chapteree chapteree;
    private Chapteree coach;

    public CoachChapteree(Chapteree coach, Chapteree chapteree) {
        this.chapteree = chapteree;
        this.coach = coach;
    }
    public Long getChaptereeId(){
        return this.chapteree.getChaptereeId();
    }
    public void setChaptereeId(Long id){
        this.chapteree.setChaptereeId(id);
    }

    public java.lang.String getChaptereeFirstName() {
        return chapteree.getFirstName();
    }

    public void setChaptereeFirstName(java.lang.String firstName) {
        this.chapteree.setFirstName(firstName);
    }

    public java.lang.String getChaptereeLastName() {
        return chapteree.getLastName();
    }

    public void setChaptereeLastName(java.lang.String lastName) {
        this.chapteree.setLastName(lastName);
    }

    public java.lang.String getChaptereeChapter() {
        return chapteree.getChapter();
    }

    public void setChaptereeChapter(java.lang.String chapter) {
        this.chapteree.setChapter(chapter);
    }

    public String getChaptereeLevel() {
        return chapteree.getLevel();
    }

    public void setChaptereeLevel(String level) {
        this.chapteree.setLevel(level);
    }
    public Long getCoachId(){
        return this.chapteree.getCoachId();
    }
    public void setCoachId(Long id){
        this.chapteree.setCoachId(id);
    }

    public void setCoachAsChaptereeId(Long id){
        this.coach.setChaptereeId(id);
    }

    public java.lang.String getCoachFirstName() {
        return coach.getFirstName();
    }

    public void setCoachFirstName(java.lang.String firstName) {
        this.coach.setFirstName(firstName);
    }

    public java.lang.String getCoachLastName() {
        return coach.getLastName();
    }

    public void setCoachLastName(java.lang.String lastName) {
        this.coach.setLastName(lastName);
    }

    public java.lang.String getCoachChapter() {
        return coach.getChapter();
    }

    public void setCoachChapter(java.lang.String chapter) {
        this.coach.setChapter(chapter);
    }

    public String getCoachLevel() {
        return coach.getLevel();
    }

    public void setCoachLevel(String level) {
        this.coach.setLevel(level);
    }

    public void setCoachCoachId(Long id){
        this.coach.setCoachId(id);
    }
}
