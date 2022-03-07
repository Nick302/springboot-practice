package com.example.letscodeinit.repos;

import com.example.letscodeinit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username); //поиск по юзернейму
    User findByActivationCode(String code);
}
