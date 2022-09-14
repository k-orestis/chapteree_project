package com.agileactors.chapteree_app.repository;

import com.agileactors.chapteree_app.model.Chapteree;
import com.agileactors.chapteree_app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select * from chapteree where chapteree_id IN " +
            "(select chapteree_id from chapteree_customer where customer_id = ?1)", nativeQuery = true)
    List<String> myChapterees(Long id);

}
