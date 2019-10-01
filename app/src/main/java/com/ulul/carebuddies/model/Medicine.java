package com.ulul.carebuddies.model;

public class Medicine {
    String nama_obat;

    public Medicine(String nama_obat) {
        this.nama_obat = nama_obat;
    }
    public Medicine(){}

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }
}
