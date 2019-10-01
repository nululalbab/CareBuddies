package com.ulul.carebuddies.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ulul.carebuddies.R;
import com.ulul.carebuddies.contract.PatientContract;
import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.model.DataInformation;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.model.Schedule;
import com.ulul.carebuddies.presenter.PatientPresenter;
import com.ulul.carebuddies.presenter.SchedulePresenter;

import java.util.ArrayList;
import java.util.List;

public class ScheduleRegisterActivity extends AppCompatActivity  implements ScheduleContract.View, PatientContract.View{

    SchedulePresenter schedulePresenter;
    PatientPresenter patientPresenter;

    List<DataInformation> listPatient;
    List<Medicine> listMedicine;

    EditText start_date, end_date, hours;
    Spinner patient_schedule, medicine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_register);

        start_date = (EditText) findViewById(R.id.start_date);
        end_date = (EditText) findViewById(R.id.end_date);
        hours = (EditText) findViewById(R.id.hours);
        patient_schedule = (Spinner) findViewById(R.id.patient_schedule);
        medicine = (Spinner) findViewById(R.id.medicine);

        listPatient = new ArrayList<>();
        listMedicine = new ArrayList<>();

        schedulePresenter = new SchedulePresenter(this);
        schedulePresenter.setContext(this);
        schedulePresenter.getListMedicine();

        patientPresenter = new PatientPresenter(this);
        patientPresenter.setContext(this);
        patientPresenter.getList();

        List<String> dummy = new ArrayList<>();
        dummy.add("ini");
        dummy.add("ini");
        dummy.add("ini");
        dummy.add("ini");
        dummy.add("ini");

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, dummy);

        medicine.setAdapter(adapter);
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
        listMedicine = list;
        List<String> arrayList = new ArrayList<>();
        for (Medicine m: list){
            arrayList.add(m.getNama_obat());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        medicine.setAdapter(adapter);
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

    @Override
    public void listPatient(List<DataInformation> list) {
        listPatient = list;
        List<String> arrayList = new ArrayList<>();
        for (DataInformation m: list){
            arrayList.add(m.getNama());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        patient_schedule.setAdapter(adapter);

        Toast.makeText(this, String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void detailPatient(DataInformation user) {

    }
}
