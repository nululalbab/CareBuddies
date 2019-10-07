package com.ulul.medbuddies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ulul.medbuddies.R;
import com.ulul.medbuddies.model.Schedule;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    List<Schedule> list;

    public HistoryAdapter(List<Schedule> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_history_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bindItem(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateList(List<Schedule> list){
        this.list = list;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_jadwal;
        TextView txt_jam;
        TextView txt_obat;
        TextView txt_nama_jadwal;
        TextView txt_keterangan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           txt_jadwal = (TextView) itemView.findViewById(R.id.txt_jadwal_history);
            txt_jam = (TextView) itemView.findViewById(R.id.txt_jam_history);
            txt_obat = (TextView) itemView.findViewById(R.id.txt_obat_history);
            txt_nama_jadwal = (TextView) itemView.findViewById(R.id.txt_nama_jadwal_history);
            txt_keterangan = (TextView) itemView.findViewById(R.id.txt_keterangan_history);

        }

        public void bindItem(Schedule item){
            txt_jadwal.setText(item.getJadwal());
            txt_jam.setText(item.getJam());
            txt_obat.setText(item.getDetail_medicine().getNama_obat());
            txt_keterangan.setText(item.getKeterangan());
            txt_nama_jadwal.setText(item.getDetail_patient().getNama());

        }
    }
}
