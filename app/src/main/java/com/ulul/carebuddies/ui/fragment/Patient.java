package com.ulul.carebuddies.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulul.carebuddies.R;
import com.ulul.carebuddies.adapter.PatientAdapter;
import com.ulul.carebuddies.contract.PatientContract;
import com.ulul.carebuddies.model.DataInformation;
import com.ulul.carebuddies.presenter.PatientPresenter;

import java.util.ArrayList;
import java.util.List;

public class Patient extends Fragment implements PatientContract.View {
    PatientAdapter adapter;
    RecyclerView recyclerView;
    List<DataInformation> dataList;
    PatientPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_patient, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        DataInformation datadummy = new DataInformation();
        datadummy.setNama("Dimas");
        datadummy.setNo_telp("081224997124");
        dataList = new ArrayList<>();
        dataList.add(datadummy);

        presenter = new PatientPresenter(this);
        presenter.setContext(getContext());
        presenter.getList();
    }


    @Override
    public void listPatient(List<DataInformation> list) {
        dataList = list;
        adapter = new PatientAdapter(dataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void detailPatient(DataInformation user) {

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
