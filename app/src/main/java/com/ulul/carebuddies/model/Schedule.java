package com.ulul.carebuddies.model;

import android.support.annotation.Nullable;

public class Schedule {
    String jadwal;
    String jam;
    int status;
    String keterangan;
    String care_taker;
    String patient;
    String medicine;

    public Schedule(String jadwal, String jam, int status, String keterangan, @Nullable String care_taker, String patient, String medicine) {
        this.jadwal = jadwal;
        this.jam = jam;
        this.status = status;
        this.keterangan = keterangan;
        this.care_taker = care_taker;
        this.patient = patient;
        this.medicine = medicine;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getCare_taker() {
        return care_taker;
    }

    public void setCare_taker(String care_taker) {
        this.care_taker = care_taker;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }
}
