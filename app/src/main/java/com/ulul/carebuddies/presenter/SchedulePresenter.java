package com.ulul.carebuddies.presenter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.DatePicker;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.model.DataInformation;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.model.Schedule;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class SchedulePresenter implements ScheduleContract.Presenter{
    ScheduleContract.View view;
    Context context;
    Schedule schedule;
    List<Schedule> listSchedule;
    DatabaseReference databaseReference;
    FirebaseUser mAuth;
    String idMedicine;
    String idPatient;


    public SchedulePresenter(ScheduleContract.View view){
        this.view = view;

        listSchedule = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();

        idMedicine = "";
        idPatient = "";
    }

    @Override
    public void setData(Date start, Date end, String jam, int status, String keterangan, String patient, String medicine) {
        List<Date> range = new ArrayList<>();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        endCalendar.add(Calendar.DATE, 1);

        while (startCalendar.before(endCalendar)){
            Date result = startCalendar.getTime();
//            view.message(String.valueOf(result));
            range.add(result);
            startCalendar.add(Calendar.DATE, 1);
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        for (Date d: range){
            String jadwal = formatter.format(d);
            Log.e("e", jadwal);
            Schedule in = new Schedule(jadwal, jam, status, keterangan, mAuth.getUid() , patient, medicine);
            in.setCare_taker(mAuth.getUid());
            listSchedule.add(in);
        }
    }

    @Override
    public void submitData() {
        view.onLoad();
        for (final Schedule s: listSchedule){
            final String uiSchedule = databaseReference.child("schedule").push().getKey();
            databaseReference.child("schedule").child(uiSchedule).setValue(s).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    view.onError();
                    view.message(e.getMessage());
                }
            });

            databaseReference.child("user").child(idPatient).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        DataInformation di = dataSnapshot.getValue(DataInformation.class);
                        databaseReference.child("user").child(mAuth.getUid()).child("pasien").child(idPatient).child("schedule").child(uiSchedule)
                                .setValue(s);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    view.onError();
                    view.message(databaseError.getMessage());
                }
            });
        }
    }

    @Override
    public void listScheduleById(String id) {

    }

    @Override
    public void listScheduleByDate(String date) {

    }

    @Override
    public void getInformationStatusAll() {

    }

    @Override
    public void getInformationStatusone(String id) {

    }

    @Override
    public void getListMedicine() {
        view.onLoad();
        databaseReference.child("obat").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Medicine> list = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Medicine m = ds.getValue(Medicine.class);
                    m.setKey(ds.getKey());
                    list.add(m);
                }
                view.listMedicine(list);
                view.onSuccess();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.onError();
                view.message(databaseError.getMessage());
            }
        });
    }

    @Override
    public void setPatient(String id) {
        idPatient = id;
    }

    @Override
    public void setMedicine(String id) {
        idMedicine = id;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

}
