package com.ulul.carebuddies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ulul.carebuddies.R;
import com.ulul.carebuddies.model.DataInformation;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    List<DataInformation> list;

    public ScheduleAdapter(List<DataInformation> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_schedule_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bindItem(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_jadwal;
        TextView txt_jam;
        TextView txt_obat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           txt_jadwal = (TextView) itemView.findViewById(R.id.txt_jadwal);
            txt_jam = (TextView) itemView.findViewById(R.id.txt_jam);
            txt_obat = (TextView) itemView.findViewById(R.id.txt_obat);
        }

        public void bindItem(DataInformation item){
            txt_jadwal.setText(item.getNama());
            txt_jam.setText(item.getNo_telp());
            txt_obat.setText(item.getNo_telp());
        }
    }
}
