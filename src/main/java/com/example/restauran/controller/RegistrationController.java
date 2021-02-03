package com.example.restauran.controller;

import com.example.restauran.dto.UsersDTO;
import com.example.restauran.entity.Users;
import com.example.restauran.error.ValidationException;
import com.example.restauran.converters.UsersConverter;
import com.example.restauran.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final UsersService usersService;
    private final UsersConverter usersConverter;

    @GetMapping(value = "/registration")
    public String registrationForm(Model model) {
        model.addAttribute("users", new Users());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registrationSubmit(@ModelAttribute @Valid Users user, ModelMap model) {

        UsersDTO userFromDb = usersService.findByEmail(user.getEmail());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        try {
            usersService.saveUser(usersConverter.fromUserToUserDto(user));
            //throw  new ValidationException("ex");

        } catch (ValidationException e) {
            model.addAttribute("message", "Not valid");
            return "registration";
        }
        return "redirect:/";
    }


}
