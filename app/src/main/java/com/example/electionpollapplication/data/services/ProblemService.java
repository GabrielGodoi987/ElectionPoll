package com.example.electionpollapplication.data.services;

import com.example.electionpollapplication.data.entities.Problem;

import java.util.ArrayList;
import java.util.List;

public class ProblemService {
    private static ProblemService instance;
    List<Problem> problemList = new ArrayList<>();
    Long lastInsertedId = 0L;

    public static synchronized ProblemService getInstance() {
        if (instance == null) {
            instance = new ProblemService();
        }
        return instance;
    }

    public void problemsFactory() {
        this.problemList.add(new Problem(lastInsertedId += 1, "Saúde pública e falta de hospitais"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Educação básica e valorização dos professores"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Infraestrutura e transporte público"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Segurança pública e criminalidade"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Corrupção e má gestão dos recursos públicos"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Desemprego e geração de empregos"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Saneamento básico e acesso à água potável"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Desigualdade social e pobreza"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Meio ambiente e desmatamento"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Transparência governamental e prestação de contas"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Reforma tributária e carga de impostos"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Tecnologia e inovação no setor público"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Mobilidade urbana e trânsito nas grandes cidades"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Direitos das minorias e inclusão social"));
        this.problemList.add(new Problem(lastInsertedId += 1, "Políticas de habitação e moradia digna"));
    }

    public List<Problem> getProblemList() {
        return problemList;
    }


    public Problem findOne(Long id) {
        return problemList.stream()
                .filter(problem -> problem.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
