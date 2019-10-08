package com.ulul.medbuddies.ui.fragment;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import static android.Manifest.permission.CALL_PHONE;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulul.medbuddies.R;
import com.ulul.medbuddies.model.DataInformation;
import com.ulul.medbuddies.model.Hospital;
import com.ulul.medbuddies.ui.activity.CareTakerRegisterActivity;
import com.ulul.medbuddies.ui.activity.HospitalActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulul.medbuddies.ui.activity.LoginActivity;
import com.ulul.medbuddies.ui.activity.PatientRegisterActivity;
import com.ulul.medbuddies.util.LocalStorage;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {
    final int REQUEST_CALL = 1;
    LocalStorage localStorage;
    int roleUser;
    String care_taker;

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

        TextView tv_pc_card = view.findViewById(R.id.tv_caretaker);
        TextView tv_pc_card_sub = view.findViewById(R.id.tv_rumahsakit);

        final TextView profileName = view.findViewById(R.id.profile_name);
        final TextView profilePhone = view.findViewById(R.id.profile_phone);
        final TextView profileAddress = view.findViewById(R.id.profile_address);
        final TextView namaRS = view.findViewById(R.id.tv_hospital_name);
        final TextView no_telpRS = view.findViewById(R.id.tv_hospital_phone);
        final TextView alamatRS = view.findViewById(R.id.tv_hospital_address);
        final FloatingActionButton btn_add_patient = view.findViewById(R.id.btn_add_patient);
        Button btn_logout = view.findViewById(R.id.btn_logout);

        localStorage = new LocalStorage(getContext(), "user");
        roleUser = localStorage.getInt("role");
        care_taker = localStorage.getString("care_taker");

        if (roleUser == 1){
            tv_pc_card.setText("Care Taker Card");
            if (!care_taker.equals("") && care_taker != null){
                btn_add_patient.setImageResource(R.drawable.ic_call_black_24dp);
                tv_pc_card_sub.setText(localStorage.getString("nama_care_taker"));
            }
        }

        final FirebaseAuth auth = FirebaseAuth.getInstance();
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

        btn_add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roleUser == 0){
                    startActivity(new Intent(getActivity(), PatientRegisterActivity.class));
                } else if (roleUser ==1){
                    if (care_taker.equals("") || care_taker == null){
                        startActivity(new Intent(getActivity(), CareTakerRegisterActivity.class));
                    } else {
//                        Toast.makeText(getContext(), localStorage.getString("no_telp_care_taker"), Toast.LENGTH_SHORT).show();
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" +localStorage.getString("no_telp_care_taker")));

                        if (ContextCompat.checkSelfPermission(getActivity(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                            startActivity(callIntent);
                        } else {
                            requestPermissions(new String[]{CALL_PHONE}, 1);
                        }
                    }
                }
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Toast.makeText(getActivity(), "Logout Success", Toast.LENGTH_SHORT).show();
                localStorage.removeUserPref();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });
    }

}
