package com.example.restauran.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private Integer id;

    @Email
    @Size(min = 9, max = 45, message = "Length 9-45")
    @NotBlank(message = "Enter email")
    private String email;

    @Size(min = 6, max = 45, message = "Length 6-45")
    @NotBlank(message = "Enter password")
    private String password;

    private Integer role = 0;

}