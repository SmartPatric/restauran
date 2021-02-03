package com.example.restauran.converters;

import com.example.restauran.dto.OrderDTO;
import com.example.restauran.entity.Orders;
import org.springframework.stereotype.Component;


@Component
public class OrdersConverter {

    public Orders fromOrderDtoToOrder(OrderDTO orderDTO) {
        Orders order = new Orders();
        order.setId(orderDTO.getId());
        order.setCreationDate(orderDTO.getCreationDate());
        order.setUpdateDate(orderDTO.getUpdateDate());
        order.setStatus(orderDTO.getStatus());
        order.setUser(orderDTO.getUser());
        order.setDishes(orderDTO.getDishes());
        return order;
    }

    public OrderDTO fromOrderToOrderDto(Orders order) {
        return OrderDTO.builder()
                .id(order.getId())
                .creationDate(order.getCreationDate())
                .updateDate(order.getUpdateDate())
                .status(order.getStatus())
                .user(order.getUser())
                .dishes(order.getDishes())
                .build();
    }
}

