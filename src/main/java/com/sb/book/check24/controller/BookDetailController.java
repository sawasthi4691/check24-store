package com.sb.book.check24.controller;

import com.sb.book.check24.entity.Book;
import com.sb.book.check24.service.IBooKService;
import com.sb.book.check24.service.IBookDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/task")
public class BookDetailController {

    @Autowired
    IBookDetailService bookDetailService;

    @Autowired
    IBooKService booKService;

    @GetMapping(path = "/details")
    public ModelAndView getCollaberativeFilteredBooks(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView("bookDetails.html");

        Book detailedBook = booKService.findBookById(id);
        modelAndView.addObject("detailedBook", detailedBook);

        List<Book> suggestedBooks = bookDetailService.bookSuggestions(detailedBook);
        modelAndView.addObject("suggestedBooks", suggestedBooks);

        return modelAndView;
    }


}
