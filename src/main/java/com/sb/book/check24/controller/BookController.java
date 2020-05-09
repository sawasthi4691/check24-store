package com.sb.book.check24.controller;

import com.sb.book.check24.entity.Book;
import com.sb.book.check24.service.IBooKService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/task")
public class BookController {

    @Autowired
    private IBooKService booKService;

    @GetMapping("/books")
    public ModelAndView listBooks() {
        ModelAndView modelAndView = new ModelAndView("books.html");

        List<Book> books = new ArrayList<>();
        booKService.listAllBooks().forEach(books::add);

        modelAndView.addObject("books", books);
        //TODO Book need to be modeled as per the API, Right now I am using Entity Model.

        return modelAndView;
    }

}
