package com.ulul.carebuddies.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.ulul.carebuddies.R;

public class SplashScreenActivity extends AppCompatActivity {
    private int waktu_loading=2000;
    FirebaseAuth auth;
    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        setContentView(R.layout.acitivity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (auth.getCurrentUser() != null){
                    startActivity(new Intent(SplashScreenActivity.this, NavBottomActivity.class));
                    finish();
                } else {
                    //setelah loading maka akan langsung berpindah ke home activity
                    Intent home = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(home);
                    finish();
                }

            }
        },waktu_loading);
    }

}
