package com.ulul.medbuddies.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulul.medbuddies.contract.PatientContract;
import com.ulul.medbuddies.model.DataInformation;
import com.ulul.medbuddies.util.LocalStorage;

import java.util.ArrayList;
import java.util.List;

public class PatientPresenter implements PatientContract.Presenter {
    PatientContract.View view;
    Context context;
    DatabaseReference databaseReference;
    FirebaseUser mAuth;

    LocalStorage localStorage;

    public PatientPresenter(PatientContract.View view){
        this.view = view;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void getList() {
        view.onLoad();
        databaseReference.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<DataInformation> list = new ArrayList<>();
                Log.e("uid care taker ", mAuth.getUid());
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    if (ds.getValue(DataInformation.class).getCare_taker().equals(mAuth.getUid())){
                        DataInformation d = ds.getValue(DataInformation.class);
                        d.setKey(ds.getKey());
                        list.add(d);
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
        databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean cek = false;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    DataInformation data = ds.getValue(DataInformation.class);
                    Log.e("data connnect Patietn", data.getCare_taker() + " " + data.getRole() + " = " + no_telp);
                    if (data.getRole().equals("1") && data.getNo_telp().equals(no_telp) && localStorage.getInt("role") == 0) {
                        if (!data.getCare_taker().equals(mAuth.getUid())) {
                            if (data.getCare_taker().equals("")) {
                                data.setCare_taker(mAuth.getUid());
                                databaseReference.child("user").child(ds.getKey()).setValue(data);
                                databaseReference.child("user").child(mAuth.getUid()).child("pasien").child(ds.getKey()).setValue(data);
                                cek = true;
                                view.message("Success add patient");
                                view.onSuccess();
                                break;
                            } else {
                                cek = true;
                                view.message("Sorry, patient has registered");
                            }
                        } else {
                            cek = true;
                            view.message("Sorry, patient has registered");
                            break;
                        }
                    } else if (data.getRole().equals("0") && data.getNo_telp().equals(no_telp) && localStorage.getInt("role") == 1) {
                        boolean cekDataUser = false;
                        DataInformation dataUser = new DataInformation();

                        for (DataSnapshot cekEmpty : dataSnapshot.getChildren()) {
                            DataInformation cekData = cekEmpty.getValue(DataInformation.class);

                            if (cekEmpty.getKey().equals(mAuth.getUid())){
//                                cekDataUser = true;
                                dataUser = cekData;
                            }
                        }
                        dataUser.setCare_taker(ds.getKey());
                        databaseReference.child("user").child(mAuth.getUid()).setValue(dataUser);
                        databaseReference.child("user").child(ds.getKey()).child("pasien")
                                .child(mAuth.getUid()).setValue(dataUser);

                        cek = true;
                        localStorage.setString("care_taker", ds.getKey());
                        localStorage.setString("no_telp_care_taker", data.getNo_telp());
                        localStorage.setString("nama_care_taker", data.getNama());
                        view.message("Success add care taker");
                        view.onSuccess();
//                        if (!cekDataUser) {
//                            break;
//                        }
                    }
                }
                if (!cek){
                    view.message("Sorry phone number doesn't exists");
                    view.onError();
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
        localStorage = new LocalStorage(context, "user");
    }
}
