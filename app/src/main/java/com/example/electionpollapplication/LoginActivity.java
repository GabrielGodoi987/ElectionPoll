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
import com.example.electionpollapplication.data.enums.UserRole;
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

        UserService userService = UserService.getInstance();
        userService.usersFactory();


        emailInpt = findViewById(R.id.emailAddressInpt);
        passwordInpt = findViewById(R.id.passwordInpt);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(action -> {
            User userExists = userService.loginUser(emailInpt.getText().toString(), passwordInpt.getText().toString());
            if (userExists != null) {
                Toast.makeText(this, String.format("Olá %s Seja bem-vindo de volta ao Ellection Poll", userExists.getName()), Toast.LENGTH_SHORT).show();
                if(userExists.getUserRole() == UserRole.INTERVIEWER){
                    AppNavigator.goTo(LoginActivity.this, ResearchEstimated.class);
                }else if(userExists.getUserRole() == UserRole.ADMIN){
                    AppNavigator.goTo(LoginActivity.this, AdminMainActivity.class);
                }
                return;
            }
            Toast.makeText(this, String.format("Usuário %s não existe", emailInpt.getText().toString()), Toast.LENGTH_SHORT).show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}