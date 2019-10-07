package com.ulul.medbuddies.model;

public class Medicine {
    String nama_obat;
    String key;

    public Medicine(String nama_obat) {
        this.nama_obat = nama_obat;
    }
    public Medicine(){
        this.nama_obat = "";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama_obat() {
        return nama_obat;
    }

    public void setNama_obat(String nama_obat) {
        this.nama_obat = nama_obat;
    }
}
