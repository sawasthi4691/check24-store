package com.sb.book.check24.service;

import com.sb.book.check24.entity.Book;

import java.util.List;

public interface IBookDetailService {

    List<Book> bookSuggestions(Book book);
}
