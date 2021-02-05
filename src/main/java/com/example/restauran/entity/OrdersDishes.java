package com.example.restauran.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders_dishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrdersDishesPK.class)
public class OrdersDishes {

    @Id
    private Integer order_id;

    @Id
    private Integer dish_id;

    @Column(name = "amount")
    private Integer amount = 1;


    public void increaseAmount() {
        this.amount++;
    }

    public void decreaseAmount() {
        this.amount--;
    }
}
