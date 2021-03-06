package com.example.restauran.service;

import com.example.restauran.entity.OrdersDishes;
import com.example.restauran.repository.OrdersDishesRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultOrdersDishesService implements OrdersDishesService {

    private final OrdersDishesRepository ordersDishesRepository;

    @Override
    public List<OrdersDishes> findOrderDishesByOrder_id(Integer orderId) {
        List<OrdersDishes> ordersDishesAll = ordersDishesRepository.findAll();
        List<OrdersDishes> ordersDishesList = new ArrayList<>();
        for (OrdersDishes orderDish : ordersDishesAll) {
            if (orderDish.getOrder_id().equals(orderId)) {
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
                if (orderDish.getDish_id().equals(dishId)) return orderDish;
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

    @Override
    @Transactional
    public void changeAmount(Integer orderId, Integer dishId, String type) {
        OrdersDishes orderDishesFromExist = findOrderDishesByOrderAndDishId(orderId, dishId);
        if (type.equals("incItem")) {
            orderDishesFromExist.increaseAmount();
        } else {
            if (orderDishesFromExist.getAmount() > 1) {
                orderDishesFromExist.decreaseAmount();
            }
        }
        saveOrderDish(orderDishesFromExist);
    }

    @Override
    @Transactional
    public OrdersDishes addOrderDishToOrder(Integer orderId, Integer dishId) {
        OrdersDishes orderDish = new OrdersDishes(orderId, dishId, 1, 0d);
        OrdersDishes orderDishesFromExist = findOrderDishesByOrderAndDishId(orderId, dishId);

        //if orderDish exists increase it amount by 1
        if (orderDishesFromExist != null) {
            orderDish = orderDishesFromExist;
            orderDish.increaseAmount();
        }
        return saveOrderDish(orderDish);
    }
}
