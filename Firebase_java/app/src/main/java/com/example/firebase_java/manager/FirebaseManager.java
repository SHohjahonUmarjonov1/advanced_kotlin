package com.example.firebase_java.manager;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseManager {
    public static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public static FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    public static Boolean isSigned() {
        return firebaseUser!=null;
    }

    public void signIn(String email, String password, FirebaseHandler firebaseHandler) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseHandler.onSuccess();
                } else {
                    firebaseHandler.onError(task.getException());
                }
            }
        });
    }

    public void signUp(String email, String password, FirebaseHandler firebaseHandler) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseHandler.onSuccess();
                } else {
                    firebaseHandler.onError(task.getException());
                }
            }
        });
    }

    public void signOut() {
        firebaseAuth.signOut();
    }
}
