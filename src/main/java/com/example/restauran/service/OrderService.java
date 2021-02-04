package com.example.restauran.service;

import com.example.restauran.dto.OrderDTO;
import com.example.restauran.entity.Orders;
import com.example.restauran.entity.Status;
import com.example.restauran.error.ValidationException;

import java.util.Date;
import java.util.List;

public interface OrderService {

    Orders saveOrder(Orders orderDTO);

    void deleteOrder(Integer dishId);

    Orders findById(Integer dishId);

    Orders findOrdersByUserId(Integer userId);

    List<OrderDTO> findAll();

    void updateOrderPrice(Orders order, Double price) throws ValidationException;
    void updateOrderStatus(Orders order, Status status) throws ValidationException;
    void updateUserUpdateDate(Orders order, Date date) throws ValidationException;

}
