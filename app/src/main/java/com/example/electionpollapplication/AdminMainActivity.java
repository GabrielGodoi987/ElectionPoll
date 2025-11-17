package com.example.electionpollapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.electionpollapplication.data.entities.User;
import com.example.electionpollapplication.data.enums.UserRole;
import com.example.electionpollapplication.data.services.UserService;

public class AdminMainActivity extends AppCompatActivity {


    TextView tvTotalEntrevistados;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_main);


        userService = UserService.getInstance();

        tvTotalEntrevistados = findViewById(R.id.tvTotalEntrevistados);

        int countOnlyVoters = 0;

        for(User user : userService.getUsersData()){
            if (user.getUserRole() == UserRole.VOTER){
                countOnlyVoters++;
            }
        }

        tvTotalEntrevistados.setText(String.format("Quantidade de entrevistados %d", countOnlyVoters));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}