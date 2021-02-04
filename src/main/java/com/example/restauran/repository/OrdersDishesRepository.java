package com.example.restauran.repository;

import com.example.restauran.entity.OrdersDishes;
import com.example.restauran.entity.OrdersDishesPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDishesRepository extends JpaRepository<OrdersDishes, OrdersDishesPK> {
}
