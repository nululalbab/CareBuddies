package com.ulul.medbuddies.model;

public class Caretaker {
    String nama_caretaker;
    String alamat_caretaker;
    String no_telp_caretaker;

    public Caretaker() {
    }

    public Caretaker(String nama_caretaker, String alamat_caretaker, String no_telp_caretaker) {
        this.nama_caretaker = nama_caretaker;
        this.alamat_caretaker = alamat_caretaker;
        this.no_telp_caretaker = no_telp_caretaker;
    }

    public String getNama_caretaker() {
        return nama_caretaker;
    }

    public void setNama_caretaker(String nama_caretaker) {
        this.nama_caretaker = nama_caretaker;
    }

    public String getAlamat_caretaker() {
        return alamat_caretaker;
    }

    public void setAlamat_caretaker(String alamat_caretaker) {
        this.alamat_caretaker = alamat_caretaker;
    }

    public String getNo_telp_caretaker() {
        return no_telp_caretaker;
    }

    public void setNo_telp_caretaker(String no_telp_caretaker) {
        this.no_telp_caretaker = no_telp_caretaker;
    }
}
