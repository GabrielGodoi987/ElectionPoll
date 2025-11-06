package com.example.electionpollapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.electionpollapplication.data.entities.User;
import com.example.electionpollapplication.data.services.UserService;
import com.example.electionpollapplication.utils.AppNavigator;

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
            User user =  userService.loginUser(emailInpt.getText().toString(), passwordInpt.getText().toString());
            if(user == null){
                Toast.makeText(this, "Não Autorizado", Toast.LENGTH_SHORT).show();
                return;
            }

            AppNavigator.goTo(LoginActivity.this, ResearchStimulated.class);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}