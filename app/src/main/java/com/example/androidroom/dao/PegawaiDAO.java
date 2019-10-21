package com.example.androidroom.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidroom.entity.Pegawai;

import java.util.List;

@Dao
public interface PegawaiDAO {
    @Insert
    void insert(Pegawai pegawai);

    @Update
    void update(Pegawai pegawai);

    @Delete
    void delete(Pegawai pegawai);

    @Query("DELETE FROM pegawai")
    void deleteAll();

    @Query("SELECT * from pegawai ORDER BY nama_pegawai ASC")
    LiveData<List<Pegawai>> getAllPegawai();
}
