package com.example.electionpollapplication.data.services;

import com.example.electionpollapplication.data.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserService {
    private final List<User> usersData = new ArrayList<>();


    public UserService() {
    }

    public void initiateUsers() {
        usersData.add(new User(1L, "joao@example.com", "1234", "João Silva"));
        usersData.add(new User(2L, "maria@example.com", "abcd", "Maria Oliveira"));
    }


    public List<User> getUsersData() {
        return usersData;
    }

    public User loginUser(String email, String password) {
        for (User user : this.usersData) {
            if (Objects.equals(user.getEmail(), email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }
}
