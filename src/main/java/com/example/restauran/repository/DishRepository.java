package com.example.restauran.repository;

import com.example.restauran.entity.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishRepository extends JpaRepository<Dishes, Integer> {

    @Override
    Optional<Dishes> findById(Integer integer);

    Dishes findByCategory(String category);

}