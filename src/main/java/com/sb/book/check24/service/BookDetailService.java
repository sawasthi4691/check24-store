package com.sb.book.check24.service;

import com.sb.book.check24.entity.Book;
import com.sb.book.check24.entity.BookViews;
import com.sb.book.check24.repository.IBookRepository;
import com.sb.book.check24.repository.IBookViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class BookDetailService implements IBookDetailService {

    @Autowired
    IBookRepository bookRepository;

    @Autowired
    IBookViewRepository bookViewRepository;

    // TODO This needs to be moved in utility interface
    private static Double cosineSimilarity(Boolean[] vector1, Boolean[] vector2) {
        int dotProduct = 0;
        int normA = 0;
        int normB = 0;

        for (int i = 0; i < vector1.length; i++) {
            int value1 = vector1[i] ? 1 : 0;
            int value2 = vector2[2] ? 1 : 0;

            dotProduct += value1 * value2;
            normA += Math.pow(value1, 2);
            normB += Math.pow(value2, 2);
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    /**
     * Returns suggested books by chosen book
     * TODO Exception for negative scenarios like :-
     * 1 . No suggested books found.
     * 2 . Better Exception Handling.
     *
     * @param bookValue
     * @return
     */
    public List<Book> bookSuggestions(Book bookValue) {

        List<BookViews> bookViews = new ArrayList<>();
        bookViewRepository.findAll().forEach(bookViews::add);

        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);

        // Get Unique Username can be done in data base
        List<String> userNames = new ArrayList<>(bookViews.stream().map((BookViews::getUserName)).collect(Collectors.toSet()));

        // Prepare Matrix
        Map<Book, Boolean[]> bookUserNameMatrix = getBookMatrix(bookViews, books, userNames);

        // Find that book and Cosine similarity for it
        Map<Book, Double> similarBooks = new LinkedHashMap<>();
        for (Map.Entry<Book, Boolean[]> bookUserNameMatValue : bookUserNameMatrix.entrySet()) {
            if (!bookUserNameMatValue.equals(bookValue)) {
                //System.out.println("Book " + bookValue.getBookId() + " Book " + bookUserNameMatValue.getKey().getBookId() + " Value " + cosineSimilarity(bookUserNameMatrix.get(bookValue), bookUserNameMatValue.getValue()).toString());
                similarBooks.put(bookUserNameMatValue.getKey(), cosineSimilarity(bookUserNameMatrix.get(bookValue), bookUserNameMatValue.getValue()));
            }
        }

        // Prepare
        List<Book> collectedValue = prepareSortedResultSet(bookValue, similarBooks);

        return collectedValue;
    }

    private List<Book> prepareSortedResultSet(Book bookValue, Map<Book, Double> similarBooks) {
        List<Book> collectedValue = similarBooks.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .map(bookDoubleEntry -> bookDoubleEntry.getKey()).collect(Collectors.toList());
        collectedValue.remove(bookValue);
        return collectedValue;
    }

    // TODO Move to Utility
    private Map<Book, Boolean[]> getBookMatrix(List<BookViews> bookViews, List<Book> books, List<String> userNames) {
        Map<Book, Boolean[]> bookUserNameMatrix = books.stream().collect(Collectors.toMap(book -> book, book -> new Boolean[userNames.size()]));

        for (int i = 0; i <= userNames.size() - 1; i++) {
            for (Book book : books) {

                Boolean[] integerList = bookUserNameMatrix.get(book);
                String username = userNames.get(i);
                integerList[i] = bookViews.stream().filter(bookViews1 -> bookViews1.getUserName().equals(username) && bookViews1.getBook().equals(book)).count() == 1.0 ? true : false;
                bookUserNameMatrix.put(book, integerList);

            }
            //bookUserNameMatrix.forEach((book, doubles) -> System.out.println("Book " + book + " double Array" + doubles));
        }
        return bookUserNameMatrix;
    }

}
