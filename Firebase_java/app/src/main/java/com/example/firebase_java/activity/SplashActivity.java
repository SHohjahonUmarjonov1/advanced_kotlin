package com.example.firebase_java.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;
import com.example.firebase_java.R;
import com.example.firebase_java.manager.FirebaseManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initViews();
    }
    private void initViews() {
        LottieAnimationView lottieAnimationView=findViewById(R.id.animationView);
        Intent signIn=new Intent(this,SignInActivity.class);
        Intent home=new Intent(this,MainActivity.class);
        Runnable runnable= () -> {
            Log.d("&&&&",FirebaseManager.isSigned().toString());
            if (FirebaseManager.isSigned()) {
                startActivity(home);
            } else {
                startActivity(signIn);
            }
            finish();
        };
        lottieAnimationView.postOnAnimationDelayed(runnable,3000);
    }
}