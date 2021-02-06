package com.example.restauran.service;

import com.example.restauran.entity.OrdersDishes;
import com.example.restauran.repository.OrdersDishesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultOrdersDishesService implements OrdersDishesService {

    @Autowired
    private final OrdersDishesRepository ordersDishesRepository;

    @Override
    public List<OrdersDishes> findOrderDishesByOrder_id(Integer orderId) {
        List<OrdersDishes> ordersDishesAll = ordersDishesRepository.findAll();
        List<OrdersDishes> ordersDishesList = new ArrayList<>();
        for (OrdersDishes orderDish : ordersDishesAll) {
            if (orderDish.getOrder_id() == orderId) {
                ordersDishesList.add(orderDish);
            }
        }
        return ordersDishesList;
    }

    @Override
    public OrdersDishes findOrderDishesByOrderAndDishId(Integer orderId, Integer dishId) {
        List<OrdersDishes> ordersDishesListByOrder = findOrderDishesByOrder_id(orderId);
        if(ordersDishesListByOrder!=null) {
            for (OrdersDishes orderDish : ordersDishesListByOrder) {
                if (orderDish.getDish_id() == dishId) return orderDish;
            }
        }
        return null;
    }


    @Override
    public List<OrdersDishes> findAllDishes() {
        return ordersDishesRepository.findAll();
    }

    @Override
    public OrdersDishes saveOrderDish(OrdersDishes ordersDishes) {
        return ordersDishesRepository.save(ordersDishes);
    }

    @Override
    public void deleteOrderDish(OrdersDishes ordersDishes) {
        ordersDishesRepository.delete(ordersDishes);
    }
}
