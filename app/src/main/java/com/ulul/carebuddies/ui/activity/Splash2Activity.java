package com.ulul.carebuddies.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ulul.carebuddies.R;
import com.google.firebase.auth.FirebaseAuth;

public class Splash2Activity extends AppCompatActivity {
    private int waktu_loading=2000;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        auth = FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (auth.getCurrentUser() != null){
                    startActivity(new Intent(Splash2Activity.this, NavBottomActivity.class));
                    finish();
                } else {
                    //setelah loading maka akan langsung berpindah ke home activity
                    Intent home = new Intent(Splash2Activity.this, LoginActivity.class);
                    startActivity(home);
                    finish();
                }

            }
        },waktu_loading);
    }
}
