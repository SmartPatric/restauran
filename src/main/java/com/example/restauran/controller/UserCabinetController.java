package com.example.restauran.controller;

import com.example.restauran.converters.UsersConverter;
import com.example.restauran.dto.OrderDTO;
import com.example.restauran.dto.UsersDTO;
import com.example.restauran.entity.Dishes;
import com.example.restauran.entity.Orders;
import com.example.restauran.entity.OrdersDishes;
import com.example.restauran.service.DishService;
import com.example.restauran.service.OrderService;
import com.example.restauran.service.OrdersDishesService;
import com.example.restauran.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserCabinetController {

    @Autowired
    private final UsersService usersService;
    @Autowired
    private final UsersConverter usersConverter;
    @Autowired
    private final OrdersDishesService ordersDishesService;
    @Autowired
    private final DishService dishService;
    @Autowired
    private final OrderService orderService;

    @GetMapping(value = "/userCabinet")
    public String userCabinet(@AuthenticationPrincipal User userAuth,
                              ModelMap model) {

        UsersDTO user = usersService.findByEmail(userAuth.getUsername());
        Orders order = orderService.findOrdersByUserId(user.getId());

        if(order!=null) {

            List<OrdersDishes> ordersDishes = ordersDishesService.findOrderDishesByOrder_id(order.getId());

            List<Dishes> dishes = new ArrayList<>();
            for (OrdersDishes od : ordersDishes) {
                Dishes dish = dishService.findDishById(od.getDish_id());
                if (dish != null) {
                    dishes.add(dish);
                }
            }
            model.addAttribute("orders_dishes", ordersDishes);
            model.addAttribute("dishes", dishes);
        }
        else {
            model.addAttribute("orders_dishes", null);
            model.addAttribute("dishes", null);
        }
        model.addAttribute("user", userAuth.getUsername());

        return "userCabinet";
    }

    @GetMapping(value = "/userCabinet/{dishId}")
    public String userCabinetAddOrder(@PathVariable(value = "dishId") Integer dishId,
                                      @AuthenticationPrincipal User userAuth,
                                      ModelMap model) {
        UsersDTO user = usersService.findByEmail(userAuth.getUsername());
        Orders order = orderService.findOrdersByUserId(user.getId());
        if(order==null){
            Orders newOrder = new Orders();
            newOrder.setUser(usersConverter.fromUserDtoToUser(user));
            LocalDateTime localDateTime = LocalDateTime.now();
            newOrder.setCreationDate(localDateTime);
            newOrder.setUpdateDate(localDateTime);
            orderService.saveOrder(newOrder);
        }
        OrdersDishes ordersDishes = new OrdersDishes();
        ordersDishes.setOrder_id(order.getId());
        ordersDishes.setDish_id(dishId);
        ordersDishes.setAmount(1);
        ordersDishesService.saveNewOrderDish(ordersDishes);
        return "redirect:/userCabinet";
    }
}
