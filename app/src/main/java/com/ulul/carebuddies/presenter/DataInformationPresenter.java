package com.ulul.carebuddies.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ulul.carebuddies.contract.DataInformationContract;
import com.ulul.carebuddies.model.DataInformation;

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
        dataInformation = new DataInformation(nama, alamat, no_telp, ttl, jenis_kelamin, sumber_biaya,role, "");
    }

    @Override
    public void submitData() {
        view.onLoad();

        databaseReference.child("user").child(mAuth.getUid()).setValue(dataInformation).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                view.onSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                view.onError();
            }
        });
    }
}
