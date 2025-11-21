package com.example.electionpollapplication.adapters.admin.voters;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electionpollapplication.R;

public class VotersViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName, tvEmail, tvPhone, tvDataHora, tvLocation;
    public VotersViewHolder(@NonNull View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.tvName);
        tvEmail = itemView.findViewById(R.id.tvEmail);
        tvPhone = itemView.findViewById(R.id.tvPhone);
        tvDataHora = itemView.findViewById(R.id.tvDataHora);
        tvLocation = itemView.findViewById(R.id.tvLocation);
    }
}