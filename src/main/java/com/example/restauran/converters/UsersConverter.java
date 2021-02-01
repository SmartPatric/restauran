package com.example.restauran.converters;
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
        users.setActive(usersDto.getActive());
        return users;
    }

    public UsersDTO fromUserToUserDto(Users users) {
        return UsersDTO.builder()
                .id(users.getId())
                .email(users.getEmail())
                .password(users.getPassword())
                .active(users.getActive())
                .role(users.getRole())
                .build();
    }
}
