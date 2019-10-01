package com.ulul.carebuddies.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulul.carebuddies.contract.PatientContract;
import com.ulul.carebuddies.model.DataInformation;

import java.util.ArrayList;
import java.util.List;

public class PatientPresenter implements PatientContract.Presenter {
    PatientContract.View view;
    Context context;
    DatabaseReference databaseReference;
    FirebaseUser mAuth;

    public PatientPresenter(PatientContract.View view){
        this.view = view;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void getList(String id) {
        view.onLoad();
        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataInformation> list = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (ds.getValue(DataInformation.class).getCare_taker() == mAuth.getUid()){
                        list.add(ds.getValue(DataInformation.class));
                    }
                }
                view.listPatient(list);
                view.onSuccess();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.onError();
            }
        });
    }

    @Override
    public void getPatient(String id) {
        view.onLoad();
        databaseReference.child("user").child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    DataInformation data = dataSnapshot.getValue(DataInformation.class);
                    view.detailPatient(data);
                    view.onSuccess();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.onError();
            }
        });
    }

    @Override
    public void connectPatient(final String no_telp) {
        view.onLoad();
        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean cek = false;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    DataInformation data = ds.getValue(DataInformation.class);
                    if (data.getRole() == "1" && data.getNo_telp() == no_telp){
                        data.setCare_taker(mAuth.getUid());
                        databaseReference.child("user").child(ds.getKey()).setValue(data);
                        view.onSuccess();
                        break;
                    }
                }
                if (!cek){
                    view.message("Sorry phone number doesn't exists");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.onError();
                view.message(databaseError.getMessage());
            }
        });
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }
}
