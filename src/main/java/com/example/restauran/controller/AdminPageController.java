package com.example.restauran.controller;


import com.example.restauran.dto.OrderDTO;
import com.example.restauran.service.DishService;
import com.example.restauran.service.OrderService;
import com.example.restauran.service.OrdersDishesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminPageController {

    @Autowired
    private final OrderService orderService;

    @Autowired
    private final DishService dishService;

    @Autowired
    private final OrdersDishesService ordersDishesService;

    @GetMapping(value = "/adminPage")
    public String adminPage(ModelMap model) {
        List<OrderDTO> orders = orderService.findAll();
        model.put("orders", orderService.findAll());
        model.put("orders_dishes", ordersDishesService.findAllDishes());
        model.put("dishes", dishService.findAll());
        return "adminPage";
    }
}
