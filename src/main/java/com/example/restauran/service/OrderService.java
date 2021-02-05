package com.example.restauran.service;

import com.example.restauran.dto.OrderDTO;
import com.example.restauran.entity.Orders;

import java.util.List;

public interface OrderService {

    Orders saveOrder(Orders order);

    void deleteOrder(Integer dishId);

    Orders findById(Integer orderId);

    Orders findOrdersByUserId(Integer userId);

    List<OrderDTO> findAll();

}
