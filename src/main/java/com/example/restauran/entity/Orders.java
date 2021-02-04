package com.example.restauran.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.APPROVING;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Users user;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_dishes",
            joinColumns = @JoinColumn(name="order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="dish_id", referencedColumnName = "id")
    )
    private List<Dishes> dishes;

}
