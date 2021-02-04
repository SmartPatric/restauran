package com.example.restauran.service;

import com.example.restauran.entity.OrdersDishes;

import java.util.List;

public interface OrdersDishesService {
    List<OrdersDishes> findOrderDishesByOrder_id(Integer orderId);

    List<OrdersDishes> findAllDishes();
}
