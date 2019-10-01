package com.ulul.carebuddies.presenter;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ulul.carebuddies.contract.HospitalContract;
import com.ulul.carebuddies.model.Hospital;

public class HospitalPresenter implements HospitalContract.Presenter {
    DatabaseReference databaseReference;
    FirebaseUser mAuth;
    HospitalContract.View hospitalContract;
    Context context;
    Hospital hospital;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    public HospitalPresenter(HospitalContract.View view){
        this.hospitalContract = view;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    public void setHospital(String nama_rumahsakit, String alamat_rumahsakit, String no_telp_rumahsakit) {
        hospital = new Hospital(nama_rumahsakit, alamat_rumahsakit, no_telp_rumahsakit);
    }

    @Override
    public void registerHospital() {
        databaseReference.child("hospital").child(mAuth.getUid()).setValue(hospital);
        hospitalContract.onSuccess();
    }
}
