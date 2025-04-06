package com.github.filipe.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.filipe.desafioapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByAccountNumber(String number);

    boolean existsByCardNumber(String number);

}
