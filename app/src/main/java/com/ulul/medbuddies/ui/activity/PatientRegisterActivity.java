package com.ulul.medbuddies.ui.activity;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ulul.medbuddies.R;
import com.ulul.medbuddies.contract.PatientContract;
import com.ulul.medbuddies.model.DataInformation;
import com.ulul.medbuddies.presenter.PatientPresenter;

import java.util.List;

public class PatientRegisterActivity extends AppCompatActivity implements PatientContract.View {
    FloatingActionButton btn_submit;
    EditText no_telp;
    PatientPresenter presenter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        dialog = new ProgressDialog(PatientRegisterActivity.this);
        dialog.setMessage("Loading. Please wait...");

        btn_submit = (FloatingActionButton) findViewById(R.id.btn_submit);
        no_telp = (EditText) findViewById(R.id.no_telp);
        presenter = new PatientPresenter(this);
        presenter.setContext(this);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.connectPatient(no_telp.getText().toString());
            }
        });
    }

    @Override
    public void setPresenter(Object presenter) {

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
        super.onBackPressed();
    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void listPatient(List<DataInformation> list) {

    }

    @Override
    public void detailPatient(DataInformation user) {

    }
}
