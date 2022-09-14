package com.agileactors.chapteree_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity(name = "chapteree_customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@IdClass(CustomerIds.class)
public class ChaptereeCustomer {
    @Id
    @Column(name="chapteree_id")
    private Long chaptereeId;

    @Id
    @Column(name="customer_id")
    private Long customerId;

    public ChaptereeCustomer(Long chaptereeId, Long customerId) {
        this.chaptereeId = chaptereeId;
        this.customerId = customerId;
    }

    public ChaptereeCustomer() {
    }

    @Override
    public boolean equals(Object obj) {
       if(obj instanceof ChaptereeCustomer)
           return customerId == ((ChaptereeCustomer) obj).getCustomerId()
                   && chaptereeId == ((ChaptereeCustomer) obj).getChaptereeId();
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
}
