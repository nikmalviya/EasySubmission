package com.project.repository;

import com.project.entity.User;
import com.project.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    public List<User> findAllByUserType(UserType type);

    Optional<User> findByUsername(String username);
}
