package com.example.restauran.repository;

import com.example.restauran.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    List<Orders> findByUserId (Integer userId);

}
