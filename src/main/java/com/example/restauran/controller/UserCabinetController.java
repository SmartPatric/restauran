package com.example.restauran.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserCabinetController {

    @GetMapping(value = "/userCabinet")
    public String userCabinet(ModelMap model) {
        return "userCabinet";
    }
}
