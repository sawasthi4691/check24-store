package com.sb.book.check24.service;

import com.sb.book.check24.entity.Book;

public interface IBooKService {

    Iterable<Book> listAllBooks();

    Book findBookById(String id);
}
