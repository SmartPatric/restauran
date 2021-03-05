package com.example.restauran.service;

import com.example.restauran.converters.OrdersConverter;
import com.example.restauran.dto.OrderDTO;
import com.example.restauran.entity.Orders;
import com.example.restauran.entity.Status;
import com.example.restauran.entity.Users;
import com.example.restauran.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public void nextStatus(String type, Orders order) {
        if(type.equals("cancel")){
            order.setStatus(Status.CANCELED);
        }
        else{
            Integer currentStatusId = order.getStatus().getId();
            Integer nextStatus = currentStatusId+1;
            if(nextStatus<5){
                order.setStatus(Status.findStatusById(nextStatus));
            }
        }
        order.setUpdateDate(LocalDateTime.now());
        saveOrder(order);
    }

    @Override
    public Orders createNewOrder(Users u) {
        Orders order = new Orders();
        order.setCreationDate(LocalDateTime.now());
        order.setUpdateDate(LocalDateTime.now());
        order.setUser(u);
        order = saveOrder(order);
        return order;
    }


}
