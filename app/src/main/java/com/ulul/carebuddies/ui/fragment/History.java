package com.ulul.carebuddies.ui.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.style.LineBackgroundSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.ulul.carebuddies.R;
import com.ulul.carebuddies.adapter.HistoryAdapter;
import com.ulul.carebuddies.contract.HistoryContract;
import com.ulul.carebuddies.presenter.HistoryPresenter;
import com.ulul.carebuddies.ui.activity.DataHistoryActivity;
import com.ulul.carebuddies.ui.activity.DataScheduleActivity;
import com.ulul.carebuddies.ui.activity.PrintHistoryActivity;
import com.ulul.carebuddies.ui.activity.ScheduleRegisterActivity;
import com.ulul.carebuddies.util.CustomSpan;
import com.ulul.carebuddies.util.EventDecorator;
import com.ulul.carebuddies.util.EventDecoratorMaterial;
import com.ulul.carebuddies.util.ItemClickSupport;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class History extends Fragment implements HistoryContract.View {
    HistoryPresenter presenter;
    TextView countSucccess, countFailure;
    LinearLayout card_success;
    MaterialCalendarView calendarView;
    Button btn_print_resume;
    HistoryAdapter adapter;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    List<com.ulul.carebuddies.model.Schedule> dataList;

    public History() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        countFailure = (TextView) view.findViewById(R.id.countFailure);
        countSucccess = (TextView) view.findViewById(R.id.countSuccess);
        calendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        card_success = (LinearLayout)view.findViewById(R.id.card1);
        btn_print_resume = (Button)view.findViewById(R.id.btn_print_resume);

        calendarView.state().edit().commit();

        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading. Please wait...");

        presenter = new HistoryPresenter(this);
        presenter.setContext(getContext());

        presenter.countHistory();
        presenter.getListFailure();
        presenter.getListSuccess();

        card_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataHistoryActivity.class);
                intent.putExtra("status", 1);
                startActivity(intent);
            }
        });

        btn_print_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PrintHistoryActivity.class);
                intent.putExtra("status", 1);
                startActivity(intent);
            }
        });

    }

    @Override
    public void showResume() {

    }

    @Override
    public void listSuccess(List<CalendarDay> list) {
//        EventDecorator[] decoratorArray = new ArrayList; //Max 4 dots
//        for(int i = 0; i<decoratorArray.length; i++){
//            decoratorArray[i] = new EventDecorator(0x000, 4.0f,i);
//        }

        /*dayInstanceMap contains all the mappings.*/

//        EventDecoratorMaterial event = new EventDecoratorMaterial(Color.WHITE, list);
//        calendarView.addDecorator(event);
        for (int i = 0; i < list.size(); i++) {
            EventDecorator event = new EventDecorator(Color.YELLOW, 5.0f, 0);
            event.addDate(list.get(i));
            calendarView.addDecorator(event);
            Log.e("masuk succes list ", list.get(i).getDate().toString());
        }
//        calendarView.addDecorators(decoratorArray);
//        for(Map.Entry<CalendarDay,Integer> entry : dayInstanceMap.entrySet()){
//            CalendarDay currDay = entry.getKey();
//            Integer currDayCount = entry.getValue(); //If you have max amount of dots, check here if currDay is too large.
//            for(int i = 0; i<currDayCount; i++)
//                decoratorArray[i].addDate(currDay);
//        }
    }

    @Override
    public void listFailure(List<CalendarDay> list) {
        for (int i = 0; i < list.size(); i++) {
            EventDecorator event = new EventDecorator(Color.BLACK, 5.0f, 2);
            event.addDate(list.get(i));
            calendarView.addDecorator(event);
            Log.e("masuk succes list ", list.get(i).getDate().toString());
        }
    }

    @Override
    public void listHistory(List<CalendarDay> success, List<CalendarDay> failure) {
        countFailure.setText(String.valueOf(failure.size()) + " Times");
        countSucccess.setText(String.valueOf(success.size()) + " Times");
    }

    @Override
    public void setPresenter(Object presenter) {

    }

    @Override
    public void onLoad() {
        dialog.show();
    }

    @Override
    public void onError() {
        dialog.dismiss();
    }

    @Override
    public void onSuccess() {
        dialog.dismiss();
    }

    @Override
    public void message(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
