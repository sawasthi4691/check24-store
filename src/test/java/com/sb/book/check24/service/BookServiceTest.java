package com.sb.book.check24.service;

import com.sb.book.check24.entity.Book;
import com.sb.book.check24.repository.IBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class BookServiceTest {

    @InjectMocks
    IBooKService booKService = new BookService();

    @Mock
    IBookRepository bookRepository;

    @Test
    void listAllBooks() {

        // Prepare
        Book[] books = new Book[]{new Book("bookId", "bookName", "details", new BigDecimal(10), "imageUrl")};
        Iterable<Book> iterable = Arrays.asList(books);
        when(bookRepository.findAll()).thenReturn(iterable);

        // execute
        Iterable<Book> listAllBooks = booKService.listAllBooks();

        //test
        assertEquals(iterable, listAllBooks);
    }

    @Test
    void findBookById() {
        // Prepare
        Book testBook = new Book("bookId", "bookName", "details", new BigDecimal(10), "imageUrl");
        when(bookRepository.findByBookId("bookId")).thenReturn(testBook);

        //execute
        Book book = booKService.findBookById("bookId");

        //test
        assertEquals(book, testBook);
    }

    @Test
    void findBookByIdNegativeTest() {
        // Prepare
        Book testBook = new Book("bookId", "bookName", "details", new BigDecimal(10), "imageUrl");
        when(bookRepository.findByBookId("bookId")).thenReturn(testBook);

        //execute
        Book book = booKService.findBookById("b1");

        //test
        assertEquals(book, null);
    }

    // TODO Add Exception Test cases
    // TODO Add Negative Test cases
}