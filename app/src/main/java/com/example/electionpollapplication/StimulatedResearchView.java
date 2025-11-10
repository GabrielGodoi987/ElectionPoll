package com.example.electionpollapplication;

import android.annotation.SuppressLint;
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

import com.example.electionpollapplication.adapters.CandidateAdapter;
import com.example.electionpollapplication.data.entities.Candidate;
import com.example.electionpollapplication.data.services.CandidateService;
import com.example.electionpollapplication.utils.AppNavigator;

import java.util.List;

public class StimulatedResearchView extends AppCompatActivity {
    RecyclerView candidateReciclerView;
    CandidateService candidateService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stimulated_research_view);

         this.candidateService = new CandidateService();
        candidateService.candidateFactory();

        candidateReciclerView = findViewById(R.id.candidateReciclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        candidateReciclerView.setLayoutManager(linearLayoutManager);
        candidateReciclerView.setAdapter(new CandidateAdapter(this, candidateService.getCandidateList(), candidate -> {
            this.showAlertDialog(candidate.getId(), candidate.getName());
        }));
        candidateReciclerView.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void showAlertDialog(Long id, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(String.format("Você confirma o seu voto em %s?", name));
        Candidate candidate = this.candidateService.findOne(id);

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (candidate == null){
                    Toast.makeText(StimulatedResearchView.this, "Candidato não encontrado", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(StimulatedResearchView.this, "Ação confirmada!", Toast.LENGTH_SHORT).show();
                AppNavigator.goTo(StimulatedResearchView.this, ProblemSetActivity.class);

                Long intentions = candidate.getVotingIntetions() + 1L;
                Toast.makeText(StimulatedResearchView.this, intentions.toString(), Toast.LENGTH_SHORT).show();
                candidate.setVotingIntetions(intentions);
                Toast.makeText(StimulatedResearchView.this, candidate.getVotingIntetions().toString(), Toast.LENGTH_SHORT).show();
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