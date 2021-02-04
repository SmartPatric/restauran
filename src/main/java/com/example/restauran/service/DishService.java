package com.example.restauran.service;

import com.example.restauran.dto.DishDTO;
import com.example.restauran.entity.Dishes;
import com.example.restauran.error.ValidationException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DishService {

    DishDTO saveDish(DishDTO dishDTO) throws ValidationException;

    void deleteDish(Integer dishId);

    DishDTO findByName(String name);

    List<DishDTO> findAll();

    Dishes findDishById(Integer id);

    Page<Dishes> findPaginated(int pageNum, int pageSize);

}
