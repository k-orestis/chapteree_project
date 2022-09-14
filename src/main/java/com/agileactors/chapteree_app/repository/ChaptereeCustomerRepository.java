package com.agileactors.chapteree_app.repository;

import com.agileactors.chapteree_app.model.ChaptereeCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChaptereeCustomerRepository extends JpaRepository<ChaptereeCustomer, Long> {
}
