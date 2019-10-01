package com.ulul.carebuddies.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ulul.carebuddies.R;
import com.ulul.carebuddies.contract.PatientContract;
import com.ulul.carebuddies.model.DataInformation;
import com.ulul.carebuddies.presenter.PatientPresenter;

import java.util.List;

public class PatientRegisterActivity extends AppCompatActivity implements PatientContract.View {
    Button btn_submit;
    EditText no_telp;
    PatientPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);

        btn_submit = (Button) findViewById(R.id.btn_submit);
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
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show();
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
