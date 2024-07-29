package com.example.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class signup extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = findViewById(R.id.signup_username);
        passwordEditText = findViewById(R.id.signup_password);
        Button signUpButton = findViewById(R.id.signup_button);

        databaseHelper = new DatabaseHelper(this);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(signup.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isAdded = databaseHelper.addUser(username, password);
                    if (isAdded) {
                        Toast.makeText(signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(signup.this, loginactivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
