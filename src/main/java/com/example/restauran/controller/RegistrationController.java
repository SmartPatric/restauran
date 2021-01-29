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

    @GetMapping(value="/reg")
    public String registrationForm(Model model) {
        model.addAttribute("users", new Users());
        return "registration";
    }

    @PostMapping(value="/result")
    public String registrationSubmit(@ModelAttribute @Valid Users users, Model model){
        UsersDTO usersDTO = usersService.findByEmail(users.getEmail());
        if(usersDTO==null){
            model.addAttribute("users", users);
            try {
                usersService.saveUser(usersConverter.fromUserToUserDto(users));
            }
            catch (ValidationException e){
                model.addAttribute("message", "Not valid");
                return "error";
            }
            return "result";
        }
        model.addAttribute("message", "User with such email exists");
        return "error";

    }


}
