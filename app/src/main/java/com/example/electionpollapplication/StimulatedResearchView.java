package com.example.electionpollapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electionpollapplication.adapters.candiates.CandidateAdapter;
import com.example.electionpollapplication.data.entities.Candidate;
import com.example.electionpollapplication.data.entities.User;
import com.example.electionpollapplication.data.services.CandidateService;
import com.example.electionpollapplication.data.services.UserService;
import com.example.electionpollapplication.utils.AppNavigator;

public class StimulatedResearchView extends AppCompatActivity {
    RecyclerView candidateReciclerView;
    CandidateService candidateService;

    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stimulated_research_view);

        this.candidateService = CandidateService.getInstance();
        this.userService = UserService.getInstance();
        candidateService.candidateFactory();

        candidateReciclerView = findViewById(R.id.candidateReciclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        candidateReciclerView.setLayoutManager(linearLayoutManager);

        candidateReciclerView.setAdapter(new CandidateAdapter(this, candidateService.getCandidateList(), candidate -> {
            User newUser = this.userService.createNewUser();
            this.showAlertDialog(candidate.getId(), candidate.getName(), newUser);
        }));

        candidateReciclerView.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showAlertDialog(Long id, String name, User newUser) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(String.format("Você confirma o seu voto em %s?", name));
        Candidate candidate = this.candidateService.findOne(id);

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (candidate == null) {
                    Toast.makeText(StimulatedResearchView.this, "Candidato não encontrado", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(StimulatedResearchView.this, String.format("Voto confirmado em %s", name), Toast.LENGTH_SHORT).show();
                AppNavigator.goToWithParams(StimulatedResearchView.this, ProblemSetActivity.class, "USER_ID", newUser.getId());

                Long intentions = candidate.getVotingIntetions() + 1L;
                candidate.setVotingIntetions(intentions);
                candidate.getVoterList().add(newUser);
                newUser.setVoteFor(candidate);
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(StimulatedResearchView.this, "Ação cancelada.", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}