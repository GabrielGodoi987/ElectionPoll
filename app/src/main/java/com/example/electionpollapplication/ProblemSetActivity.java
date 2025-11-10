package com.example.electionpollapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electionpollapplication.adapters.problems.ProblemAdapter;
import com.example.electionpollapplication.data.services.ProblemService;

public class ProblemSetActivity extends AppCompatActivity {

    RecyclerView problemSetRecyclerView;
    ProblemService problemService;

    Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_problem_set);

        problemService = new ProblemService();
        problemService.problemsFactory();

        problemSetRecyclerView = findViewById(R.id.problemSetRecyclerView);
        confirmBtn = findViewById(R.id.confirmBtn);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        problemSetRecyclerView.setLayoutManager(linearLayoutManager);
        problemSetRecyclerView.setAdapter(new ProblemAdapter(this, problemService.getProblemList(), (problemButton, isChecked, problem) -> {
            if (isChecked) {
                Toast.makeText(this, String.format("Botão toast ativado para o problema %s, id: %s", problem.getTitle(), problem.getId().toString()), Toast.LENGTH_SHORT).show();
            }
        }));

        confirmBtn.setOnClickListener(view -> {
            Toast.makeText(this, "Clicado", Toast.LENGTH_SHORT).show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}