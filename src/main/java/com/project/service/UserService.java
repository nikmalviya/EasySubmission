package com.project.service;

import com.project.entity.User;
import com.project.entity.UserType;
import com.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(int userId) {
        return this.userRepository.findById(userId).orElse(null);
    }

    public List<User> getUserList() {
        return this.userRepository.findAll();
    }
    public List<User> getUserListByType(UserType type) {
        return this.userRepository.findAllByUserType(type);
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }
    public void deleteUserById(int id) {
        this.userRepository.deleteById(id);
    }
}
