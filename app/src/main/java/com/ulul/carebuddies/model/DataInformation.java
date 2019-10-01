package com.ulul.carebuddies.model;

public class DataInformation {
    String nama;
    String alamat;
    String no_telp;
    String ttl;
    String jenis_kelamin;
    String sumber_biaya;
    String role;
    String care_taker;

    public DataInformation(String nama, String alamat, String no_telp, String ttl, String jenis_kalamin, String sumber_biaya, String role, String care_taker) {
        this.nama = nama;
        this.alamat = alamat;
        this.no_telp = no_telp;
        this.ttl = ttl;
        this.jenis_kelamin = jenis_kalamin;
        this.sumber_biaya = sumber_biaya;
        this.role = role;
        this.care_taker = care_taker;
    }

    public DataInformation() {
        this.nama = "";
        this.alamat = "";
        this.no_telp = "";
        this.ttl = "";
        this.jenis_kelamin = "";
        this.sumber_biaya = "";
        this.role = "";
        this.care_taker = "";
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getSumber_biaya() {
        return sumber_biaya;
    }

    public void setSumber_biaya(String sumber_biaya) {
        this.sumber_biaya = sumber_biaya;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getCare_taker() {
        return care_taker;
    }

    public void setCare_taker(String care_taker) {
        this.care_taker = care_taker;
    }
}
