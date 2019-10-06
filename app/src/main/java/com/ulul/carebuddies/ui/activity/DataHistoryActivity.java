package com.ulul.carebuddies.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.ulul.carebuddies.R;
import com.ulul.carebuddies.adapter.HistoryAdapter;
import com.ulul.carebuddies.adapter.ScheduleAdapter;
import com.ulul.carebuddies.contract.HistoryContract;
import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.model.DataInformation;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.model.Schedule;
import com.ulul.carebuddies.presenter.HistoryPresenter;
import com.ulul.carebuddies.presenter.SchedulePresenter;
import com.ulul.carebuddies.ui.fragment.History;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.CALL_PHONE;

public class DataHistoryActivity extends AppCompatActivity implements ScheduleContract.View {
    SchedulePresenter presenter;
    List<Schedule> dataList;
    ProgressDialog dialog;
    RecyclerView recyclerView;
    HistoryAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);
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
