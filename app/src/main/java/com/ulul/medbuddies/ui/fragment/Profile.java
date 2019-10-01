package com.ulul.medbuddies.ui.fragment;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import static android.Manifest.permission.CALL_PHONE;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulul.medbuddies.R;
import com.ulul.medbuddies.model.DataInformation;
import com.ulul.medbuddies.model.Hospital;
import com.ulul.medbuddies.ui.activity.HospitalActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {
    final int REQUEST_CALL = 1;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        final TextView profileName = view.findViewById(R.id.profile_name);
        final TextView profilePhone = view.findViewById(R.id.profile_phone);
        final TextView profileAddress = view.findViewById(R.id.profile_address);
        final TextView namaRS = view.findViewById(R.id.tv_hospital_name);
        final TextView no_telpRS = view.findViewById(R.id.tv_hospital_phone);
        final TextView alamatRS = view.findViewById(R.id.tv_hospital_address);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String idUser = user.getUid();
        final FloatingActionButton fab_call_hospital=(FloatingActionButton) view.findViewById(R.id.fab_call_hospital);

       FirebaseDatabase getDatabase = FirebaseDatabase.getInstance();
       DatabaseReference getRefenence = getDatabase.getReference();
       //Read Profile
        getRefenence.child("user").child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    DataInformation profil = dataSnapshot.getValue(DataInformation.class);
                    profileName.setText(profil.getNama());
                    profilePhone.setText(profil.getNo_telp());
                    profileAddress.setText(profil.getAlamat());
                } else {
                    //bus number doesn't exists.
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MyListData", "Error: ", databaseError.toException());
            }
        });

//      Read Hospital
        getRefenence.child("hospital").child(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    Hospital rumahsakit = dataSnapshot.getValue(Hospital.class);
                    namaRS.setText(rumahsakit.getNama_rumahsakit());
                    no_telpRS.setText(rumahsakit.getNo_telp_rumahsakit());
                    alamatRS.setText(rumahsakit.getAlamat_rumahsakit());
                    final String nope = rumahsakit.getNo_telp_rumahsakit();
                    fab_call_hospital.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                            Intent callIntent = new Intent(Intent.ACTION_CALL);
                            callIntent.setData(Uri.parse("tel:" +nope ));

                            if (ContextCompat.checkSelfPermission(getActivity(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                startActivity(callIntent);
                            } else {
                                requestPermissions(new String[]{CALL_PHONE}, 1);
                            }
                        }
                    });
                    //Register Rumahsakit
                } else {

                    fab_call_hospital.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent goRegister = new Intent(getActivity(), HospitalActivity.class);
                            startActivity(goRegister);

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("MyListData", "Error: ", databaseError.toException());
            }
        });
//      Read Caretaker




    }

}
