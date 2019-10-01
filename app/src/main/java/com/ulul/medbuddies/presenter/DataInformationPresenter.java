package com.ulul.medbuddies.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulul.medbuddies.contract.DataInformationContract;
import com.ulul.medbuddies.model.DataInformation;

public class DataInformationPresenter implements DataInformationContract.Presenter {
    DatabaseReference databaseReference;
    FirebaseUser mAuth;
    DataInformationContract.View view;
    Context context;
    DataInformation dataInformation;

    public DataInformationPresenter(DataInformationContract.View view){
        this.view = view;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void setData(String nama, String alamat, String no_telp, String ttl, String jenis_kelamin, String sumber_biaya,String role) {
        dataInformation = new DataInformation(nama, alamat, no_telp, ttl, jenis_kelamin, sumber_biaya,role);
    }

    @Override
    public void submitData() {
        databaseReference.child("user").child(mAuth.getUid()).setValue(dataInformation);
    }
}
