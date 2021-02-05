package com.example.restauran.service;

import com.example.restauran.converters.OrdersConverter;
import com.example.restauran.dto.OrderDTO;
import com.example.restauran.entity.Orders;
import com.example.restauran.entity.Status;
import com.example.restauran.error.ValidationException;
import com.example.restauran.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultOrderService implements OrderService{

    private final OrderRepository orderRepository;
    private final OrdersConverter ordersConverter;

    @Override
    public Orders saveOrder(Orders order){
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Orders findById(Integer orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }


    @Override
    public Orders findOrdersByUserId(Integer userId){
        List<Orders> orders = orderRepository.findByUserId(userId);
        for (Orders order : orders) {
            if(!order.getStatus().equals(Status.CANCELED) &&
                    !order.getStatus().equals(Status.CLOSED)){
                return order;
            }
        }
        return null;
    }


    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(ordersConverter::fromOrderToOrderDto)
                .collect(Collectors.toList());
    }


}
