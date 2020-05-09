package com.sb.book.check24.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Table(name = "TBL_BOOKS")
@Entity
public class Book implements Serializable {

    @Id
    @Column(name = "BOOK_ID", nullable = false, unique = true)
    private String bookId;

    @Column(name = "BOOK_NAME")
    private String bookName;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "IMAGE_URL")
    private String imageUrl;


    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<BookViews> bookViews;

    public Book() {
    }

    public Book(String bookId, String bookName, String details, BigDecimal price, String imageUrl) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.details = details;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("bookId='").append(bookId).append('\'');
        sb.append(", bookName='").append(bookName).append('\'');
        sb.append(", details='").append(details).append('\'');
        sb.append(", price=").append(price);
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", bookViews=").append(bookViews);
        sb.append('}');
        return sb.toString();
    }
}
