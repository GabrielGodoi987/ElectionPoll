package com.example.electionpollapplication.data.services;

import com.example.electionpollapplication.data.entities.Candidate;
import com.example.electionpollapplication.data.entities.User;
import com.example.electionpollapplication.data.enums.UserRole;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserService {
    private static UserService instance;
    private final List<User> usersData = new ArrayList<>();
    private Long lastInsertedId = 0L;

    private UserService() {
    }

    public static synchronized UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void usersFactory() {
        usersData.add(new User(++lastInsertedId, "Joao example", "joao@example.com", "1234", UserRole.INTERVIEWER, this.dateTimeGenerator()));
        usersData.add(new User(++lastInsertedId, "Maria example", "maria@example.com", "1234", UserRole.INTERVIEWER, this.dateTimeGenerator()));
        usersData.add(new User(++lastInsertedId, "Gabriel Godoi", "gabriel@example.com", "1234", UserRole.ADMIN, this.dateTimeGenerator()));
    }

    public User createNewUser() {
        User newUser = new User(
                ++lastInsertedId,
                "",
                "",
                "",
                UserRole.VOTER,
                this.dateTimeGenerator()
        );
        usersData.add(newUser);
        return newUser;
    }

    public User findOne(Long id) {
        return usersData.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User loginUser(String email, String password) {
        for (User user : usersData) {
            if (Objects.equals(user.getEmail(), email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public List<User> getUsersData() {
        return usersData;
    }

    private String dateTimeGenerator() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy'T'00:00");
        return sdf.format(currentTime);
    }


    public void cleanData() {
        this.getUsersData().clear();
    }
}
