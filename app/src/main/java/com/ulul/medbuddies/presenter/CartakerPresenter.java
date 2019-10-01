package com.ulul.medbuddies.presenter;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ulul.medbuddies.contract.CaretakerContract;
import com.ulul.medbuddies.contract.HospitalContract;
import com.ulul.medbuddies.model.Caretaker;
import com.ulul.medbuddies.model.Hospital;

public class CartakerPresenter implements CaretakerContract.Presenter {
    DatabaseReference databaseReference;
    FirebaseUser mAuth;
    CaretakerContract.View view;
    Context context;
    Caretaker caretaker;

    public CartakerPresenter(CaretakerContract.View view){
        this.view = view;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }


    @Override
    public void setCaretaker(String nama_caretaker, String alamat_caretaker, String no_telp_caretaker) {
        caretaker = new Caretaker(nama_caretaker, alamat_caretaker, no_telp_caretaker);
    }

    @Override
    public void registerCaretaker() {
        databaseReference.child("hospital").child(caretaker.getNama_caretaker()).setValue(caretaker);
    }
}
