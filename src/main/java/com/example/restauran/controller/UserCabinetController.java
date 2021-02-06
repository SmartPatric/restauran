package com.example.restauran.controller;

import com.example.restauran.converters.UsersConverter;
import com.example.restauran.dto.UsersDTO;
import com.example.restauran.entity.Dishes;
import com.example.restauran.entity.Orders;
import com.example.restauran.entity.OrdersDishes;
import com.example.restauran.entity.Status;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserCabinetController {

    private final UsersService usersService;
    private final UsersConverter usersConverter;
    private final OrdersDishesService ordersDishesService;
    private final DishService dishService;
    private final OrderService orderService;

/*    TODO
    Clean all dishes
    Clean one dish
    */


    @GetMapping(value = "/userCabinet")
    public String userCabinet(@AuthenticationPrincipal User userAuth, ModelMap model) {

        UsersDTO user = usersService.findByEmail(userAuth.getUsername());
        Orders order = orderService.findOrdersByUserId(user.getId());

        if (order != null) {
            List<OrdersDishes> ordersDishes = ordersDishesService.findOrderDishesByOrder_id(order.getId());

            List<Dishes> dishes = new ArrayList<>();
            for (OrdersDishes od : ordersDishes) {
                Dishes dish = dishService.findDishById(od.getDish_id());
                if (dish != null) {
                    dishes.add(dish);
                }
            }

            model.addAttribute("dishes", dishes);
            model.addAttribute("orders_dishes", ordersDishes);
            model.addAttribute("order", order);
        } else {
            model.addAttribute("dishes", null);
            model.addAttribute("orders_dishes", null);
            model.addAttribute("order", null);

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

        if (order == null) {
            order = new Orders();
            order.setCreationDate(LocalDateTime.now());
            order.setUpdateDate(LocalDateTime.now());
            order.setUser(usersConverter.fromUserDtoToUser(user));
            orderService.saveOrder(order);
        }

        if (order.getStatus().equals(Status.MAKING)) {

            OrdersDishes orderDish = new OrdersDishes(order.getId(), dishId, 1);
            OrdersDishes orderDishesFromExist = ordersDishesService.findOrderDishesByOrderAndDishId(order.getId(), dishId);

            //if orderDish exists increase it amount by 1
            if (orderDishesFromExist != null) {
                orderDish = orderDishesFromExist;
                orderDish.increaseAmount();
            }

            ordersDishesService.saveOrderDish(orderDish);
        }
        return "redirect:/userCabinet";
    }

    @GetMapping(value = "/userCabinet/{type}/{dishId}")
    public String userCabinetChangeItem(@PathVariable(value = "type") String type,
                                        @PathVariable(value = "dishId") Integer dishId,
                                        @AuthenticationPrincipal User userAuth,
                                        ModelMap model) {
        UsersDTO user = usersService.findByEmail(userAuth.getUsername());
        Orders order = orderService.findOrdersByUserId(user.getId());
        OrdersDishes orderDishesFromExist = ordersDishesService.findOrderDishesByOrderAndDishId(order.getId(), dishId);
        if (type.equals("incItem")) {
            orderDishesFromExist.increaseAmount();
        } else {
            if (orderDishesFromExist.getAmount() > 1) {
                orderDishesFromExist.decreaseAmount();
            }
        }
        ordersDishesService.saveOrderDish(orderDishesFromExist);
        return "redirect:/userCabinet";
    }

    @GetMapping(value = "/approve/{orderId}")
    public String approveOrder(@PathVariable(value = "orderId") Integer orderId,
                               ModelMap model) {
        Orders order = orderService.findById(orderId);
        order.setStatus(Status.APPROVING);
        orderService.saveOrder(order);
        return "redirect:/userCabinet";
    }

}