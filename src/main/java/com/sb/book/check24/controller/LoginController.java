package com.sb.book.check24.controller;

import com.sb.book.check24.to.LoginInformation;
import com.sb.book.check24.validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/task")
public class LoginController {


    @Autowired
    LoginValidator loginValidator;

    @GetMapping("/login")
    public ModelAndView showForm(@ModelAttribute LoginInformation loginInformation) {
        ModelAndView modelAndView = new ModelAndView("login.html");
        return modelAndView;
    }

    @PostMapping("/logged-in")
    public ModelAndView redirectForm(@Valid LoginInformation loginInformation, BindingResult bindingResult) {

        loginValidator.validate(loginInformation, bindingResult);
        if (bindingResult.hasErrors()) {

            ModelAndView modelAndView = new ModelAndView("login.html");
            modelAndView.addObject("loginInformation", loginInformation);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(new RedirectView("/task/books"));

        return modelAndView;
    }

}
