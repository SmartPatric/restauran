package com.example.restauran.service;

import com.example.restauran.dto.DishDTO;
import com.example.restauran.entity.Dishes;
import com.example.restauran.entity.Users;
import com.example.restauran.error.ValidationException;

import java.util.Date;
import java.util.List;

public interface DishService {

    DishDTO saveDish(DishDTO dishDTO) throws ValidationException;

    void deleteDish(Integer dishId);

    DishDTO findByName(String name);

    List<DishDTO> findAll();

    void updateUserUpdateName(Dishes dish, String name) throws ValidationException;
    void updateUserUpdateDescription(Dishes dish, String description) throws ValidationException;
    void updateUserUpdatePrice(Dishes dish, Double price) throws ValidationException;
    void updateUserUpdateImage(Dishes dish, String image) throws ValidationException;

}
