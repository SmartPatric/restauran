package com.example.restauran.dto;

import com.example.restauran.entity.Dishes;
import com.example.restauran.entity.Status;
import com.example.restauran.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Integer id;

    private Status status;
    private Users user;

    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    private List<Dishes> dishes;

}
