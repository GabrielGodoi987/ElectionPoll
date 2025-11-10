package com.example.electionpollapplication.data.entities;

import java.util.Objects;

public class Problems {
    private Long id;
    private String title;

    public Problems() {
    }

    public Problems(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Problems problems = (Problems) o;
        return Objects.equals(id, problems.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}