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
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.ulul.medbuddies.contract.HistoryContract;
import com.ulul.medbuddies.model.Schedule;
import com.ulul.medbuddies.util.LocalStorage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HistoryPresenter implements HistoryContract.Presenter {
    HistoryContract.View view;
    Context context;
    DatabaseReference databaseReference;
    FirebaseUser mAuth;

    SimpleDateFormat dateFormat;

    LocalStorage localStorage;

    public HistoryPresenter(HistoryContract.View view){
        this.view = view;

        databaseReference = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance().getCurrentUser();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public List<CalendarDay> filter (List<CalendarDay> list ){
        List<CalendarDay> baru = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean cek = false;
            for (int j = 0; j < baru.size(); j++) {
                if (list.get(i).getDate().toString().equals(baru.get(j).getDate().toString())){
                    cek = true;
                }
            }
            if (!cek){
                baru.add(list.get(i));
            }
        }
        return baru;
    }

    @Override
    public void getListSuccess() {
        view.onLoad();
        databaseReference.child("schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<CalendarDay> list = new ArrayList<>();
                for (DataSnapshot ds :dataSnapshot.getChildren()){
                    Schedule schedule = ds.getValue(Schedule.class);
                    boolean cek = false;
                    if (schedule.getCare_taker().equals(mAuth.getUid()) && localStorage.getInt("role") == 0){
                        cek = true;
                    } else if (schedule.getPatient().equals(mAuth.getUid()) && localStorage.getInt("role") == 1){
                        cek = true;
                    }
                    if (schedule.getStatus() == 1 && cek){
                        String [] arr = schedule.getJadwal().split("-");
                        if (arr.length > 1){
                            Log.e("masuk success ", "oke");
                            CalendarDay day = CalendarDay.from(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]));
                            list.add(day);
                        }
                    }
                }
                view.listSuccess(filter(list));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.onError();
                view.message(databaseError.getMessage());
            }
        });
    }

    @Override
    public void getListFailure() {
        view.onLoad();
        databaseReference.child("schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<CalendarDay> list = new ArrayList<>();

                Calendar c = Calendar.getInstance();
                Date now = new Date();
                c.setTime(now);
                c.add(Calendar.DATE, -1);
                now = c.getTime();

                for (DataSnapshot ds :dataSnapshot.getChildren()){
                    Schedule schedule = ds.getValue(Schedule.class);
                    boolean cek = false;
                    if (schedule.getCare_taker().equals(mAuth.getUid()) && localStorage.getInt("role") == 0){
                        cek = true;
                    } else if (schedule.getPatient().equals(mAuth.getUid()) && localStorage.getInt("role") == 1){
                        cek = true;
                    }
                    if (schedule.getStatus() == 0 && cek){
                        Date dateS = null;
                        try {
                            dateS = dateFormat.parse(schedule.getJadwal());
                            if (now.after(dateS)){
                                String [] arr = schedule.getJadwal().split("-");
                                if (arr.length == 3){
                                    CalendarDay day = CalendarDay.from(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]), Integer.valueOf(arr[2]));
                                    list.add(day);
                                }
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                view.listFailure(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                view.onError();
                view.message(databaseError.getMessage());
            }
        });
    }

    @Override
    public void countHistory() {
        view.onLoad();
        databaseReference.child("schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<CalendarDay> listSuccess = new ArrayList<>();
                List<CalendarDay> listFailure = new ArrayList<>();
                Calendar c = Calendar.getInstance();
                Date now = new Date();
                c.setTime(now);
                c.add(Calendar.DATE, -1);
                now = c.getTime();

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Schedule schedule = ds.getValue(Schedule.class);
                    boolean ceking = false;
                    if (localStorage.getInt("role") == 1 && schedule.getPatient().equals(mAuth.getUid())){
                        ceking = true;
                    } else if (localStorage.getInt("role") == 0 && schedule.getCare_taker().equals(mAuth.getUid())) {
                        ceking = true;
                    }

                    if (ceking){
                        if (schedule.getStatus() == 1){
                            String [] arr = schedule.getJadwal().split("-");
                            if (arr.length > 1){
                                CalendarDay day = CalendarDay.from(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]) - 1, Integer.valueOf(arr[2]));
                                listSuccess.add(day);
                            }
                        } else if (schedule.getStatus() == 0){
                            Date dateS = null;
                            try {
                                dateS = dateFormat.parse(schedule.getJadwal());
                                if (now.after(dateS)){
                                    String [] arr = schedule.getJadwal().split("-");
                                    if (arr.length > 1){
                                        CalendarDay day = CalendarDay.from(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]) - 1, Integer.valueOf(arr[2]));
                                        listFailure.add(day);
                                    }
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                view.listHistory(listSuccess, listFailure);
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
    public void downloadResume() {

    }

    @Override
    public void setContext(Context context) {
        this.context = context;
        localStorage = new LocalStorage(context, "user");
    }
}
