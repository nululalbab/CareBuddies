package com.ulul.medbuddies.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.ulul.medbuddies.R;
import com.ulul.medbuddies.contract.HospitalContract;
import com.ulul.medbuddies.presenter.HospitalPresenter;

public class CaretakerActivity extends AppCompatActivity implements HospitalContract.View {

    HospitalPresenter presenter;

    EditText nama_rumahsakit, no_telp_rumahsakit, alamat_rumahsakit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_register);

        presenter = new HospitalPresenter(this);
        presenter.setContext(this);

        FloatingActionButton btn_submit_hospital = findViewById(R.id.btn_submit_hospital);

        nama_rumahsakit = (EditText) findViewById(R.id.nama_rumahsakit);
        no_telp_rumahsakit = (EditText) findViewById(R.id.no_telp_rumahsakit);
        alamat_rumahsakit = (EditText) findViewById(R.id.alamat_rumahsakit);

        btn_submit_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setHospital(nama_rumahsakit.getText().toString(), alamat_rumahsakit.getText().toString(), no_telp_rumahsakit.getText().toString());
                presenter.registerHospital();
            }
        });
    }
    @Override
    public void setPresenter(HospitalContract.Presenter presenter) {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void message(String msg) {

    }
}
