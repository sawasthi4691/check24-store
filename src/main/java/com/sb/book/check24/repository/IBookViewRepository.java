package com.sb.book.check24.repository;

import com.sb.book.check24.entity.BookViews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookViewRepository extends CrudRepository<BookViews, Number> {

}