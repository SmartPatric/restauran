package com.example.restauran.service;
import com.example.restauran.dto.UsersDTO;
import com.example.restauran.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UsersConverter {

    public Users fromUserDtoToUser(UsersDTO usersDto) {
        Users users = new Users();
        users.setId(usersDto.getId());
        users.setEmail(usersDto.getEmail());
        users.setPassword(usersDto.getPassword());
        users.setRole(usersDto.getRole());
        return users;
    }

    public UsersDTO fromUserToUserDto(Users users) {
        return UsersDTO.builder()
                .id(users.getId())
                .email(users.getEmail())
                .password(users.getPassword())
                .role(users.getRole())
                .build();
    }
}
