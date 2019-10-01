package com.ulul.medbuddies.model;

public class Hospital {
    String nama_rumahsakit;
    String alamat_rumahsakit;
    String no_telp_rumahsakit;

    public Hospital(String nama_rumahsakit, String alamat_rumahsakit, String no_telp_rumahsakit) {
        this.nama_rumahsakit = nama_rumahsakit;
        this.alamat_rumahsakit = alamat_rumahsakit;
        this.no_telp_rumahsakit = no_telp_rumahsakit;
    }

    public Hospital(){

    }

    public String getNama_rumahsakit() {
        return nama_rumahsakit;
    }

    public void setNama_rumahsakit(String nama_rumahsakit) {
        this.nama_rumahsakit = nama_rumahsakit;
    }

    public String getAlamat_rumahsakit() {
        return alamat_rumahsakit;
    }

    public void setAlamat_rumahsakit(String alamat_rumahsakit) {
        this.alamat_rumahsakit = alamat_rumahsakit;
    }

    public String getNo_telp_rumahsakit() {
        return no_telp_rumahsakit;
    }

    public void setNo_telp_rumahsakit(String no_telp_rumahsakit) {
        this.no_telp_rumahsakit = no_telp_rumahsakit;
    }
}
