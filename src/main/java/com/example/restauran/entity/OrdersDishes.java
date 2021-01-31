package com.example.restauran.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders_dishes")
@Data
@NoArgsConstructor
@IdClass(OrdersDishesPK.class)
public class OrdersDishes {

    @Id
    private Integer order_id;

    @Id
    private Integer dish_id;

    @Column(name = "amount")
    private Integer amount = 1;

}
