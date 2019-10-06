package com.ulul.carebuddies.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ulul.carebuddies.R;
import com.ulul.carebuddies.model.Schedule;

import java.util.HashMap;
import java.util.List;

public class PrintAdapter extends RecyclerView.Adapter<PrintAdapter.ViewHolder> {

    HashMap<String, List<Schedule>> hashMap;
    List<Schedule> list;


    public PrintAdapter(HashMap<String, List<Schedule>> list){
        this.hashMap = list;
    }

    public PrintAdapter(List<Schedule> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_print_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PrintAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bindItem(list.get(i));
    }

    @Override
    public int getItemCount() {
        return hashMap.size();
    }

//    public void updateList(HashMap<String, List<Schedule>> hashMap){
//        this.hashMap = hashMap;
//        notifyDataSetChanged();
//    }
    public void updateList(List<Schedule> list){
        this.list = list;
        notifyDataSetChanged();
        Log.e("Hashmap",String.valueOf(list.size()));
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_jadwal_print;
        TextView tv_jam_print;
        TextView tv_obat_print;
        TextView tv_nama_print;
        TextView tv_keterangan_print;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           tv_jadwal_print = (TextView) itemView.findViewById(R.id.tv_jadwal_print);
            tv_jam_print = (TextView) itemView.findViewById(R.id.tv_jam_print);
            tv_obat_print = (TextView) itemView.findViewById(R.id.tv_obat_print);
            tv_nama_print = (TextView) itemView.findViewById(R.id.tv_jadwal_print);
            tv_keterangan_print = (TextView) itemView.findViewById(R.id.tv_keterangan_print);

        }

        public void bindItem(Schedule item){
            tv_jadwal_print.setText(item.getJadwal());
            tv_jam_print.setText(item.getJam());
            tv_obat_print.setText(item.getDetail_medicine().getNama_obat());
            tv_keterangan_print.setText(item.getKeterangan());
            tv_nama_print.setText(item.getDetail_patient().getNama());

        }
    }
}
