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
            model.addAttribute("mes", "nothing");

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
            order = orderService.createNewOrder(usersConverter.fromUserDtoToUser(user));
        }

        if (order.getStatus().equals(Status.MAKING)) {
            ordersDishesService.addOrderDishToOrder(order.getId(), dishId);
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
        ordersDishesService.changeAmount(order.getId(), dishId, type);
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

    @GetMapping(value = "/remove/{orderId}/{dishId}")
    public String removeOrderDish(@PathVariable(value = "orderId") Integer orderId,
                                  @PathVariable(value = "dishId") Integer dishId,
                               ModelMap model) {
        OrdersDishes orderDish = ordersDishesService.findOrderDishesByOrderAndDishId(orderId, dishId);
        ordersDishesService.deleteOrderDish(orderDish);
        return "redirect:/userCabinet";
    }
}