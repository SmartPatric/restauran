package com.example.restauran.repository;

import com.example.restauran.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dishes, Integer> {

    Dishes findByCategory(String category);

}