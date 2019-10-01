package com.ulul.medbuddies.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulul.medbuddies.contract.AuthContract;
import com.ulul.medbuddies.ui.activity.LoginActivity;
import com.ulul.medbuddies.ui.activity.NavBottomActivity;

public class AuthPresenter implements AuthContract.Presenter {
    FirebaseAuth mAuth;
    Context context;
    AuthContract.View authContract;
    String username;
    String email;
    String password;
    DatabaseReference databaseReference;


    public AuthPresenter(AuthContract.View authContract){
        this.authContract = authContract;
        this.username = "";
        this.email = "";
        this.password = "";

        databaseReference = FirebaseDatabase.getInstance().getReference();

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void setLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void setRegister(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public void login() {
        authContract.onLoad();

        if (email.equals("")){
            authContract.message("Silahkan Input Email Anda");
        }else if (password.equals("")){
            authContract.message("Silahkan Input Password Anda");
        }else{
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                authContract.getCurrentUser(user);

                                checkData();

                                authContract.message("Authentication Berhasil.");
                                authContract.onSuccess();

                            } else {
                                // If sign in fails, display a message to the user.
                                authContract.message("Authentication failed.");
                                authContract.onError();
                            }
                        }
                    });
        }
    }

    public void checkData(){
        databaseReference.child("user").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    authContract.onSuccess();
                } else {
                    authContract.checkData();
//                    authContract.onError();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                authContract.onError();
            }
        });
    }

    @Override
    public void register() {

    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
