package com.sb.book.check24.repository;


import com.sb.book.check24.entity.Book;
import com.sb.book.check24.entity.BookViews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 */

@Configuration
public class InitH2Database {

    private static final Logger INIT_DB_LOGGER = LoggerFactory.getLogger(InitH2Database.class);

    @Autowired
    IBookRepository bookRepository;

    @Autowired
    IBookViewRepository bookViewRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            //bookRepository.saveAll(CSVReader.processBookFile("D:\\Projects\\check24-store\\check24-store\\src\\main\\resources\\static\\books.csv"));
            //bookViewRepository.saveAll(CSVReader.processInputBookViews("D:\\Projects\\check24-store\\check24-store\\src\\main\\resources\\static\\books-views.csv"));

            bookRepository.saveAll(new ArrayList<Book>() {{
                add(new Book("b1", "Textbook A:  Unit testing basic principles", "lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum", new BigDecimal(75.234), "https://bilder.buecher.de/produkte/42/42530/42530510z.jpg"));
                add(new Book("b2", "Textbook B:  Machine learning fundamentals", "lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum", new BigDecimal(23.34), "https://bilder.buecher.de/produkte/48/48193/48193654z.jpg"));
                add(new Book("b3", "Textbook C:  Domain driven design", "lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum, lorem ipsum", new BigDecimal(120), "https://bilder.buecher.de/produkte/49/49065/49065197z.jpg"));
            }});

            bookViewRepository.saveAll(new ArrayList<BookViews>() {{
                add(new BookViews(bookRepository.findByBookId("b1"), "test1@check24.de"));
                add(new BookViews(bookRepository.findByBookId("b2"), "test1@check24.de"));
                add(new BookViews(bookRepository.findByBookId("b1"), "test2@check24.de"));
                add(new BookViews(bookRepository.findByBookId("b2"), "test2@check24.de"));
                add(new BookViews(bookRepository.findByBookId("b3"), "test2@check24.de"));
                add(new BookViews(bookRepository.findByBookId("b3"), "test3@check24.de"));
            }});

        };
    }


}
