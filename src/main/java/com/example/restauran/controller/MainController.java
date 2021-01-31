package com.example.restauran.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping
public class MainController {

    @GetMapping(value = "/")
    public String logInForm(Model model) {
        return "main";
    }


    @GetMapping(value = "/userCabinet")
    public String userCabinet(Model model) {
        return "userCabinet";
    }

    @GetMapping(value = "/adminPage")
    public String adminPage(Model model) {
        return "adminPage";
    }

}
