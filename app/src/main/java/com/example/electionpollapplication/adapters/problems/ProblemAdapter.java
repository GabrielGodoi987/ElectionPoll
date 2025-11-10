package com.example.electionpollapplication.adapters.problems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electionpollapplication.R;
import com.example.electionpollapplication.adapters.hooks.OnCheckChangeCheckBox;
import com.example.electionpollapplication.data.entities.Problem;

import java.util.List;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemViewHolder> {

    private Context context;
    private List<Problem> problems;
    private OnCheckChangeCheckBox onCheckChangeCheckBox;

    public ProblemAdapter(Context context, List<Problem> problemList, OnCheckChangeCheckBox onCheckChangeCheckBox) {
        this.context = context;
        this.problems = problemList;
        this.onCheckChangeCheckBox = onCheckChangeCheckBox;
    }

    @NonNull
    @Override
    public ProblemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProblemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_problem_set, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProblemViewHolder holder, int position) {
        Problem problem = problems.get(position);
        holder.textProblemTitle.setText(problem.getTitle());
        holder.checkProblem.setOnCheckedChangeListener((button, isChecked) -> onCheckChangeCheckBox.setOnChangeListener(button, isChecked));
    }

    @Override
    public int getItemCount() {
        return problems.size();
    }
}
