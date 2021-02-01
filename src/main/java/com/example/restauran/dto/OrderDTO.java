package com.example.restauran.dto;

import com.example.restauran.entity.Dishes;
import com.example.restauran.entity.Status;
import com.example.restauran.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Integer id;

    private Double price;

    private Status status;
    private Users user;

    private Date creationDate;
    private Date updateDate;

    private List<Dishes> dishes;

}
