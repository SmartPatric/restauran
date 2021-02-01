package com.example.restauran.service;

import com.example.restauran.converters.UsersConverter;
import com.example.restauran.dto.UsersDTO;
import com.example.restauran.entity.Role;
import com.example.restauran.entity.Users;
import com.example.restauran.repository.UsersRepository;
import com.example.restauran.error.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultUsersService implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersConverter usersConverter;


    @Override
    public UsersDTO saveUser(UsersDTO usersDto) throws ValidationException {
        validateUserDto(usersDto);
        Users savedUser = usersRepository.save(usersConverter.fromUserDtoToUser(usersDto));
        return usersConverter.fromUserToUserDto(savedUser);
    }

    private void validateUserDto(UsersDTO usersDto) throws ValidationException {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.usingContext().getValidator();

        Set<ConstraintViolation<UsersDTO>> constraintViolation = validator.validateProperty(usersDto, "email");
        if (!constraintViolation.isEmpty()) {
            throw new ValidationException("Incorrect Email format");
        }
        constraintViolation = validator.validateProperty(usersDto, "password");

        if (!constraintViolation.isEmpty()) {
            throw new ValidationException("Incorrect Password format");
        }
    }

    @Override
    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public UsersDTO findByEmail(String email) {
        Users users = usersRepository.findByEmail(email);
        if (users != null) {
            return usersConverter.fromUserToUserDto(users);
        }
        return null;
    }

    @Override
    public List<UsersDTO> findAll() {
        return usersRepository.findAll()
                .stream()
                .map(usersConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUserRole(Users user, Role role) throws ValidationException {

    }

    @Override
    public void updateUserEmail(Users user, String email) throws ValidationException {

    }

    @Override
    public void updateUserPassport(Users user, String password) throws ValidationException {

    }

    @Override
    public void updateUserActive(Users user, Boolean active) throws ValidationException {

    }

}
