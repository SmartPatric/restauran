package com.example.restauran.converters;

import com.example.restauran.dto.DishDTO;
import com.example.restauran.entity.Dishes;
import org.springframework.stereotype.Component;

@Component
public class DishConverter {

    public Dishes fromDishDtoToDish(DishDTO dishDTO) {
        Dishes dishes = new Dishes();
        dishes.setId(dishDTO.getId());
        dishes.setName(dishDTO.getName());
        dishes.setPrice(dishDTO.getPrice());
        dishes.setImage(dishDTO.getImage());
        dishes.setDescription(dishDTO.getDescription());
        dishes.setCategory(dishDTO.getCategory());
        return dishes;
    }

    public DishDTO fromDishToDishDto(Dishes dish) {
        return DishDTO.builder()
                .id(dish.getId())
                .name(dish.getName())
                .price(dish.getPrice())
                .image(dish.getImage())
                .description(dish.getDescription())
                .category(dish.getCategory())
                .build();
    }
}
