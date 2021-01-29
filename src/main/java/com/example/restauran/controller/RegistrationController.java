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
public class RegistrationController {
    private final UsersService usersService;
    private final UsersConverter usersConverter;

    @GetMapping(value = "/registration")
    public String registrationForm(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "exist", required = false) String exist,
                                   Model model) {
        model.addAttribute("users", new Users());
        if (error != null) {
            model.addAttribute("bad", "Некоректні дані");//в випадку некоректного вводу
        }
        if (exist != null) {
            model.addAttribute("bad", "Мейл зайнято");//в випадку якщо емейл зайнято
        }
        return "registration";
    }

    @PostMapping(value = "/result")
    public String registrationSubmit(@ModelAttribute @Valid Users users, Model model) {
        model.addAttribute("users", users);
        try {
            usersService.saveUser(usersConverter.fromUserToUserDto(users));
        } catch (ValidationException e) {
            model.addAttribute("message", "Not valid");
            return "error";
        }
        return "result";
    }


}
