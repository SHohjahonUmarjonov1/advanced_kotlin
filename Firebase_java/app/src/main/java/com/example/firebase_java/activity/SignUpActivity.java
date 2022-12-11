package com.example.firebase_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase_java.R;
import com.example.firebase_java.manager.FirebaseHandler;
import com.example.firebase_java.manager.FirebaseManager;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
    }
    private void initViews() {
        FirebaseManager firebaseManager = new FirebaseManager();
        EditText fullName = findViewById(R.id.fullName);
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button signUp = findViewById(R.id.register);
        Button signIn = findViewById(R.id.signIn);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fullName.getText().toString().trim().isEmpty()
                        || email.getText().toString().trim().isEmpty()
                        || password.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Yuqoridagi maydonlarni to'ldiring", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseManager.signUp(email.getText().toString().trim(), password.getText().toString().trim(), new FirebaseHandler() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(SignUpActivity.this, "Success SignUp Firebase", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onError(Exception exception) {
                            Toast.makeText(SignUpActivity.this, "Error SignUp Firebase", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}