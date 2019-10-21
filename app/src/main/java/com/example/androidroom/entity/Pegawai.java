package com.example.androidroom.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pegawai")
public class Pegawai {

    @PrimaryKey(autoGenerate = true)
    private int pegawaiId;

    @NonNull
    @ColumnInfo(name = "nama_pegawai")
    private String namaPegawai;

    @NonNull
    @ColumnInfo(name = "nip")
    private String nip;

    @NonNull
    @ColumnInfo(name = "pegawai_phone")
    private int pegawaiPhone;


    public Pegawai(@NonNull String namaPegawai, @NonNull String nip, int pegawaiPhone) {
        this.namaPegawai = namaPegawai;
        this.nip = nip;
        this.pegawaiPhone = pegawaiPhone;

    }

    public int getPegawaiId() {
        return pegawaiId;
    }

    public void setPegawaiId(int pegawaiId) {
        this.pegawaiId = pegawaiId;
    }

    @NonNull
    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(@NonNull String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    @NonNull
    public String getNip() {
        return nip;
    }

    public void setNip(@NonNull String nip) {
        this.nip = nip;
    }

    public int getPegawaiPhone() {
        return pegawaiPhone;
    }

    public void setPegawaiPhone(int pegawaiPhone) {
        this.pegawaiPhone = pegawaiPhone;
    }

}
