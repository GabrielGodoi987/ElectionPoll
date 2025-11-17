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
import com.example.electionpollapplication.data.entities.Problem;
import com.example.electionpollapplication.data.entities.User;
import com.example.electionpollapplication.data.services.ProblemService;
import com.example.electionpollapplication.data.services.UserService;
import com.example.electionpollapplication.utils.AppNavigator;

import java.util.ArrayList;
import java.util.List;

public class ProblemSetActivity extends AppCompatActivity {

    RecyclerView problemSetRecyclerView;
    ProblemService problemService;

    UserService userService;

    Button confirmBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_problem_set);

        problemService = ProblemService.getInstance();
        userService = UserService.getInstance();
        problemService.problemsFactory();
        List<Problem> problemList = new ArrayList<>();
        Long userId = getIntent().getLongExtra("USER_ID", -1);


        problemSetRecyclerView = findViewById(R.id.problemSetRecyclerView);
        confirmBtn = findViewById(R.id.confirmBtn);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        problemSetRecyclerView.setLayoutManager(linearLayoutManager);
        problemSetRecyclerView.setAdapter(new ProblemAdapter(this, problemService.getProblemList(), (problemButton, isChecked, problem) -> {
            if (isChecked) {
                problemList.add(problem);
            }
            if (!isChecked) {
                problemList.remove(problem);
            }
        }));

        confirmBtn.setOnClickListener(view -> {
            if(problemList.isEmpty() || problemList.size() > 10){
                Toast.makeText(this, "É necessário escolher no minimo 1 e no máximo 10 problemas", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = this.userService.findOne(userId);
            if (user == null) {
                Toast.makeText(this, String.format("User com id %s não foi encontrado", userId), Toast.LENGTH_SHORT).show();
                return;
            }
            for (Problem problem : problemList) {
                user.getProblemsSet().add(problem);
            }

            AppNavigator.goToWithParams(ProblemSetActivity.this, CreateNewVoterActivity.class,  "USER_ID", user.getId());
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}