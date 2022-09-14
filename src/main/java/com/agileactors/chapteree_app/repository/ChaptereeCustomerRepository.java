package com.agileactors.chapteree_app.repository;

import com.agileactors.chapteree_app.model.ChaptereeCustomer;
import com.agileactors.chapteree_app.model.ChaptereeCustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChaptereeCustomerRepository extends JpaRepository<ChaptereeCustomer, ChaptereeCustomerId> {

}
