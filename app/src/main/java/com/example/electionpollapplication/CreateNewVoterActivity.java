package com.example.electionpollapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.electionpollapplication.data.entities.User;
import com.example.electionpollapplication.data.services.UserService;
import com.example.electionpollapplication.utils.AppNavigator;

public class CreateNewVoterActivity extends AppCompatActivity {

    EditText inputName, inputEmail, inputPhone;

    TextView cardUserName, cardUserPhone, cardCandidateName;

    Button buttonConfirm, buttonFinish;

    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_new_voter);


        Toast.makeText(this, "Chegou nessa porra do caraio", Toast.LENGTH_SHORT).show();
        userService = UserService.getInstance();
        Long userId = getIntent().getLongExtra("USER_ID", -1);

        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);

        cardUserName = findViewById(R.id.cardUserName);
        cardUserPhone = findViewById(R.id.cardUserPhone);
        cardCandidateName = findViewById(R.id.cardCandidateName);


        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonFinish = findViewById(R.id.buttonFinish);

        buttonConfirm.setOnClickListener(action -> {
            User user = userService.findOne(userId);
            if (user == null) {
                Toast.makeText(this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                return;
            }

            user.setName(inputName.getText().toString());
            user.setEmail(inputEmail.getText().toString());
            user.setPhoneNumber(inputPhone.getText().toString());

            cardUserName.setText(user.getName());
            cardCandidateName.setText(user.getVoteFor().getName());
            cardUserPhone.setText(user.getPhoneNumber());

            Toast.makeText(this, String.format("Obrigado por seu voto %s", user.getName()), Toast.LENGTH_SHORT).show();
        });


        buttonFinish.setOnClickListener(action -> {
            AppNavigator.goTo(CreateNewVoterActivity.this, ResearchEstimated.class);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}