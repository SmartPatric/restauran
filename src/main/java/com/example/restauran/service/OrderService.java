package com.example.restauran.service;

import com.example.restauran.dto.OrderDTO;
import com.example.restauran.entity.Orders;
import com.example.restauran.entity.Users;

import java.util.List;

public interface OrderService {

    Orders saveOrder(Orders order);

    void deleteOrder(Integer dishId);

    Orders findById(Integer orderId);

    Orders findOrdersByUserId(Integer userId);

    List<OrderDTO> findAll();

    void nextStatus(String type, Orders order);

    Orders createNewOrder(Users u);
}
