package com.sb.book.check24.utils;

import com.sb.book.check24.entity.Book;
import com.sb.book.check24.entity.BookViews;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface CSVReader {


    // TODO Need to write more generic for reading the files


    static final String SEPARATOR = ";";
    //TODO Need to write more generic mapper
    Function<String, Book> mapToBook = (line) -> {
        String[] p = line.split(SEPARATOR);
        Book book = new Book();
        //TODO More null checks and all

        book.setBookId(p[0]);
        book.setBookName(p[1]);
        book.setDetails(p[2]);
        book.setPrice(new BigDecimal(p[3]));
        book.setImageUrl(p[4]);

        return book;
    };
    //TODO Need to write more generic mapper
    Function<String, BookViews> mapToBookViews = (line) -> {
        String[] p = line.split(SEPARATOR);
        BookViews bookViews = new BookViews();
        //TODO More null checks and all

        //bookViews.setBookId(p[0]);
        bookViews.setUserName(p[1]);

        return bookViews;
    };


/*    static BufferedReader getBufferedReader(String inputFilePath) throws FileNotFoundException {
        File inputF = new File(PatinputFilePath);
        InputStream inputFS = new FileInputStream(inputF);
        return new BufferedReader(new InputStreamReader(inputFS));
    }*/

    static List<Book> processBookFile(String inputFilePath) {


        // skip the header of the csv
        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath))) {
            return stream.skip(1).map(mapToBook).collect(Collectors.toList());
        } catch (IOException e) {
            //TODO
            e.printStackTrace();

        }

        return null;
    }

    static List<BookViews> processInputBookViews(String inputFilePath) {

        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath))) {
            return stream.skip(1).map(mapToBookViews).collect(Collectors.toList());
        } catch (IOException e) {
            //TODO
            e.printStackTrace();

        }
        return null;
    }

}
