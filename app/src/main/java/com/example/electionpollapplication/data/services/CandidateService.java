package com.example.electionpollapplication.data.services;

import com.example.electionpollapplication.data.entities.Candidate;

import java.util.ArrayList;
import java.util.List;

public class CandidateService {
    private static CandidateService instance;
    private final List<Candidate> candidateList = new ArrayList<>();

    private Long lastInsertedId = 1L;

    public static synchronized void getInstance() {
        if (instance == null) {
            instance = new CandidateService();
        }
    }

    public void candidateFactory() {
        this.candidateList.add(new Candidate(this.lastInsertedId, "https://media.gazetadopovo.com.br/2023/02/06061310/Lula-1.jpg", "Zé Rouba Fácil", "Promete transparência, mas esquece o que é vidro.", 0L));
        this.candidateList.add(new Candidate(this.lastInsertedId += 1, "https://media.gazetadopovo.com.br/2023/02/06061310/Lula-1.jpg", "Maria Mãos Leves", "Especialista em pegar oportunidades... e o que mais estiver solto.", 0L));
        this.candidateList.add(new Candidate(this.lastInsertedId += 1, "https://media.gazetadopovo.com.br/2023/02/06061310/Lula-1.jpg", "Tonho do Papo", "Fala bonito, mas o plano de governo é um PowerPoint vazio.", 0L));
        this.candidateList.add(new Candidate(this.lastInsertedId += 1, "https://media.gazetadopovo.com.br/2023/02/06061310/Lula-1.jpg","Dona Promessa", "Promete até o impossível, e entrega panfletos.", 0L));
        this.candidateList.add(new Candidate(this.lastInsertedId += 1, null,"Nulo", "", 0L));
        this.candidateList.add(new Candidate(this.lastInsertedId += 1, null,"Branco", "", 0L));
        this.candidateList.add(new Candidate(this.lastInsertedId += 1, null,"Não sei", "", 0L));
    }


    public List<Candidate> getCandidateList() {
        return candidateList;
    }

    public Candidate addNewCandidate(String name, String image, String description, Long votingIntentions) {
        Candidate newCandidate = new Candidate(
                this.lastInsertedId++,
                image,
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
