package com.ulul.medbuddies.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ulul.medbuddies.R;
import com.ulul.medbuddies.contract.DataInformationContract;
import com.ulul.medbuddies.model.DataInformation;
import com.ulul.medbuddies.presenter.DataInformationPresenter;

import java.util.ArrayList;
import java.util.List;

public class DataInformationActivity extends AppCompatActivity implements DataInformationContract.View {
    DataInformationPresenter presenter;


    EditText nama, no_telp, alamat, ttl, jenis_kelamin, sumber_biaya;
    Spinner role_spinner;
    String role;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_information);

        dialog = new ProgressDialog(DataInformationActivity.this);
        dialog.setMessage("Loading. Please wait...");

        presenter = new DataInformationPresenter(this);
        presenter.setContext(this);

        FloatingActionButton btn_submit = findViewById(R.id.btn_submit);

        nama = (EditText) findViewById(R.id.nama);
        no_telp = (EditText) findViewById(R.id.no_telp);
        alamat = (EditText) findViewById(R.id.alamat);
        ttl = (EditText) findViewById(R.id.ttl);
        jenis_kelamin = (EditText) findViewById(R.id.jenis_kelamin);
        sumber_biaya = (EditText) findViewById(R.id.sumber_biaya);
        role_spinner = (Spinner) findViewById(R.id.role_spinner);

        List<String> arrayList = new ArrayList<>();
        arrayList.add("Patient");
        arrayList.add("Care Taker");
        role = "1";

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        role_spinner.setAdapter(adapter);

        role_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    role = "1";
                } else {
                    role = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
        dialog.show();
    }

    @Override
    public void onError() {
        dialog.dismiss();
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dataUser(DataInformation dataInformation) {
        dialog.dismiss();
        Intent goHome = new Intent(DataInformationActivity.this, NavBottomActivity.class);
        goHome.putExtra("role", Integer.valueOf(dataInformation.getRole()));
        startActivity(goHome);
        finish();
    }
}
