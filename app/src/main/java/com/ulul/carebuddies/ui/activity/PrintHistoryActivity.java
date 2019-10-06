package com.ulul.carebuddies.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.ulul.carebuddies.R;
import com.ulul.carebuddies.adapter.HistoryAdapter;
import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.model.Schedule;
import com.ulul.carebuddies.presenter.HistoryPresenter;

import java.util.HashMap;
import java.util.List;

public class PrintHistoryActivity extends AppCompatActivity implements ScheduleContract.View {
    HistoryPresenter presenter;
    TextView countSucccess, countFailure;
    LinearLayout card_success;
    MaterialCalendarView calendarView;
    Button btn_print_resume;
    HistoryAdapter adapter;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    List<Schedule> dataList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_history);
        // Inflate the layout for this fragment

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
