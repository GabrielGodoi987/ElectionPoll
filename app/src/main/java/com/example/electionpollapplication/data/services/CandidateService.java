package com.example.electionpollapplication.data.services;

import com.example.electionpollapplication.data.entities.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateService {
    private static CandidateService instance;
    private final List<Candidate> candidateList = new ArrayList<>();

    private Long lastInsertdId = 1L;

    public static synchronized void getInstance() {
        if (instance == null) {
            instance = new CandidateService();
        }
    }

    public void candidateFactory() {
        this.candidateList.add(new Candidate(this.lastInsertdId, "Zé Rouba Fácil", "Promete transparência, mas esquece o que é vidro.", 120L));
        this.candidateList.add(new Candidate(this.lastInsertdId += 1, "Maria Mãos Leves", "Especialista em pegar oportunidades... e o que mais estiver solto.", 230L));
        this.candidateList.add(new Candidate(this.lastInsertdId += 1, "Tonho do Papo", "Fala bonito, mas o plano de governo é um PowerPoint vazio.", 180L));
        this.candidateList.add(new Candidate(this.lastInsertdId += 1, "Dona Promessa", "Promete até o impossível, e entrega panfletos.", 95L));
    }


    public List<Candidate> getCandidateList() {
        return candidateList;
    }

    public Candidate addNewCandidate(String name, String description, Long votingIntentions) {
        Candidate newCandidate = new Candidate(
                this.lastInsertdId++,
                name,
                description,
                votingIntentions
        );
        this.candidateList.add(newCandidate);
        return newCandidate;
    }

    public Candidate findOne(Long id) {
        for (Candidate c : candidateList) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }
}
