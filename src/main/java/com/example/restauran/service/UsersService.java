package com.example.restauran.service;

import com.example.restauran.dto.UsersDTO;
import com.example.restauran.entity.Role;
import com.example.restauran.entity.Users;
import com.example.restauran.error.ValidationException;

import java.util.List;

public interface UsersService {

    UsersDTO saveUser(UsersDTO usersDto) throws ValidationException;

    void deleteUser(Integer userId);

    UsersDTO findByEmail(String email);

    List<UsersDTO> findAll();

    void updateUserRole(Users user, Role role) throws ValidationException;
    void updateUserEmail(Users user, String email) throws ValidationException;
    void updateUserPassport(Users user, String password) throws ValidationException;
    void updateUserActive(Users user, Boolean active) throws ValidationException;

}
