package com.ulul.medbuddies.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ulul.medbuddies.R;
import com.ulul.medbuddies.contract.AuthContract;
import com.ulul.medbuddies.model.DataInformation;
import com.ulul.medbuddies.presenter.AuthPresenter;

public class LoginActivity extends AppCompatActivity implements AuthContract.View {
    private FirebaseAuth mAuth;
    private AuthPresenter authPresenter;

    ProgressDialog dialog;

    EditText etEmail, etPassword ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dialog = new ProgressDialog(LoginActivity.this);
        dialog.setMessage("Loading. Please wait...");

        authPresenter = new AuthPresenter(this);
        authPresenter.setContext(LoginActivity.this);

        // Initialize Firebase Auth

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        Button btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(goRegister);
                finish();
            }
        });

        FloatingActionButton fabLogin = findViewById(R.id.fab_login);
        fabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                authPresenter.setLogin(email, password);
                authPresenter.login();
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    @Override
    public void setPresenter(AuthContract.Presenter presenter) {

    }

    @Override
    public void onLoad() {
        dialog.show();
    }

    @Override
    public void onError() {
        dialog.dismiss();
        Toast.makeText(LoginActivity.this, "error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void message(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCurrentUser(FirebaseUser currentUser) {
//        mAuth = currentUser;
    }

    @Override
    public void checkData() {
        dialog.dismiss();
        Intent goHome = new Intent(LoginActivity.this, DataInformationActivity.class);
        startActivity(goHome);
        finish();
    }

    @Override
    public void dataUser(DataInformation dataInformation) {
        dialog.dismiss();
        Intent goHome = new Intent(LoginActivity.this, NavBottomActivity.class);
        goHome.putExtra("role", Integer.valueOf(dataInformation.getRole()));
        goHome.putExtra("care_taker", dataInformation.getCare_taker());
        startActivity(goHome);
        finish();
    }
}
