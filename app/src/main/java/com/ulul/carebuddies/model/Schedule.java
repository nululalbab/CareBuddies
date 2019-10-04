package com.ulul.carebuddies.model;

import android.support.annotation.Nullable;

public class Schedule {
    String key;
    String jadwal;
    String jam;
    int status;
    String keterangan;
    String care_taker;
    String patient;
    String medicine;
    DataInformation detail_care_taker;
    DataInformation detail_patient;
    Medicine detail_medicine;

    public Schedule(String jadwal, String jam, int status, String keterangan, @Nullable String care_taker, String patient,
                    String medicine, DataInformation detail_care_taker, DataInformation detail_patient, Medicine detail_medicine) {
        this.jadwal = jadwal;
        this.jam = jam;
        this.status = status;
        this.keterangan = keterangan;
        this.care_taker = care_taker;
        this.patient = patient;
        this.medicine = medicine;
        this.detail_care_taker = detail_care_taker;
        this.detail_patient = detail_patient;
        this.detail_medicine = detail_medicine;
    }

    public Schedule() {
    }

    public Medicine getDetail_medicine() {
        return detail_medicine;
    }

    public void setDetail_medicine(Medicine detail_medicine) {
        this.detail_medicine = detail_medicine;
    }

    public DataInformation getDetail_care_taker() {
        return detail_care_taker;
    }

    public void setDetail_care_taker(DataInformation detail_care_taker) {
        this.detail_care_taker = detail_care_taker;
    }

    public DataInformation getDetail_patient() {
        return detail_patient;
    }

    public void setDetail_patient(DataInformation detail_patient) {
        this.detail_patient = detail_patient;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
