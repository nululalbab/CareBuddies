package com.ulul.medbuddies.ui.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.ulul.medbuddies.R;
import com.ulul.medbuddies.adapter.HistoryAdapter;
import com.ulul.medbuddies.contract.HistoryContract;
import com.ulul.medbuddies.presenter.HistoryPresenter;
import com.ulul.medbuddies.ui.activity.DataHistoryActivity;
import com.ulul.medbuddies.ui.activity.PrintHistoryActivity;
import com.ulul.medbuddies.util.EventDecorator;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class History extends Fragment implements HistoryContract.View {
    HistoryPresenter presenter;
    TextView countSucccess, countFailure;
    LinearLayout card_success, card_failure;
    MaterialCalendarView calendarView;
    Button btn_print_resume;
    HistoryAdapter adapter;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    List<com.ulul.medbuddies.model.Schedule> dataList;

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
        card_failure = (LinearLayout)view.findViewById(R.id.card2);
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

        card_failure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataHistoryActivity.class);
                intent.putExtra("status", 0);
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
