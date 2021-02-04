package com.example.restauran.controller;

import com.example.restauran.dto.UsersDTO;
import com.example.restauran.entity.Dishes;
import com.example.restauran.entity.Orders;
import com.example.restauran.entity.OrdersDishes;
import com.example.restauran.service.DishService;
import com.example.restauran.service.OrderService;
import com.example.restauran.service.OrdersDishesService;
import com.example.restauran.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserCabinetController {

    private final UsersService usersService;
    private final OrdersDishesService ordersDishesService;
    private final DishService dishService;
    private final OrderService orderService;

    @GetMapping(value = "/userCabinet")
    public String userCabinet(@AuthenticationPrincipal User userAuth,
                              ModelMap model) {

        UsersDTO user = usersService.findByEmail(userAuth.getUsername());
        Orders order = orderService.findOrdersByUserId(user.getId());

        List<OrdersDishes> ordersDishes = ordersDishesService.findOrderDishesByOrder_id(order.getId());

        List<Dishes> dishes = new ArrayList<>();
        for (OrdersDishes od : ordersDishes) {
            Dishes dish = dishService.findDishById(od.getDish_id());
            if (dish != null) {
                dishes.add(dish);
            }
        }

        model.addAttribute("user", userAuth.getUsername());
        model.addAttribute("orders_dishes", ordersDishes);
        model.addAttribute("dishes", dishes);

        return "userCabinet";
    }

    @GetMapping(value = "/userCabinet/{dishId}")
    public String userCabinetAddOrder(@PathVariable(value = "dishId") Integer dishId,
                                      @AuthenticationPrincipal User user,
                                      ModelMap model) {
        //model.addAttribute("user", user.getUsername());
        return "redirect:/userCabinet";
    }
}
