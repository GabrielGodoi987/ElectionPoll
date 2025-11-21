package com.example.electionpollapplication.adapters.admin.voters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electionpollapplication.R;
import com.example.electionpollapplication.data.entities.User;

import java.util.List;

public class VotersAdapter extends RecyclerView.Adapter<VotersViewHolder> {

    private final Context context;
    private final List<User> voters;

    public VotersAdapter(Context context, List<User> voters) {
        this.context = context;
        this.voters = voters;
    }

    @NonNull
    @Override
    public VotersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.voter_item_profile, parent, false);
        return new VotersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VotersViewHolder holder, int position) {
        User voter = voters.get(position);

        holder.tvName.setText(voter.getName());
        holder.tvEmail.setText(voter.getEmail());
        holder.tvPhone.setText(voter.getPhoneNumber());
        holder.tvDataHora.setText(voter.getCreated_at());
        holder.tvLocation.setText(
                "Lat: " + voter.getLatitude() + " | Long: " + voter.getLongitude()
        );
    }

    @Override
    public int getItemCount() {
        return voters.size();
    }
}
