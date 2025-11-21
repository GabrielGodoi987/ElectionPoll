package com.example.electionpollapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.electionpollapplication.adapters.admin.voters.VotersAdapter;
import com.example.electionpollapplication.data.entities.User;
import com.example.electionpollapplication.data.enums.UserRole;
import com.example.electionpollapplication.data.services.UserService;

import java.util.List;

public class AdminVotersProfilesActivity extends AppCompatActivity {

    private RecyclerView rvVoters;
    private VotersAdapter adapter;
    private List<User> votersList;

    private UserService userService;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_voters_profiles);
        userService = UserService.getInstance();
        rvVoters = findViewById(R.id.rvVoters);
        rvVoters.setLayoutManager(new LinearLayoutManager(this));

        votersList = userService.getUsersData().stream().filter(user -> user.getUserRole() == UserRole.VOTER).toList();

        adapter = new VotersAdapter(this, votersList);
        rvVoters.setAdapter(adapter);
    }
}