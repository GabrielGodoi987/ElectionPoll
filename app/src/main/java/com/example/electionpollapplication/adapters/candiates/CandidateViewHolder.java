package com.example.electionpollapplication.adapters.candiates;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electionpollapplication.R;

public class CandidateViewHolder extends RecyclerView.ViewHolder {

    ImageView imageCandidate;
    TextView nameTextView, descriptionTextView;
    Button voteBtn;

    public CandidateViewHolder(@NonNull View itemView) {
        super(itemView);
        imageCandidate = itemView.findViewById(R.id.imageCandidate);
        nameTextView = itemView.findViewById(R.id.nameTextView);
        descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        voteBtn = itemView.findViewById(R.id.voteBtn);
    }
}
