package com.example.firebase_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase_java.R;
import com.example.firebase_java.manager.FirebaseHandler;
import com.example.firebase_java.manager.FirebaseManager;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initViews();
    }

    private void initViews() {
        FirebaseManager firebaseManager = new FirebaseManager();
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);
        Button signUp = findViewById(R.id.signUp);
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().trim().isEmpty() || password.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Yuqoridagi maydonlarni to'ldiring", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseManager.signIn(email.getText().toString().trim(), password.getText().toString().trim(), new FirebaseHandler() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(SignInActivity.this, "Success SignIn Firebase", Toast.LENGTH_SHORT).show();
                            callHomeActivity();
                        }

                        @Override
                        public void onError(Exception exception) {
                            Toast.makeText(SignInActivity.this, "Error SignIn Firebase", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void callHomeActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}