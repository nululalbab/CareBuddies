package com.ulul.carebuddies.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.ulul.carebuddies.R;
import com.ulul.carebuddies.adapter.ScheduleAdapter;
import com.ulul.carebuddies.contract.PatientContract;
import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.model.DataInformation;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.presenter.PatientPresenter;
import com.ulul.carebuddies.presenter.SchedulePresenter;
import com.ulul.carebuddies.ui.activity.DataPatientActivity;
import com.ulul.carebuddies.ui.activity.DataScheduleActivity;
import com.ulul.carebuddies.ui.activity.ScheduleRegisterActivity;
import com.ulul.carebuddies.util.ItemClickSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends Fragment implements ScheduleContract.View {
    FloatingActionButton fab_add;
    RecyclerView recycler_view_schedule;
    SchedulePresenter presenter;
    List<com.ulul.carebuddies.model.Schedule> dataList;
    ScheduleAdapter adapter;

    public Schedule() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MaterialCalendarView materialCalendarView;
        super.onViewCreated(view, savedInstanceState);

        fab_add = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ScheduleRegisterActivity.class));
            }
        });


        recycler_view_schedule = view.findViewById(R.id.recycler_view_schedule);
        presenter = new SchedulePresenter(this);

        adapter = new ScheduleAdapter(new ArrayList<com.ulul.carebuddies.model.Schedule>());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycler_view_schedule.setLayoutManager(layoutManager);

        recycler_view_schedule.setAdapter(adapter);

        //deklarasi widget yang ada di layout activity_main
        materialCalendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        materialCalendarView.state().edit()
                .setMinimumDate(CalendarDay.from(1900, 1, 1))
                .setMaximumDate(CalendarDay.from(2045, 12, 31))
                // Maksud dari MONTHS adalah calender tersebut akan tampil berbentuk 4 minggu atau 1 bulan
                // jika calendar mode tersebut di ganti menjadi WEEKS maka akan yang tampil akan 1 minggu
                .setCalendarDisplayMode(CalendarMode.WEEKS)
                .commit();

        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                //menampilkan toast jika berhasil di klik
                presenter.listScheduleByDate(String.valueOf(date.getDate()));

            }
        });


        ItemClickSupport.addTo(recycler_view_schedule).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Log.d("Position","Value : "+ position);

                Intent intent = new Intent(getActivity(), DataScheduleActivity.class);
                Log.d("Data List Position","Value : "+ dataList.get(position));

                intent.putExtra("data1", dataList.get(position).getKey());

                Log.d("Key","Value : "+ dataList.get(position).getKey());
                startActivity(intent);

            }
        });
    }

    @Override
    public void listSchedule(List<com.ulul.carebuddies.model.Schedule> list) {
        this.dataList = list;
        adapter.updateList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void informationStatusAll(List<com.ulul.carebuddies.model.Schedule> finish, List<com.ulul.carebuddies.model.Schedule> unfinish) {

    }

    @Override
    public void informatinoStatusOne(List<com.ulul.carebuddies.model.Schedule> finish, List<com.ulul.carebuddies.model.Schedule> unfinish) {

    }

    @Override
    public void listMedicine(List<Medicine> list) {

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
