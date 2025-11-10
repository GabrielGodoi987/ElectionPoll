package com.example.electionpollapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.electionpollapplication.R;
import com.example.electionpollapplication.adapters.hooks.OnVoteClickListener;
import com.example.electionpollapplication.data.entities.Candidate;

import java.util.List;

public class CandidateAdapter extends RecyclerView.Adapter<CandidateViewHolder> {

    private Context context;
    private List<Candidate> items;

    private OnVoteClickListener onVoteClickListener;



    public CandidateAdapter(Context context, List<Candidate> items, OnVoteClickListener onVoteClickListener) {
        this.context = context;
        this.items = items;
        this.onVoteClickListener = onVoteClickListener;
    }

    @NonNull
    @Override
    public CandidateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CandidateViewHolder(LayoutInflater.from(context).inflate(R.layout.item_candidate, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CandidateViewHolder holder, int position) {
        Candidate candidate = items.get(position);

        Glide.with(holder.imageCandidate.getContext())
                .load(candidate.getImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageCandidate);
        holder.nameTextView.setText(candidate.getName());
        holder.descriptionTextView.setText(candidate.getDescription());
        holder.voteBtn.setOnClickListener(action -> this.onVoteClickListener.onVoteClick(candidate));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
