package com.ulul.carebuddies.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.model.Schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SchedulePresenter implements ScheduleContract.Presenter{
    ScheduleContract.View view;
    Context context;
    Schedule schedule;
    List<Schedule> listSchedule;
    DatabaseReference databaseReference;
    FirebaseUser mAuth;

    public SchedulePresenter(ScheduleContract.View view){
        this.view = view;
        listSchedule = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void setData(Date start, Date end, String jam, String status, String keterangan, String care_taker, String patient, String medicine) {
        List<Date> range = new ArrayList<>();
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(start);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(end);

        while (startCalendar.before(endCalendar)){
            Date result = startCalendar.getTime();
            range.add(result);
            startCalendar.add(Calendar.DATE, 1);
        }

        for (Date d: range){
            listSchedule.add(new Schedule(d.toString(), jam, status, keterangan, care_taker, patient, medicine));
        }
    }

    @Override
    public void submitData() {
        for (Schedule s: listSchedule){
            databaseReference.child("schedule").setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    view.onSuccess();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    view.onError();
                    view.message(e.getMessage());
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
    public void setContext(Context context) {
        this.context = context;
    }
}
