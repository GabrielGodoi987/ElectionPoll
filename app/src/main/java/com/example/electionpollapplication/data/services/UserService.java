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
        CandidateService candidateService = CandidateService.getInstance();
        candidateService.candidateFactory();
        Candidate c1 = candidateService.findOne(1L);
        Candidate c2 = candidateService.findOne(2L);
        Candidate c3 = candidateService.findOne(3L);

        usersData.add(new User(++lastInsertedId, "Joao example", "joao@example.com", "1234", UserRole.INTERVIEWER, this.dateTimeGenerator()));
        usersData.add(new User(++lastInsertedId, "Maria example", "maria@example.com", "1234", UserRole.INTERVIEWER, this.dateTimeGenerator()));
        usersData.add(new User(++lastInsertedId, "Gabriel Godoi", "gabriel@example.com", "1234", UserRole.ADMIN, this.dateTimeGenerator()));

        // voters

        usersData.add(new User(++lastInsertedId,
                "Lucas Pereira", "lucas.pereira@example.com", "1234",
                "11995554433", UserRole.VOTER,
                c1, dateTimeGenerator(), "-23.5601", "-46.6501"));
        c1.setVotingIntetions(c1.getVotingIntetions() + 1);

        usersData.add(new User(++lastInsertedId,
                "Ana Oliveira", "ana.oli@example.com", "1234",
                "11998887766", UserRole.VOTER,
                c2, dateTimeGenerator(), "-23.5612", "-46.6403"));
        c2.setVotingIntetions(c2.getVotingIntetions() + 1);

        usersData.add(new User(++lastInsertedId,
                "Bruno Martins", "bruno.martins@example.com", "1234",
                "11990011223", UserRole.VOTER,
                c3, dateTimeGenerator(), "-23.5599", "-46.6304"));
        c3.setVotingIntetions(c3.getVotingIntetions() + 1);

        usersData.add(new User(++lastInsertedId,
                "Débora Souza", "deb.souza@example.com", "1234",
                "11991112233", UserRole.VOTER,
                c1, dateTimeGenerator(), "-23.5587", "-46.6298"));
        c1.setVotingIntetions(c1.getVotingIntetions() + 1);

        usersData.add(new User(++lastInsertedId,
                "Carlos Andrade", "carlos.andrade@example.com", "1234",
                "11992223344", UserRole.VOTER,
                c2, dateTimeGenerator(), "-23.5575", "-46.6312"));
        c2.setVotingIntetions(c2.getVotingIntetions() + 1);

        usersData.add(new User(++lastInsertedId,
                "Fernanda Lima", "fer.lima@example.com", "1234",
                "11993334455", UserRole.VOTER,
                c3, dateTimeGenerator(), "-23.5564", "-46.6321"));
        c3.setVotingIntetions(c3.getVotingIntetions() + 1);

        usersData.add(new User(++lastInsertedId,
                "Ricardo Silva", "ricardo.silva@example.com", "1234",
                "11994445566", UserRole.VOTER,
                c1, dateTimeGenerator(), "-23.5553", "-46.6334"));
        c1.setVotingIntetions(c1.getVotingIntetions() + 1);

        usersData.add(new User(++lastInsertedId,
                "Juliana Mendes", "juliana.mendes@example.com", "1234",
                "11995556677", UserRole.VOTER,
                c2, dateTimeGenerator(), "-23.5542", "-46.6348"));
        c2.setVotingIntetions(c2.getVotingIntetions() + 1);

        usersData.add(new User(++lastInsertedId,
                "Eduardo Ramos", "edu.ramos@example.com", "1234",
                "11996667788", UserRole.VOTER,
                c3, dateTimeGenerator(), "-23.5529", "-46.6352"));
        c3.setVotingIntetions(c3.getVotingIntetions() + 1);


        usersData.add(new User(++lastInsertedId,
                "Mariana Castro", "mariana.castro@example.com", "1234",
                "11997778899", UserRole.VOTER,
                c1, dateTimeGenerator(), "-23.5518", "-46.6360"));
        c1.setVotingIntetions(c1.getVotingIntetions() + 1);

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
}
