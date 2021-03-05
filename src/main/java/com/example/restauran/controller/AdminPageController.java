package com.example.restauran.controller;


import com.example.restauran.dto.OrderDTO;
import com.example.restauran.entity.Orders;
import com.example.restauran.service.DishService;
import com.example.restauran.service.OrderService;
import com.example.restauran.service.OrdersDishesService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminPageController {

    private final OrderService orderService;
    private final DishService dishService;
    private final OrdersDishesService ordersDishesService;


    @GetMapping(value = "/adminPage")
    public String adminPage(ModelMap model) {
        List<OrderDTO> orders = orderService.findAll();
        model.put("orders", orders);
        model.put("orders_dishes", ordersDishesService.findAllDishes());
        model.put("dishes", dishService.findAll());
        return "adminPage";
    }


    @GetMapping(value = "/adminPage/{type}/{orderId}")
    public String userCabinetchangeItem(@PathVariable(value = "type") String type,
                                        @PathVariable(value = "orderId") Integer orderId,
                                        @AuthenticationPrincipal User userAuth,
                                        ModelMap model) {
        Orders order = orderService.findById(orderId);
        orderService.nextStatus(type, order);
        return "redirect:/adminPage";
    }
}
