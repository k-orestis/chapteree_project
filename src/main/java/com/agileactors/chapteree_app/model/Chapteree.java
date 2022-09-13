package com.agileactors.chapteree_app.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "chapteree")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Chapteree {
    public Chapteree(){

    }
    public Chapteree(Long chaptereeId, String firstName, String lastName, String chapter, String level) {
        this.chaptereeId = chaptereeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.chapter = chapter;
        this.level = level;
    }

    public Chapteree(Long chaptereeId, String firstName, String lastName, String chapter, String level, Long customerId) {
        this.chaptereeId = chaptereeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.chapter = chapter;
        this.level = level;
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public enum Level { LOW,
        MID,
        HIGH;}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chapteree_id")
    private Long chaptereeId;

    @Column(name = "first_name")
    private java.lang.String firstName;

    @Column(name = "last_name")
    private java.lang.String lastName;

    @Column(name = "chapter")
    private java.lang.String chapter;

    @Column(name = "level")
    private String level;

    @Column(name = "customer_id")
    private Long customerId;

    public Long getChaptereeId() {
        return chaptereeId;
    }

    public void setChaptereeId(Long chaptereeId) {
        this.chaptereeId = chaptereeId;
    }

    public java.lang.String getFirstName() {
        return firstName;
    }

    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }

    public java.lang.String getLastName() {
        return lastName;
    }

    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }

    public java.lang.String getChapter() {
        return chapter;
    }

    public void setChapter(java.lang.String chapter) {
        this.chapter = chapter;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
