package com.example.restauran.service;

import com.example.restauran.converters.OrdersConverter;
import com.example.restauran.dto.OrderDTO;
import com.example.restauran.entity.Orders;
import com.example.restauran.entity.Status;
import com.example.restauran.error.ValidationException;
import com.example.restauran.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultOrderService implements OrderService{

    private final OrderRepository orderRepository;
    private final OrdersConverter ordersConverter;

    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) throws ValidationException {
        return null;
    }

    @Override
    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Orders findById(Integer dishId) {
        Optional<Orders> order = orderRepository.findById(dishId);
        return order.orElse(null);
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(ordersConverter::fromOrderToOrderDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrderPrice(Orders order, Double price) throws ValidationException {

    }

    @Override
    public void updateOrderStatus(Orders order, Status status) throws ValidationException {

    }

    @Override
    public void updateUserUpdateDate(Orders order, Date date) throws ValidationException {

    }

}
