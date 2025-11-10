package com.example.electionpollapplication.data.entities;

import java.util.Objects;

public class Problem {
    private Long id;
    private String title;

    private Boolean isChecked;

    public Problem() {
    }

    public Problem(Long id, String title) {
        this.id = id;
        this.title = title;
        this.isChecked = false;
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

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return Objects.equals(id, problem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}