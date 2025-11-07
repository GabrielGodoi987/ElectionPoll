package com.example.electionpollapplication.data.services;

import com.example.electionpollapplication.data.entities.User;
import com.example.electionpollapplication.data.enums.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static UserService instance;
    private final List<User> usersData = new ArrayList<>();


    public UserService() {
    }

    public List<User> getUsersData() {
        return usersData;
    }

    public static synchronized void getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
    }

    public void initiateUsers() {
        usersData.add(new User(1L, "Joao example", "joao@example.com", "1234", UserRole.INTERVIEWER));
        usersData.add(new User(2L, "Maria example", "maria@example.com", "1234", UserRole.INTERVIEWER));
    }

    public User loginUser(String email, String password) {
        for (User user : this.usersData) {
            if (Objects.equals(user.getEmail(), email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}
