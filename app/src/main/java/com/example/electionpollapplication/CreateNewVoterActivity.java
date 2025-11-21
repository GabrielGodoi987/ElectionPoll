package com.example.electionpollapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.electionpollapplication.data.entities.User;
import com.example.electionpollapplication.data.services.UserService;
import com.example.electionpollapplication.utils.AppNavigator;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class CreateNewVoterActivity extends AppCompatActivity {

    private EditText inputName, inputEmail, inputPhone;
    private TextView cardUserName, cardUserPhone, cardCandidateName, cardUserLocation;
    private Button buttonConfirm, buttonFinish;

    private UserService userService;
    private User pendingUserForLocation;

    private static final int LOCATION_PERMISSION_REQUEST = 1001;

    private FusedLocationProviderClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_new_voter);

        userService = UserService.getInstance();
        Long userId = getIntent().getLongExtra("USER_ID", -1);

        // Inputs
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);

        // Cards
        cardUserName = findViewById(R.id.cardUserName);
        cardUserPhone = findViewById(R.id.cardUserPhone);
        cardCandidateName = findViewById(R.id.cardCandidateName);
        cardUserLocation = findViewById(R.id.cardUserLocation);

        // Buttons
        buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonFinish = findViewById(R.id.buttonFinish);

        // Location client
        locationClient = LocationServices.getFusedLocationProviderClient(this);

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

            getCurrentLocation(user);

            Toast.makeText(
                    this,
                    String.format("Obrigado por seu voto %s", user.getName()),
                    Toast.LENGTH_SHORT
            ).show();
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

    private void getCurrentLocation(User user) {
        pendingUserForLocation = user;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST
            );
            return;
        }

        fetchLocation(user);
    }

    @SuppressLint("MissingPermission")
    private void fetchLocation(User user) {
        locationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                double lat = location.getLatitude();
                double lon = location.getLongitude();
                user.setLatitude(String.valueOf(lat));
                user.setLongitude(String.valueOf(lon));
                cardUserLocation.setText("Longitude: " + lon + " | Latitude: " + lat);
            } else {
                cardUserLocation.setText("Não foi possível obter a localização");
            }
        }).addOnFailureListener(e -> {
            cardUserLocation.setText("Erro ao tentar obter localização");
        });
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_PERMISSION_REQUEST
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (pendingUserForLocation != null) {
                fetchLocation(pendingUserForLocation);
            }

        } else {
            cardUserLocation.setText("Permissão de localização negada");

            if (pendingUserForLocation != null) {
                pendingUserForLocation.setLatitude("Denied");
                pendingUserForLocation.setLongitude("Denied");
            }
        }
    }
}
