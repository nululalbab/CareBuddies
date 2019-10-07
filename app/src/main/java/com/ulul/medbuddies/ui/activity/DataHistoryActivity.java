package com.ulul.medbuddies.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ulul.medbuddies.R;
import com.ulul.medbuddies.adapter.HistoryAdapter;
import com.ulul.medbuddies.contract.ScheduleContract;
import com.ulul.medbuddies.model.Medicine;
import com.ulul.medbuddies.model.Schedule;
import com.ulul.medbuddies.presenter.SchedulePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataHistoryActivity extends AppCompatActivity implements ScheduleContract.View {
    SchedulePresenter presenter;
    List<Schedule> dataList;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    HistoryAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_history);
        presenter = new SchedulePresenter(this);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        FirebaseDatabase getDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference getRefenence = getDatabase.getReference();

        if(getIntent().getIntExtra("status",-1)==1){
            presenter.getListScheduleSuccess();

        }else if (getIntent().getIntExtra("statis",-1)==0){
            presenter.getListScheduleFailure();
        }

        dialog = new ProgressDialog(DataHistoryActivity.this);
        dialog.setMessage("Loading. Please wait...");

        recyclerView = findViewById(R.id.recycler_view_history);

        adapter = new HistoryAdapter(new ArrayList<Schedule>());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }


    @Override
    public void listSchedule(List<Schedule> list) {

    }

    @Override
    public void informationStatusAll(List<Schedule> finish, List<Schedule> unfinish) {

    }

    @Override
    public void informatinoStatusOne(List<Schedule> finish, List<Schedule> unfinish) {

    }

    @Override
    public void listMedicine(List<Medicine> list) {

    }

    @Override
    public void listScheduleByPatient(HashMap<String, List<Schedule>> list) {

    }

    @Override
    public void listScheduleSuccess(List<Schedule> list) {
        this.dataList = list;
        adapter.updateList(list);
        adapter.notifyDataSetChanged();
        Log.e("test bro","masuk gak");
    }

    @Override
    public void listScheduleFailure(List<Schedule> list) {

    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void onLoad() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void message(String msg) {

    }
}
