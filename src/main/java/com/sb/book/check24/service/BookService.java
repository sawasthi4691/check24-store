package com.sb.book.check24.service;


import com.sb.book.check24.entity.Book;
import com.sb.book.check24.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBooKService {

    @Autowired
    IBookRepository bookRepository;

    public Iterable<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(String id) {
        return bookRepository.findByBookId(id);
    }
}
