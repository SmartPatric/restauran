package com.example.restauran.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class OrdersDishesPK implements Serializable {

    private Integer order_id;
    private Integer dish_id;

}

