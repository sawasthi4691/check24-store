package com.sb.book.check24.service;

import com.sb.book.check24.entity.Book;
import com.sb.book.check24.entity.BookViews;
import com.sb.book.check24.repository.IBookRepository;
import com.sb.book.check24.repository.IBookViewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookDetailServiceTest {

    @InjectMocks
    IBookDetailService bookDetailService = new BookDetailService();

    @Mock
    IBookRepository bookRepository;

    @Mock
    IBookViewRepository bookViewRepository;

    private Book[] books;
    private BookViews[] bookViews;

    @BeforeEach
    void setUpData() {

        books = new Book[]{new Book("b1", "Textbook A:  Unit testing basic principles", "lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum", new BigDecimal(75.234), "https://bilder.buecher.de/produkte/42/42530/42530510z.jpg"),
                new Book("b2", "Textbook B:  Machine learning fundamentals", "lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum", new BigDecimal(23.34), "https://bilder.buecher.de/produkte/48/48193/48193654z.jpg"),
                new Book("b3", "Textbook C:  Domain driven design", "lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum", new BigDecimal(120), "https://bilder.buecher.de/produkte/49/49065/49065197z.jpg")};
        Iterable<Book> iterableBook = Arrays.asList(books);
        when(bookRepository.findAll()).thenReturn(iterableBook);

        bookViews = new BookViews[]{
                new BookViews(books[0], "test1@check24.de"),
                new BookViews(books[1], "test1@check24.de"),
                new BookViews(books[0], "test2@check24.de"),
                new BookViews(books[1], "test2@check24.de"),
                new BookViews(books[2], "test2@check24.de"),
                new BookViews(books[2], "test3@check24.de")};

        Iterable<BookViews> iterableBookViews = Arrays.asList(bookViews);
        when(bookViewRepository.findAll()).thenReturn(iterableBookViews);

    }

    @Test
    void bookSuggestions() {


        // execute
        Book suggestedBooksFor = books[0];
        List<Book> suggestedBooks = bookDetailService.bookSuggestions(suggestedBooksFor);


        //validation
        assertNotNull(suggestedBooks);
        assertEquals(2, suggestedBooks.size());
        assertFalse(suggestedBooks.contains(suggestedBooksFor));
    }

    // TODO Write Negative scenarios and exception
    // TODO Write Discrete test-cases for individual method.

}