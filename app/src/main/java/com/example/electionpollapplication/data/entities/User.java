package com.example.electionpollapplication.data.entities;

import androidx.annotation.NonNull;

import com.example.electionpollapplication.data.enums.UserRole;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;

    private String phoneNumber;

    private UserRole userRole;

    private Candidate voteFor;

    private String created_at;

    private String longitude;
    private String latitude;




    private final Set<Problem> problemsSet = new HashSet<>();

    public User(){}
    public User(Long id, String name, String email, String password, UserRole userRole, String created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
        this.created_at = created_at;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Set<Problem> getProblemsSet() {
        return problemsSet;
    }

    public Candidate getVoteFor() {
        return voteFor;
    }

    public void setVoteFor(Candidate voteFor) {
        this.voteFor = voteFor;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
