package com.sb.book.check24.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "TBL_BOOK_VIEWS")
@Entity
public class BookViews implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_VIEW_ID", nullable = false)
    private Integer bookViewId;

    @Column(name = "USER_NAME")
    private String userName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public BookViews() {
    }

    public BookViews(Book bookId, String userName) {
        this.book = bookId;
        this.userName = userName;
    }

    public Integer getBookViewId() {
        return bookViewId;
    }

    public void setBookViewId(Integer bookViewId) {
        this.bookViewId = bookViewId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookViews{");
        sb.append("bookViewId=").append(bookViewId);
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", book=").append(book);
        sb.append('}');
        return sb.toString();
    }
}
