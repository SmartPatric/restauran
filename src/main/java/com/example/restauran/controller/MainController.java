package com.example.restauran.controller;

import com.example.restauran.dto.UsersDTO;
import com.example.restauran.service.DefaultUsersService;
import com.example.restauran.service.DishService;
import com.example.restauran.service.OrderService;
import com.example.restauran.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping
public class MainController {

    @Autowired
    DishService dishService;

    @Autowired
    UsersService usersService;

    @Autowired
    OrderService orderService;

    @GetMapping(value = "/")
    public String logInForm(ModelMap model) {
        model.put("dishes", dishService.findAll());
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

/*    @Autowired
    UsersService usersService;


/*
    @GetMapping(value = "/task")
    @ResponseBody
    public List<UsersDTO> taskList() {
        List<UsersDTO> users = usersService.findAll();
        return users;
    }*/

    @GetMapping(value = "/task")
    public String taskList(ModelMap model) {
        model.put("users", orderService.findAll());
        return "task";
    }

}
