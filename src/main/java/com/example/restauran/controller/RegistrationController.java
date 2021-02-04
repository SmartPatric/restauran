package com.example.restauran.controller;

import com.example.restauran.dto.UsersDTO;
import com.example.restauran.entity.Users;
import com.example.restauran.error.ValidationException;
import com.example.restauran.converters.UsersConverter;
import com.example.restauran.repository.UsersRepository;
import com.example.restauran.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegistrationController {

    @Autowired
    private final UsersService usersService;
    @Autowired
    private final UsersConverter usersConverter;

    @Autowired
    private final UsersRepository usersRepository;

    @GetMapping(value = "/registration")
    public String registrationForm(Model model) {
        model.addAttribute("users", new Users());
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registrationSubmit(@ModelAttribute @Valid Users user, ModelMap model) {

        Users userFromDb = usersRepository.findByEmail(user.getEmail());

/*        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        try {
            usersService.saveUser(usersConverter.fromUserToUserDto(user));

        } catch (ValidationException e) {
            model.addAttribute("message", "Not valid");
            return "registration";
        }*/
        model.addAttribute("user", userFromDb.getEmail());
        return "/task";
    }


}
