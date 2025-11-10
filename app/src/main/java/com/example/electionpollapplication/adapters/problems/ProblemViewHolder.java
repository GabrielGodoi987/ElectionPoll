package com.example.electionpollapplication.adapters.problems;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.electionpollapplication.R;
public class ProblemViewHolder extends RecyclerView.ViewHolder {

    TextView textProblemTitle;
    CheckBox checkProblem;
    public ProblemViewHolder(@NonNull View itemView) {
        super(itemView);
        textProblemTitle = itemView.findViewById(R.id.textProblemTitle);
        checkProblem = itemView.findViewById(R.id.checkProblem);
    }
}
