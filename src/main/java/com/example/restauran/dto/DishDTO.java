package com.example.restauran.dto;

import com.example.restauran.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishDTO {

    private Integer id;
    private String name;
    private Double price;

    private String image;
    private String description;

    private Category category;
}
