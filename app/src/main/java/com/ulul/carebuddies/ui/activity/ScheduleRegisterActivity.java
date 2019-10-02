package com.ulul.carebuddies.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ulul.carebuddies.R;
import com.ulul.carebuddies.contract.PatientContract;
import com.ulul.carebuddies.contract.ScheduleContract;
import com.ulul.carebuddies.model.DataInformation;
import com.ulul.carebuddies.model.Medicine;
import com.ulul.carebuddies.model.Schedule;
import com.ulul.carebuddies.presenter.PatientPresenter;
import com.ulul.carebuddies.presenter.SchedulePresenter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScheduleRegisterActivity extends AppCompatActivity  implements ScheduleContract.View, PatientContract.View{

    SchedulePresenter schedulePresenter;
    PatientPresenter patientPresenter;

    List<DataInformation> listPatient;
    List<Medicine> listMedicine;

    EditText start_date, end_date, hours;
    Spinner patient_schedule, medicine;
    FloatingActionButton btn_submit_jadwal;

    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    TimePickerDialog timePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_register);

        start_date = (EditText) findViewById(R.id.start_date);
        end_date = (EditText) findViewById(R.id.end_date);
        hours = (EditText) findViewById(R.id.hours);
        patient_schedule = (Spinner) findViewById(R.id.patient_schedule);
        medicine = (Spinner) findViewById(R.id.medicine);
        btn_submit_jadwal = (FloatingActionButton) findViewById(R.id.btn_submit_jadwal);

        listPatient = new ArrayList<>();
        listMedicine = new ArrayList<>();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        schedulePresenter = new SchedulePresenter(this);
        schedulePresenter.setContext(this);
        schedulePresenter.getListMedicine();

        patientPresenter = new PatientPresenter(this);
        patientPresenter.setContext(this);
        patientPresenter.getList();

        final String keterangan = "";
        final String care_taker = "";
        final String patient = "";

        final ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item);

        medicine.setAdapter(adapter);

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(start_date);
            }
        });

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog(end_date);
            }
        });

        hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimeDialog(hours);
            }
        });
        medicine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                adapter.getItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_submit_jadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date start = format.parse(start_date.getText().toString());
                    Date end = format.parse(end_date.getText().toString());
                    schedulePresenter.setData(start, end, hours.getText().toString(), 0, "", "oNUurmaxr4blpa0NMrJ8nvL5wPC3", "obat1");
                    schedulePresenter.submitData();
                    Toast.makeText(ScheduleRegisterActivity.this, String.valueOf(start.getMonth() +1), Toast.LENGTH_SHORT).show();
                }catch (ParseException e){

                }

            }
        });
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

    private void showDateDialog(final EditText editText){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                editText.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void showTimeDialog(final EditText editText) {

        /**
         * Calendar untuk mendapatkan waktu saat ini
         */
        Calendar calendar = Calendar.getInstance();

        /**
         * Initialize TimePicker Dialog
         */
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                /**
                 * Method ini dipanggil saat kita selesai memilih waktu di DatePicker
                 */
                editText.setText(hourOfDay+":"+minute);
            }
        },
                /**
                 * Tampilkan jam saat ini ketika TimePicker pertama kali dibuka
                 */
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),

                /**
                 * Cek apakah format waktu menggunakan 24-hour format
                 */
                DateFormat.is24HourFormat(this));

        timePickerDialog.show();
    }


}
