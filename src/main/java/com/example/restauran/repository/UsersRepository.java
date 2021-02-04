package com.example.restauran.repository;

import com.example.restauran.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    @Query("select u from Users u where email = ?1")
    Users findByEmail(String email);

}
