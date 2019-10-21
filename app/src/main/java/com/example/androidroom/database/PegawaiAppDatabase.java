package com.example.androidroom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidroom.dao.PegawaiDAO;
import com.example.androidroom.entity.Pegawai;

@Database(entities = {Pegawai.class}, version = 1)
public abstract  class PegawaiAppDatabase extends RoomDatabase {

    public abstract PegawaiDAO pegawaiDAO();

    private static volatile PegawaiAppDatabase INSTANCE;

    static PegawaiAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PegawaiAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PegawaiAppDatabase.class, "pegawai_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
