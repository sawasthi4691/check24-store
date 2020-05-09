package com.sb.book.check24.repository;

import com.sb.book.check24.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends CrudRepository<Book, Number> {

    Book findByBookId(String bookId);
}