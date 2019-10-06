package com.ulul.carebuddies.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulul.carebuddies.R;
import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.model.DataInformation;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.model.Schedule;
import com.ulul.carebuddies.presenter.HospitalPresenter;
import com.ulul.carebuddies.presenter.SchedulePresenter;

import java.util.List;

import static android.Manifest.permission.CALL_PHONE;

public class DataScheduleActivity extends AppCompatActivity implements ScheduleContract.View {

    SchedulePresenter presenter;

    ProgressDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_schedule);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        FirebaseDatabase getDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference getRefenence = getDatabase.getReference();


        dialog = new ProgressDialog(DataScheduleActivity.this);
        dialog.setMessage("Loading. Please wait...");

        presenter = new SchedulePresenter(this);
        presenter.setContext(this);


        final TextView tv_nama_schedule = (TextView) findViewById(R.id.tv_nama_schedule);
        final TextView tv_jadwal_patient = (TextView) findViewById(R.id.tv_jadwal_patient);
        final TextView tv_jam_patient = (TextView) findViewById(R.id.tv_jam_patient);
        final TextView tv_obat_patient = (TextView) findViewById(R.id.tv_obat_patient);
        final EditText et_keterangan_patient = (EditText) findViewById(R.id.et_keterangan_patient);
        final FloatingActionButton fab_approve = (FloatingActionButton)findViewById(R.id.fab_approve);
        final String key = getIntent().getStringExtra("data1");
        Log.d("Key di Intent","Value : "+ key);

        getRefenence.child("schedule").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    final Schedule schedule = dataSnapshot.getValue(Schedule.class);

                    DataInformation patient = schedule.getDetail_patient();
                    Medicine medicine = schedule.getDetail_medicine();
                    tv_nama_schedule.setText(patient.getNama());
                    tv_jadwal_patient.setText(schedule.getJadwal());
                    tv_jam_patient.setText(schedule.getJam());
                    tv_obat_patient.setText(medicine.getNama_obat());


                    fab_approve.setOnClickListener(new View.OnClickListener(){
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(View view){
                            try {

                                presenter.approvalSchedule(key, et_keterangan_patient.getText().toString());

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                } else {
                    //bus number doesn't exists.
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MyListData", "Error: ", databaseError.toException());
            }
        });



    }

    @Override
    public void listSchedule(List<Schedule> list) {

    }

    @Override
    public void informationStatusAll(List<Schedule> finish, List<Schedule> unfinish) {

    }

    @Override
    public void informatinoStatusOne(List<Schedule> finish, List<Schedule> unfinish) {

    }

    @Override
    public void listMedicine(List<Medicine> list) {

    }

    @Override
    public void listScheduleSuccess(List<Schedule> list) {

    }

    @Override
    public void listScheduleFailure(List<Schedule> list) {

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
        Toast.makeText(this, "Success approve", Toast.LENGTH_SHORT).show();
        dialog.show();
        super.onBackPressed();
    }

    @Override
    public void message(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
