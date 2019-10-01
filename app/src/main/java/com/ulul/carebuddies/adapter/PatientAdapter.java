package com.ulul.carebuddies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ulul.carebuddies.R;
import com.ulul.carebuddies.model.DataInformation;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    List<DataInformation> list;

    public PatientAdapter(List<DataInformation> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_patient_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bindItem(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindItem(DataInformation item){

        }
    }
}
