package com.ulul.carebuddies.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ulul.carebuddies.R;
import com.ulul.carebuddies.contract.DataInformationContract;
import com.ulul.carebuddies.presenter.DataInformationPresenter;

public class DataInformationActivity extends AppCompatActivity implements DataInformationContract.View {
    DataInformationPresenter presenter;


    EditText nama, no_telp, alamat, ttl, jenis_kelamin, sumber_biaya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_information);

        presenter = new DataInformationPresenter(this);
        presenter.setContext(this);

        FloatingActionButton btn_submit = findViewById(R.id.btn_submit);

        nama = (EditText) findViewById(R.id.nama);
        no_telp = (EditText) findViewById(R.id.no_telp);
        alamat = (EditText) findViewById(R.id.alamat);
        ttl = (EditText) findViewById(R.id.ttl);
        jenis_kelamin = (EditText) findViewById(R.id.jenis_kelamin);
        sumber_biaya = (EditText) findViewById(R.id.sumber_biaya);
        final String role = "0";
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setData(nama.getText().toString(), alamat.getText().toString(), no_telp.getText().toString(),
                        ttl.getText().toString(), jenis_kelamin.getText().toString(), sumber_biaya.getText().toString(), role);
                presenter.submitData();
                }
        });
    }

    @Override
    public void setPresenter(DataInformationContract.Presenter presenter) {

    }

    @Override
    public void onLoad() {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess() {
        Intent goHome = new Intent(DataInformationActivity.this, NavBottomActivity.class);
        startActivity(goHome);
        finish();
    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
