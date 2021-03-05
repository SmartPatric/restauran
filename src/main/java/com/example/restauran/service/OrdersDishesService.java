package com.example.restauran.service;

import com.example.restauran.entity.OrdersDishes;

import java.util.List;

public interface OrdersDishesService {

    List<OrdersDishes> findOrderDishesByOrder_id(Integer orderId);

    OrdersDishes findOrderDishesByOrderAndDishId(Integer orderId, Integer dishId);

    List<OrdersDishes> findAllDishes();

    OrdersDishes saveOrderDish(OrdersDishes ordersDishes);

    void deleteOrderDish(OrdersDishes ordersDishes);

    void changeAmount(Integer orderId, Integer dishId, String type);

    OrdersDishes addOrderDishToOrder(Integer orderId, Integer dishId);


}
