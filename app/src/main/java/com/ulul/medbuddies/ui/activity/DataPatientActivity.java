package com.ulul.medbuddies.ui.activity;

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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulul.medbuddies.R;
import com.ulul.medbuddies.model.DataInformation;

import static android.Manifest.permission.CALL_PHONE;

public class DataPatientActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_patient);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        FirebaseDatabase getDatabase = FirebaseDatabase.getInstance();
        DatabaseReference getRefenence = getDatabase.getReference();

        final TextView tv_nama_patient = (TextView) findViewById(R.id.tv_nama_patient);
        final TextView tv_alamat_patient = (TextView) findViewById(R.id.tv_alamat_patient);
        final TextView tv_no_telp_patient = (TextView) findViewById(R.id.tv_no_telp_patient);
        final TextView tv_ttl = (TextView) findViewById(R.id.tv_ttl);
        final TextView tv_jenis_kelamin = (TextView) findViewById(R.id.tv_jenis_kelamin);
        final TextView tv_sumber_biaya = (TextView) findViewById(R.id.tv_sumber_biaya);
        final FloatingActionButton fab_call_patient = (FloatingActionButton)findViewById(R.id.fab_call_patient);
        String key = getIntent().getStringExtra("data");

        getRefenence.child("user").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    DataInformation profil = dataSnapshot.getValue(DataInformation.class);
                    tv_nama_patient.setText(profil.getNama());
                    tv_no_telp_patient.setText(profil.getNo_telp());
                    tv_alamat_patient.setText(profil.getAlamat());
                    tv_ttl.setText(profil.getTtl());
                    tv_jenis_kelamin.setText(profil.getJenis_kelamin());
                    tv_sumber_biaya.setText(profil.getSumber_biaya());

                    final String nope = profil.getNo_telp();
                    fab_call_patient.setOnClickListener(new View.OnClickListener(){
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(View view){
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" +nope ));

                            if (ContextCompat.checkSelfPermission(DataPatientActivity.this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                startActivity(callIntent);
                            } else {
                                requestPermissions(new String[]{CALL_PHONE}, 1);
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
}
