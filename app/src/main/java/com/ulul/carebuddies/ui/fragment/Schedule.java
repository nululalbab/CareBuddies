package com.ulul.carebuddies.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ulul.carebuddies.R;
import com.ulul.carebuddies.contract.PatientContract;
import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.model.DataInformation;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.presenter.PatientPresenter;
import com.ulul.carebuddies.presenter.SchedulePresenter;
import com.ulul.carebuddies.ui.activity.ScheduleRegisterActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule extends Fragment {
    FloatingActionButton fab_add;

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
        super.onViewCreated(view, savedInstanceState);

        fab_add = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ScheduleRegisterActivity.class));
            }
        });
    }
}
