package com.ulul.medbuddies.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ulul.medbuddies.R;
import com.ulul.medbuddies.model.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {
    List<Schedule> list;

    public ScheduleAdapter(List<Schedule> list){
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

    public void updateList(List<Schedule> list){
        this.list = list;
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_jadwal;
        TextView txt_jam;
        TextView txt_obat;
        TextView txt_nama_jadwal;
        ConstraintLayout card2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           txt_jadwal = (TextView) itemView.findViewById(R.id.txt_jadwal);
            txt_jam = (TextView) itemView.findViewById(R.id.txt_jam);
            txt_obat = (TextView) itemView.findViewById(R.id.txt_obat);
            txt_nama_jadwal = (TextView) itemView.findViewById(R.id.txt_nama_jadwal);
            card2 = (ConstraintLayout) itemView.findViewById(R.id.card2);
        }

        public void bindItem(Schedule item){
            txt_jadwal.setText(item.getJadwal());
            txt_jam.setText(item.getJam());
            txt_obat.setText(item.getDetail_medicine().getNama_obat());
            txt_nama_jadwal.setText(item.getDetail_patient().getNama());

            Calendar c = Calendar.getInstance();
            Date now = new Date();
            c.setTime(now);
            c.add(Calendar.DATE, -1);
            now = c.getTime();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            try{
                Date dateS = formatter.parse(item.getJadwal());
                if (item.getStatus() == 0 && now.after(dateS)){
                    card2.setBackgroundResource(R.drawable.bg_cross);
                } else if (item.getStatus() == 1){
                    Log.e("status adapter" , String.valueOf(item.getStatus()));
                    card2.setBackgroundResource(R.drawable.bg_green);
                } else {
                    card2.setBackgroundResource(R.drawable.bg_pill);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
    }
}
