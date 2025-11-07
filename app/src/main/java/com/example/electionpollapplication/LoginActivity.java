package com.example.electionpollapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.electionpollapplication.data.services.UserService;


public class LoginActivity extends AppCompatActivity {
    EditText emailInpt, passwordInpt;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        emailInpt = findViewById(R.id.emailAddressInpt);
        passwordInpt = findViewById(R.id.passwordInpt);
        loginBtn = findViewById(R.id.loginBtn);

        UserService userService = new UserService();

        userService.initiateUsers();

        loginBtn.setOnClickListener(action -> {
            Intent intent = new Intent(LoginActivity.this, ResearchStimulated.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}