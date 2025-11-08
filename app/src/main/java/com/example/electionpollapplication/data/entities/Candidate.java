package com.example.electionpollapplication.data.entities;

import java.util.Objects;

public class Candidate {

    private Long id;
    private String name;

    private String description;

    private Long votingIntetions;

    public Candidate() {
    }

    public Candidate(Long id, String name, String description, Long votingIntetions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.votingIntetions = votingIntetions;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVotingIntetions() {
        return votingIntetions;
    }

    public void setVotingIntetions(Long votingIntetions) {
        this.votingIntetions = votingIntetions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(id, candidate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
