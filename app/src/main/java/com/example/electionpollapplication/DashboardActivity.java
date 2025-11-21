package com.example.electionpollapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.electionpollapplication.data.entities.Candidate;
import com.example.electionpollapplication.data.entities.User;
import com.example.electionpollapplication.data.enums.UserRole;
import com.example.electionpollapplication.data.services.CandidateService;
import com.example.electionpollapplication.data.services.UserService;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvTotalInterviewed;
    private LinearLayout containerCandidateVotes;
    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        pieChart = findViewById(R.id.pieChart);
        tvTotalInterviewed = findViewById(R.id.tvTotalInterviewed);
        containerCandidateVotes = findViewById(R.id.containerCandidateVotes);

        setupPieChart();
    }

    private void setupPieChart() {
        CandidateService candidateService = CandidateService.getInstance();
        UserService userService = UserService.getInstance();

        List<Candidate> candidates = candidateService.getCandidateList();
        List<User> users = userService.getUsersData();

        ArrayList<PieEntry> entries = new ArrayList<>();

        for (Candidate c : candidates) {
            long voteCount = users.stream()
                    .filter(u -> u.getUserRole() == UserRole.VOTER)
                    .filter(u -> u.getVoteFor() != null)
                    .filter(u -> u.getVoteFor().getId().equals(c.getId()))
                    .count();

            if (voteCount > 0) {
                entries.add(new PieEntry(voteCount, c.getName()));
            }
        }

        PieDataSet dataSet = new PieDataSet(entries, "Votos por candidato");
        dataSet.setSliceSpace(2f);
        dataSet.setValueTextSize(14f);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(255, 99, 71));
        colors.add(Color.rgb(30, 144, 255));
        colors.add(Color.rgb(60, 179, 113));
        colors.add(Color.rgb(255, 215, 0));
        colors.add(Color.rgb(138, 43, 226));
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(40f);
        pieChart.setTransparentCircleRadius(50f);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.animateY(1400, Easing.EaseInOutQuad);

        pieChart.invalidate();

        // Atualiza total de entrevistados
        long total = users.stream()
                .filter(u -> u.getUserRole() == UserRole.VOTER)
                .count();

        tvTotalInterviewed.setText("Total entrevistados: " + total);
    }
}
