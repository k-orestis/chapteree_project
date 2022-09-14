package com.agileactors.chapteree_app.service;

import com.agileactors.chapteree_app.model.ChaptereeCustomer;
import com.agileactors.chapteree_app.model.ChaptereeCustomerId;
import com.agileactors.chapteree_app.repository.ChaptereeCustomerRepository;
import com.agileactors.chapteree_app.repository.ChaptereeRepository;
import com.agileactors.chapteree_app.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChaptereeCustomerServiceTest {
    ChaptereeCustomerId chaptereeCustomerId;
    ChaptereeCustomer chaptereeCustomer;
    private ChaptereeCustomerRepository chaptereeCustomerRepository = mock(ChaptereeCustomerRepository.class);
    private CustomerRepository customerRepository = mock(CustomerRepository.class);
    private ChaptereeRepository chaptereeRepository = mock(ChaptereeRepository.class);
    private ChaptereeCustomerService chaptereeCustomerService;

    @BeforeEach
    void setUp() {
        chaptereeCustomerService = new ChaptereeCustomerService(chaptereeCustomerRepository,
                customerRepository, chaptereeRepository);
        chaptereeCustomerId = new ChaptereeCustomerId(10L, 4L);
       chaptereeCustomer = new ChaptereeCustomer(10L, 4L);
    }
    @Test
    void save() {
        when(chaptereeCustomerRepository.save(chaptereeCustomer)).thenReturn(chaptereeCustomer);
        ChaptereeCustomer newChaptereeCustomer = chaptereeCustomerService.save(chaptereeCustomerId);
        assertEquals(chaptereeCustomer.getChaptereeId(), newChaptereeCustomer.getChaptereeId());
        assertEquals(chaptereeCustomer.getCustomerId(), newChaptereeCustomer.getCustomerId());

    }
}
