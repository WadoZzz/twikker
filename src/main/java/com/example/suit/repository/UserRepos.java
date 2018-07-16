package com.example.suit.repository;

import com.example.suit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User, Long> {
    User findByUsername(String name);
}
