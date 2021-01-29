package com.example.restauran.controller;

import com.example.restauran.dto.UsersDTO;
import com.example.restauran.entity.Users;
import com.example.restauran.error.ValidationException;
import com.example.restauran.service.UsersConverter;
import com.example.restauran.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class LogInController {
    private final UsersService usersService;
    private final UsersConverter usersConverter;

    @GetMapping(value = "/logIn")
    public String logInForm(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "exist", required = false) String exist,
                            Model model) {
        model.addAttribute("logUsers", new Users());
        if (error != null) {
            model.addAttribute("bad", "Некоректні дані");//в випадку некоректного вводу
        }
        if (exist != null) {
            model.addAttribute("bad", "Мейл зайнято");//в випадку якщо емейл зайнято
        }
        return "logIn";
    }

    @PostMapping(value = "/userCabinet")
    public String logInSubmit(@ModelAttribute @Valid Users users, Model model) {
        UsersDTO usersDTO = usersService.findByEmail(users.getEmail());
        if (usersDTO != null) {
            model.addAttribute("logUsers", users);
            return "userCabinet";
        }
        model.addAttribute("message", "No such user");
        return "error";
    }
}
