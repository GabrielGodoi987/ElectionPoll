package com.example.electionpollapplication.data.repository;

import com.example.electionpollapplication.data.entities.Candidate;

import java.util.Collections;
import java.util.List;

public class MysqlRepository implements Repository<Candidate>{
    @Override
    public List<Candidate> findAll() {
        return Collections.emptyList();
    }

    @Override
    public Candidate findOne(Long id) {
        return null;
    }

    @Override
    public Candidate createOne(Candidate data) {
        return null;
    }

    @Override
    public Candidate update(Long id, Candidate data) {
        return null;
    }

    @Override
    public void deleteOne(Long id) {

    }
}
