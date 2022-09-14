package com.agileactors.chapteree_app.repository;

import com.agileactors.chapteree_app.model.Chapteree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChaptereeRepository extends JpaRepository<Chapteree, Long> {

    @Query(value = "select * from customer where customer_id IN " +
            "(select customer_id from chapteree_customer where chapteree_id = ?1)", nativeQuery = true)
    List<String> myCustomers(Long id);

}
