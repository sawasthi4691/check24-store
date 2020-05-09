package com.sb.book.check24.controller;

import com.sb.book.check24.entity.Book;
import com.sb.book.check24.service.IBooKService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookControllerTest {

    @MockBean
    private IBooKService booKService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listBooks() throws Exception {

        // Prepare
        Book[] books = new Book[]{new Book("bookId", "bookName", "details", new BigDecimal(10), "imageUrl")};
        Iterable<Book> iterable = Arrays.asList(books);
        when(booKService.listAllBooks()).thenReturn(iterable);

        // execute and test
        assertNotNull(booKService);

        mockMvc.perform(get(("/task/books")))
                .andExpect(model().attributeExists("books"))
                .andExpect(view().name("books.html"));
    }
}